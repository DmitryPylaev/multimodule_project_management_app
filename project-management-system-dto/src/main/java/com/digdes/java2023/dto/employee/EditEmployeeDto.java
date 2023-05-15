package com.digdes.java2023.dto.employee;

import lombok.Data;
//Данные на вход по операциям редактирования/создания
@Data
public class EditEmployeeDto {
    private String lastName;
    private String name;
    private String patronymic;
    private String position;
    private String account;
    private String email;
}
