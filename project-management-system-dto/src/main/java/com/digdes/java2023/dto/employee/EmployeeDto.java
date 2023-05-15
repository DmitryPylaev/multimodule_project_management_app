package com.digdes.java2023.dto.employee;

import lombok.Data;
//Данные на выход по операциям поиска
@Data
public class EmployeeDto {
    private String displayName;
    private String position;
    private String account;
    private String email;
    private String employeeStatus;
}
