package com.digdes.java2023.dto.project;

import com.digdes.java2023.model.project.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Проект. Данные на выход по операциям поиска")
public class ProjectDto {

    @Schema(description = "Код проекта")
    private String code;

    @Schema(description = "Наименование проекта")
    private String name;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Статус проекта")
    private ProjectStatus projectStatus;
}
