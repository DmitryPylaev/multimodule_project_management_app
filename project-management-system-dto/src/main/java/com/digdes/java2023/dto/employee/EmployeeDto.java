package com.digdes.java2023.dto.employee;

import com.digdes.java2023.model.employee.EmployeeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Сотрудник. Данные на выход по операциям поиска")
public class EmployeeDto {

    @Schema(description = "Имя, фамилия, отчество сотрудника, отображаемое в ответ на запрос")
    private String displayName;

    @Schema(description = "Должность")
    private String position;

    @Schema(description = "Учетная запись сотрудника")
    private String account;

    @Schema(description = "Адрес электронной почты")
    private String email;

    @Schema(description = "Статус сотрудника (ACTIVE, REMOVED)")
    private EmployeeStatus employeeStatus;
}
