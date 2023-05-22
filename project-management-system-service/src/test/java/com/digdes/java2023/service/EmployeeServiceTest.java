package com.digdes.java2023.service;

import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.model.employee.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmployeeServiceTest {
    @Test
    void create() {
        EditEmployeeDto employee = new EditEmployeeDto();
        employee.setLastName("Иванов");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Java Developer");
        employee.setAccount("ivi");
        employee.setEmail("iv@mail.ru");
        EmployeeService.create(employee);
    }

    @Test
    void edit() {
        EditEmployeeDto employee = new EditEmployeeDto();
        employee.setPatronymic("Павлович");
        employee.setPosition("Senior Java Developer");
        employee.setAccount("ivi");
        EmployeeService.edit(employee);
    }

    @Test
    void getById() {
        EmployeeDto employee = EmployeeService.get(-44);
        System.out.println(employee);
    }

    @Test
    void getByAccount() {
        EmployeeDto employee = EmployeeService.get("ivi");
        System.out.println(employee);
    }

    @Test
    void find() {
        List<Employee> employeeList = EmployeeService.find("mail");
    }

    @Test
    void deleteById() {
        EmployeeService.delete(-44);
    }
}