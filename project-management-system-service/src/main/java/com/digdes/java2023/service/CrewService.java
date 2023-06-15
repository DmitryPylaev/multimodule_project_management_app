package com.digdes.java2023.service;

import com.digdes.java2023.dto.crew.CrewDto;
import com.digdes.java2023.dto.crew.AddCrewDto;
import com.digdes.java2023.dto.crew.DeleteCrewDto;
import com.digdes.java2023.mapping.CrewMapper;
import com.digdes.java2023.model.crew.ProjectAssignment;
import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.project.Project;
import com.digdes.java2023.repository.CrewRepository;
import com.digdes.java2023.repository.EmployeeRepository;
import com.digdes.java2023.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CrewService {
    private CrewRepository crewRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    public CrewDto add(AddCrewDto addCrewDto) {
        ProjectAssignment projectAssignment = new ProjectAssignment();
        Project project = projectRepository.findByCode(addCrewDto.getProjectCode()).orElseThrow();
        Employee employee = employeeRepository.findByAccount(addCrewDto.getEmployeeAccount()).orElseThrow();

        Optional<ProjectAssignment> projectAssignmentExist = crewRepository.findByProjectIdAndEmployeeId(project.getId(), employee.getId());
        if (projectAssignmentExist.isPresent()) return new CrewDto();

        projectAssignment.setProject(project);
        projectAssignment.setEmployee(employee);
        projectAssignment.setProject_role(addCrewDto.getProjectRole());
        crewRepository.save(projectAssignment);
        return CrewMapper.mapFromEntity(projectAssignment);
    }

    public CrewDto delete(DeleteCrewDto deleteCrewDto) {
        Project project = projectRepository.findByCode(deleteCrewDto.getProjectCode()).orElseThrow();
        Employee employee = employeeRepository.findByAccount(deleteCrewDto.getEmployeeAccount()).orElseThrow();
        Optional<ProjectAssignment> projectAssignment = crewRepository.findByProjectIdAndEmployeeId(project.getId(), employee.getId());
        if (projectAssignment.isPresent()) {
            crewRepository.delete(projectAssignment.get());
            return CrewMapper.mapFromEntity(projectAssignment.get());
        }
        return new CrewDto();
    }

    public List<CrewDto> getByProject(String code) {
        Pageable firstPageWithTenElements = PageRequest.of(0, 10);
        List<CrewDto> result = new ArrayList<>();
        Project project = projectRepository.findByCode(code).orElseThrow();
        List<ProjectAssignment> projectAssignmentList = crewRepository.findByProjectId(project.getId(), firstPageWithTenElements);

        projectAssignmentList.forEach(o -> result.add(CrewMapper.mapFromEntity(o)));

        return result;
    }
}
