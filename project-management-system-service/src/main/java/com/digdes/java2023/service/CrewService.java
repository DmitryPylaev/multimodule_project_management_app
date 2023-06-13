package com.digdes.java2023.service;

import com.digdes.java2023.dto.crew.CrewDto;
import com.digdes.java2023.dto.crew.EditCrewDto;
import com.digdes.java2023.mapping.CrewMapper;
import com.digdes.java2023.model.crew.ProjectAssignment;
import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.project.Project;
import com.digdes.java2023.repository.CrewRepository;
import com.digdes.java2023.repository.EmployeeRepository;
import com.digdes.java2023.repository.ProjectRepository;
import lombok.AllArgsConstructor;
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

    public CrewDto add(EditCrewDto editCrewDto) {
        ProjectAssignment projectAssignment = new ProjectAssignment();
        Project project = projectRepository.findByCode(editCrewDto.getCode()).orElseThrow();
        Employee employee = employeeRepository.findByAccount(editCrewDto.getAccount()).orElseThrow();
        projectAssignment.setProject(project);
        projectAssignment.setEmployee(employee);
        projectAssignment.setProject_role(editCrewDto.getProjectRole());
        crewRepository.save(projectAssignment);
        return CrewMapper.mapFromEntity(projectAssignment);
    }

    public CrewDto delete(String code, String account) {
        Project project = projectRepository.findByCode(code).orElseThrow();
        Employee employee = employeeRepository.findByAccount(account).orElseThrow();
        Optional<ProjectAssignment> projectAssignment = crewRepository.findByProjectIdAndEmployeeId(project.getId(), employee.getId());
        if (projectAssignment.isPresent()) {
            crewRepository.delete(projectAssignment.get());
            return CrewMapper.mapFromEntity(projectAssignment.get());
        }
        return new CrewDto();
    }

    public List<CrewDto> getByProject(String code) {
        List<CrewDto> result = new ArrayList<>();
        Project project = projectRepository.findByCode(code).orElseThrow();
        List<ProjectAssignment> projectAssignmentList = crewRepository.findByProjectId(project.getId());

        for (ProjectAssignment item:projectAssignmentList) {
            result.add(CrewMapper.mapFromEntity(item));
        }
        return result;
    }
}
