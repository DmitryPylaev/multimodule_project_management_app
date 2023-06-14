package com.digdes.java2023.dto.crew;

import com.digdes.java2023.model.crew.ProjectRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Проект. Данные на вход по операции добавления")
public class AddCrewDto {

    @Schema(description = "Код проекта")
    private String code;

    @Schema(description = "Учетная запись сотрудинка")
    private String account;

    @Schema(description = "Роль сотрудника в проекте")
    private ProjectRole projectRole;
}
