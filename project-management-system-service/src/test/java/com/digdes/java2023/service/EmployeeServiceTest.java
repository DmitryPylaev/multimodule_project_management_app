package com.digdes.java2023.service;

import com.digdes.java2023.dto.employee.CreateEmployeeDto;
import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        Mockito.when(passwordEncoder.encode("root")).thenReturn("root");
        Mockito.when(passwordEncoder.encode("user1")).thenReturn("123");

        CreateEmployeeDto employee = new CreateEmployeeDto();
        employee.setLastName("Иванов");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Java Developer");
        employee.setAccount("iva");
        employee.setEmail("iv@mail.ru");
        employee.setUsername("user1");
        employee.setPassword("123");
        employeeService.create(employee);
    }

    @Test
    void create() {
        CreateEmployeeDto employee = new CreateEmployeeDto();
        employee.setLastName("Петров");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Developer");
        employee.setAccount("petr");
        employee.setUsername("user3");
        employee.setPassword("111");
        employeeService.create(employee);

        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Петров Петр Сергеевич");
        expect.setPosition("Developer");
        expect.setAccount("petr");
        expect.setEmployeeStatus("ACTIVE");

        Assertions.assertEquals(expect, employeeService.create(employee));
    }

    @Test
    void edit() {
        EditEmployeeDto employee = new EditEmployeeDto();
        employee.setPatronymic("Павлович");
        employee.setPosition("Senior Java Developer");
        employee.setAccount("ivi");

        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Иванов Петр Павлович");
        expect.setPosition("Senior Java Developer");
        expect.setAccount("ivi");
        expect.setEmail("iv@mail.ru");
        expect.setEmployeeStatus("ACTIVE");

        Assertions.assertEquals(expect, employeeService.edit(employee));
    }

    @Test
    void getById() {
        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Иванов Петр Павлович");
        expect.setPosition("Senior Java Developer");
        expect.setAccount("ivi");
        expect.setEmail("iv@mail.ru");
        expect.setEmployeeStatus("ACTIVE");

        Assertions.assertEquals(expect, employeeService.get(-33));
    }

    @Test
    void getByAccount() {
        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Иванов Петр Павлович");
        expect.setPosition("Senior Java Developer");
        expect.setAccount("ivi");
        expect.setEmail("iv@mail.ru");
        expect.setEmployeeStatus("ACTIVE");

        Assertions.assertEquals(expect, employeeService.get("ivi"));
    }

    @Test
    void find() {
        CreateEmployeeDto employee = new CreateEmployeeDto();
        employee.setLastName("Петров");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Developer");
        employee.setAccount("petr");
        employeeService.create(employee);

        EmployeeDto expect1 = new EmployeeDto();
        expect1.setDisplayName("Иванов Петр Павлович");
        expect1.setPosition("Senior Java Developer");
        expect1.setAccount("ivi");
        expect1.setEmail("iv@mail.ru");
        expect1.setEmployeeStatus("ACTIVE");

        EmployeeDto expect2 = new EmployeeDto();
        expect2.setDisplayName("Петров Петр Сергеевич");
        expect2.setPosition("Developer");
        expect2.setAccount("petr");
        expect2.setEmployeeStatus("ACTIVE");

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(expect1);
        employeeDtoList.add(expect2);
        Assertions.assertEquals(employeeDtoList, employeeService.find("mail"));
    }

    @Test
    void deleteById() {
        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Иванов Петр Павлович");
        expect.setPosition("Senior Java Developer");
        expect.setAccount("ivi");
        expect.setEmail("iv@mail.ru");
        expect.setEmployeeStatus("REMOVED");

        Assertions.assertEquals(expect, employeeService.delete(-33));
    }
}