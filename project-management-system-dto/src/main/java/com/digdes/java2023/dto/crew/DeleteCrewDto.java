package com.digdes.java2023.dto.crew;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Проект. Данные на вход по операции удаления")
@Data
public class DeleteCrewDto {

    @Schema(description = "Код проекта")
    private String projectCode;

    @Schema(description = "Учетная запись сотрудинка")
    private String employeeAccount;
}
