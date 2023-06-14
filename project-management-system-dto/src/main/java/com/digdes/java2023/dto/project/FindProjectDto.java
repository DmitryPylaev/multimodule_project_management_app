package com.digdes.java2023.dto.project;

import com.digdes.java2023.model.project.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Проект. Данные на вход по операции поиска проекта")
public class FindProjectDto {

    @Schema(description = "Список статусов")
    private List<ProjectStatus> statuses;

    @Schema(description = "Строка для поиска")
    private String input;
}
