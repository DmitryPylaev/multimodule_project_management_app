package com.digdes.java2023.dto.crew;

import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.model.crew.ProjectRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Проект. Данные на выход по операции получения участников проекта")
public class CrewDto {

    @Schema(description = "Сотрудник")
    private EmployeeDto employee;

    @Schema(description = "Роль сотрудника в проекте")
    private ProjectRole projectRole;
}
