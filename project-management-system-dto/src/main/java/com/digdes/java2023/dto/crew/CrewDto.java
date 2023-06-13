package com.digdes.java2023.dto.crew;

import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.model.crew.ProjectRole;
import lombok.Data;
//Данные на выход по операции "Получить всех участников проекта"
@Data
public class CrewDto {
    private EmployeeDto employee;
    private ProjectRole projectRole;
}
