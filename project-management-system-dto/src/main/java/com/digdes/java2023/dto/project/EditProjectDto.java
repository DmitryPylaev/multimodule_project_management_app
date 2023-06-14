package com.digdes.java2023.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Проект. Данные на вход по операциям редактирования/создания")
public class EditProjectDto {

    @Schema(description = "Код проекта")
    private String code;

    @Schema(description = "Наименование проекта")
    private String name;

    @Schema(description = "Описание проекта")
    private String description;
}
