package com.digdes.java2023.service;

import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    void create() {
        EditEmployeeDto employee = new EditEmployeeDto();
        employee.setLastName("Иванов");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Java Developer");
        employee.setAccount("ivi");
        employee.setEmail("iv@mail.ru");
        employeeService.create(employee);
    }

    @Test
    void edit() {
        EditEmployeeDto employee = new EditEmployeeDto();
        employee.setPatronymic("Павлович");
        employee.setPosition("Senior Java Developer");
        employee.setAccount("ivi");
        employeeService.edit(employee);
    }

    @Test
    void getById() {
        EmployeeDto employee = employeeService.get(-41);
        System.out.println(employee);
    }

    @Test
    void getByAccount() {
        EmployeeDto employee = employeeService.get("ivi");
        System.out.println(employee);
    }

    @Test
    void find() {
        List<EmployeeDto> employeeList = employeeService.find("mail");
    }

    @Test
    void deleteById() {
        employeeService.delete(-41);
    }
}