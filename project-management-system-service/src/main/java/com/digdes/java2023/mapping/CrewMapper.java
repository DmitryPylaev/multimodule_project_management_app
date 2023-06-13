package com.digdes.java2023.mapping;

import com.digdes.java2023.dto.crew.CrewDto;
import com.digdes.java2023.model.crew.ProjectAssignment;

public class CrewMapper {
    public static CrewDto mapFromEntity(ProjectAssignment projectAssignment) {
        CrewDto crewDto = new CrewDto();
        crewDto.setEmployee(EmployeeMapper.mapFromEntity(projectAssignment.getEmployee()));
        crewDto.setProjectRole(projectAssignment.getProject_role());
        return crewDto;
    }
}
