package com.digdes.java2023.model.employee;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {
    private String lastName;
    private String name;
    private String patronymic;
    private String position;
    private String account;
    private String email;
    private EmployeeStatus employeeStatus;
    private long id;
}
