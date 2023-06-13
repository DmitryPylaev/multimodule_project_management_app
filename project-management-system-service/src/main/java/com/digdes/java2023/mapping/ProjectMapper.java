package com.digdes.java2023.mapping;

import com.digdes.java2023.dto.project.EditProjectDto;
import com.digdes.java2023.dto.project.ProjectDto;
import com.digdes.java2023.model.project.Project;

public class ProjectMapper {
    public static Project createEntity(EditProjectDto projectDto) {
        Project project = new Project();
        project.setCode(projectDto.getCode());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        return project;
    }

    public static Project editEntity(EditProjectDto editProjectDto, Project projectOld) {
        if (editProjectDto.getCode() != null) projectOld.setCode(editProjectDto.getCode());
        if (editProjectDto.getName() != null) projectOld.setName(editProjectDto.getName());
        if (editProjectDto.getDescription() != null) projectOld.setDescription(editProjectDto.getDescription());
        return projectOld;
    }

    public static ProjectDto mapFromEntity(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setCode(project.getCode());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setProjectStatus(project.getProjectStatus());
        return projectDto;
    }
}
