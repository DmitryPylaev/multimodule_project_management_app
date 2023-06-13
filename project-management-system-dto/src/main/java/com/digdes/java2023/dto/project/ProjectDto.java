package com.digdes.java2023.dto.project;

import com.digdes.java2023.model.project.ProjectStatus;
import lombok.Data;
//Данные на выход по операциям поиска
@Data
public class ProjectDto {
    private String code;
    private String name;
    private String description;
    private ProjectStatus projectStatus;
}
