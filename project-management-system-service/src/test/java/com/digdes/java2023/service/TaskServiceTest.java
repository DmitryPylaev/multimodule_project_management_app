package com.digdes.java2023.service;

import com.digdes.java2023.dto.employee.CreateEmployeeDto;
import com.digdes.java2023.dto.task.EditTaskDto;
import com.digdes.java2023.dto.task.TaskDto;
import com.digdes.java2023.model.task.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest(classes = {EmployeeService.class})
@EnableAutoConfiguration
@ComponentScan("com.digdes.java2023")
@EnableJpaRepositories("com.digdes.java2023.repository")
@EntityScan(basePackages = "com.digdes.java2023.model")
class TaskServiceTest extends BaseTest{

    @Autowired
    EmployeeService employeeService;

    @Autowired
    TaskService taskService;

    @Test
    void create() {
        EditTaskDto editTaskDto = new EditTaskDto();
        editTaskDto.setName("task1");
        editTaskDto.setDescription("important");
        editTaskDto.setEstimate(5);
        editTaskDto.setDeadline("2023-08-16");

        CreateEmployeeDto employee = new CreateEmployeeDto();
        employee.setLastName("Петров");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Developer");
        employee.setAccount("petr");
        employee.setUsername("user3");
        employee.setPassword("111");
        employeeService.create(employee);

        editTaskDto.setEmployee("petr");

        TaskDto expectedTask = taskService.create(editTaskDto);

        Assertions.assertEquals("task1", expectedTask.getName());
        Assertions.assertEquals("important", expectedTask.getDescription());
        Assertions.assertEquals(5, expectedTask.getEstimate());
        Assertions.assertEquals("2023-08-16", expectedTask.getDeadline());
        Assertions.assertEquals("petr", expectedTask.getEmployee());
        Assertions.assertEquals(TaskStatus.NEW, expectedTask.getTaskStatus());
    }

    @Test
    void edit() {
        create();

        EditTaskDto editTaskDto = new EditTaskDto();
        editTaskDto.setName("task1");
        editTaskDto.setDescription("prioritized");
        editTaskDto.setEstimate(4);

        TaskDto expectedTask = taskService.edit(editTaskDto);

        Assertions.assertEquals("task1", expectedTask.getName());
        Assertions.assertEquals("prioritized", expectedTask.getDescription());
        Assertions.assertEquals(4, expectedTask.getEstimate());
        Assertions.assertEquals("2023-08-16", expectedTask.getDeadline());
        Assertions.assertEquals("petr", expectedTask.getEmployee());
        Assertions.assertEquals(TaskStatus.NEW, expectedTask.getTaskStatus());
    }

    @Test
    void shift() {
        create();

        TaskDto expectedTask = taskService.shiftStatus("task1");

        Assertions.assertEquals("task1", expectedTask.getName());
        Assertions.assertEquals("important", expectedTask.getDescription());
        Assertions.assertEquals(5, expectedTask.getEstimate());
        Assertions.assertEquals("2023-08-16", expectedTask.getDeadline());
        Assertions.assertEquals("petr", expectedTask.getEmployee());
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, expectedTask.getTaskStatus());
    }


//    @Test
//    void find() {
//        create();
//
//        CreateEmployeeDto employee = new CreateEmployeeDto();
//        employee.setLastName("Иванов");
//        employee.setName("Петр");
//        employee.setPatronymic("Павлович");
//        employee.setPosition("Java Developer");
//        employee.setAccount("ivi");
//        employee.setEmail("iv@mail.ru");
//        employee.setUsername("user1");
//        employee.setPassword("123");
//        employeeService.create(employee);
//
//        EmployeeDto expect1 = new EmployeeDto();
//        expect1.setDisplayName("Иванов Петр Павлович");
//        expect1.setPosition("Java Developer");
//        expect1.setAccount("ivi");
//        expect1.setEmail("iv@mail.ru");
//        expect1.setEmployeeStatus(EmployeeStatus.ACTIVE);
//
//        List<EmployeeDto> employeeDtoList = new ArrayList<>();
//        employeeDtoList.add(expect1);
//        Assertions.assertEquals(employeeDtoList, employeeService.find("mail"));
//    }

}