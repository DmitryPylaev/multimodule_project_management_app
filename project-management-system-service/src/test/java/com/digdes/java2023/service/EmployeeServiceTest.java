package com.digdes.java2023.service;

import com.digdes.java2023.dto.employee.CreateEmployeeDto;
import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.model.employee.EmployeeStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {EmployeeService.class})
@EnableAutoConfiguration
@ComponentScan("com.digdes.java2023")
@EnableJpaRepositories("com.digdes.java2023.repository")
@EntityScan(basePackages = "com.digdes.java2023.model")
class EmployeeServiceTest extends BaseTest {
    private static final long id = 12;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    PasswordEncoder passwordEncoder;

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

        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Петров Петр Сергеевич");
        expect.setPosition("Developer");
        expect.setAccount("petr");
        expect.setEmployeeStatus(EmployeeStatus.ACTIVE);

        Assertions.assertEquals(expect, employeeService.create(employee));
    }

    @Test
    void edit() {
        create();

        EditEmployeeDto employee = new EditEmployeeDto();
        employee.setPosition("Middle Developer");
        employee.setAccount("petr");

        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Петров Петр Сергеевич");
        expect.setPosition("Middle Developer");
        expect.setAccount("petr");
        expect.setEmployeeStatus(EmployeeStatus.ACTIVE);

        Assertions.assertEquals(expect, employeeService.edit(employee));
    }

    @Test
    void getById() {
        create();

        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Петров Петр Сергеевич");
        expect.setPosition("Developer");
        expect.setAccount("petr");
        expect.setEmployeeStatus(EmployeeStatus.ACTIVE);

        Assertions.assertEquals(expect, employeeService.get(id));
    }

    @Test
    void getByAccount() {
        create();

        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Петров Петр Сергеевич");
        expect.setPosition("Developer");
        expect.setAccount("petr");
        expect.setEmployeeStatus(EmployeeStatus.ACTIVE);

        Assertions.assertEquals(expect, employeeService.get("petr"));
    }

    @Test
    void find() {
        create();

        CreateEmployeeDto employee = new CreateEmployeeDto();
        employee.setLastName("Иванов");
        employee.setName("Петр");
        employee.setPatronymic("Павлович");
        employee.setPosition("Java Developer");
        employee.setAccount("ivi");
        employee.setEmail("iv@mail.ru");
        employee.setUsername("user1");
        employee.setPassword("123");
        employeeService.create(employee);

        EmployeeDto expect1 = new EmployeeDto();
        expect1.setDisplayName("Иванов Петр Павлович");
        expect1.setPosition("Java Developer");
        expect1.setAccount("ivi");
        expect1.setEmail("iv@mail.ru");
        expect1.setEmployeeStatus(EmployeeStatus.ACTIVE);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(expect1);
        Assertions.assertEquals(employeeDtoList, employeeService.find("mail"));
    }

    @Test
    void deleteById() {
        create();

        EmployeeDto expect = new EmployeeDto();
        expect.setDisplayName("Иванов Петр Павлович");
        expect.setPosition("Developer");
        expect.setAccount("ivi");
        expect.setEmail("iv@mail.ru");
        expect.setEmployeeStatus(EmployeeStatus.REMOVED);

        Assertions.assertEquals(expect, employeeService.delete(id));
    }
}