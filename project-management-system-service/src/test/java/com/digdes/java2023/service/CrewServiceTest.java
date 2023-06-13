package com.digdes.java2023.service;

import com.digdes.java2023.dto.crew.CrewDto;
import com.digdes.java2023.dto.crew.EditCrewDto;
import com.digdes.java2023.dto.employee.CreateEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.dto.project.EditProjectDto;
import com.digdes.java2023.model.crew.ProjectRole;
import com.digdes.java2023.model.employee.EmployeeStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {ProjectService.class})
@EnableAutoConfiguration
@ComponentScan("com.digdes.java2023")
@EnableJpaRepositories("com.digdes.java2023.repository")
@EntityScan(basePackages = "com.digdes.java2023.model")
class CrewServiceTest extends BaseTest{

    @Autowired
    CrewService crewService;

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @Test
    void create() {
        EditCrewDto editCrewDto = new EditCrewDto();
        editCrewDto.setProjectRole(ProjectRole.QA);
        editCrewDto.setCode("code17");
        editCrewDto.setAccount("petr");

        CreateEmployeeDto employee = new CreateEmployeeDto();
        employee.setLastName("Петров");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Developer");
        employee.setAccount("petr");
        employee.setUsername("user3");
        employee.setPassword("111");
        employeeService.create(employee);

        EditProjectDto project = new EditProjectDto();
        project.setCode("code17");
        project.setName("top secret");
        project.setDescription("very top secret");
        projectService.create(project);

        EmployeeDto expectEmployee = new EmployeeDto();
        expectEmployee.setDisplayName("Петров Петр Сергеевич");
        expectEmployee.setPosition("Developer");
        expectEmployee.setAccount("petr");
        expectEmployee.setEmployeeStatus(EmployeeStatus.ACTIVE);

        CrewDto expect = new CrewDto();
        expect.setEmployee(expectEmployee);
        expect.setProjectRole(ProjectRole.QA);

        Assertions.assertEquals(expect, crewService.add(editCrewDto));
    }

    @Test
    void getByProject() {
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

        EditCrewDto editCrewDto = new EditCrewDto();
        editCrewDto.setProjectRole(ProjectRole.DEVELOPER);
        editCrewDto.setCode("code17");
        editCrewDto.setAccount("ivi");
        crewService.add(editCrewDto);

        EmployeeDto expectEmployee = new EmployeeDto();
        expectEmployee.setDisplayName("Петров Петр Сергеевич");
        expectEmployee.setPosition("Developer");
        expectEmployee.setAccount("petr");
        expectEmployee.setEmployeeStatus(EmployeeStatus.ACTIVE);

        EmployeeDto expectEmployee2 = new EmployeeDto();
        expectEmployee2.setDisplayName("Иванов Петр Павлович");
        expectEmployee2.setPosition("Java Developer");
        expectEmployee2.setAccount("ivi");
        expectEmployee2.setEmail("iv@mail.ru");
        expectEmployee2.setEmployeeStatus(EmployeeStatus.ACTIVE);

        CrewDto expect = new CrewDto();
        expect.setEmployee(expectEmployee);
        expect.setProjectRole(ProjectRole.QA);

        CrewDto expect2 = new CrewDto();
        expect2.setEmployee(expectEmployee2);
        expect2.setProjectRole(ProjectRole.DEVELOPER);

        List<CrewDto> expectList = new ArrayList<>();
        expectList.add(expect);
        expectList.add(expect2);

        Assertions.assertEquals(expectList, crewService.getByProject("code17"));
    }

    @Test
    void delete() {
        create();

        EmployeeDto expectEmployee = new EmployeeDto();
        expectEmployee.setDisplayName("Петров Петр Сергеевич");
        expectEmployee.setPosition("Developer");
        expectEmployee.setAccount("petr");
        expectEmployee.setEmployeeStatus(EmployeeStatus.ACTIVE);

        CrewDto expect = new CrewDto();
        expect.setEmployee(expectEmployee);
        expect.setProjectRole(ProjectRole.QA);

        Assertions.assertEquals(expect, crewService.delete("code17", "petr"));
    }
}