package com.digdes.java2023.service;

import com.digdes.java2023.dto.employee.CreateEmployeeDto;
import com.digdes.java2023.dto.task.EditTaskDto;
import com.digdes.java2023.dto.task.FindTaskDto;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

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

        TaskDto taskDto = taskService.create(editTaskDto);

        Assertions.assertEquals("task1", taskDto.getName());
        Assertions.assertEquals("important", taskDto.getDescription());
        Assertions.assertEquals(5, taskDto.getEstimate());
        Assertions.assertEquals("2023-08-16", taskDto.getDeadline());
        Assertions.assertEquals("petr", taskDto.getEmployee());
        Assertions.assertEquals(TaskStatus.NEW, taskDto.getTaskStatus());
    }

    @Test
    void edit() {
        create();

        EditTaskDto editTaskDto = new EditTaskDto();
        editTaskDto.setName("task1");
        editTaskDto.setDescription("prioritized");
        editTaskDto.setEstimate(4);

        TaskDto taskDto = taskService.edit(editTaskDto);

        Assertions.assertEquals("task1", taskDto.getName());
        Assertions.assertEquals("prioritized", taskDto.getDescription());
        Assertions.assertEquals(4, taskDto.getEstimate());
        Assertions.assertEquals("2023-08-16", taskDto.getDeadline());
        Assertions.assertEquals("petr", taskDto.getEmployee());
        Assertions.assertEquals(TaskStatus.NEW, taskDto.getTaskStatus());
    }

    @Test
    void shift() {
        create();

        TaskDto taskDto = taskService.shiftStatus("task1");

        Assertions.assertEquals("task1", taskDto.getName());
        Assertions.assertEquals("important", taskDto.getDescription());
        Assertions.assertEquals(5, taskDto.getEstimate());
        Assertions.assertEquals("2023-08-16", taskDto.getDeadline());
        Assertions.assertEquals("petr", taskDto.getEmployee());
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, taskDto.getTaskStatus());
    }


    @Test
    void find() {
        create();

        EditTaskDto editTaskDto = new EditTaskDto();
        editTaskDto.setName("task1");

        TaskDto expectedTask = taskService.edit(editTaskDto);

        FindTaskDto dto = new FindTaskDto();
        dto.setName("task1");
        dto.setTaskStatus(TaskStatus.NEW);
        dto.setEmployee("petr");
        dto.setAuthor("petr");
        dto.setDeadlineMin(LocalDate.parse("2023-07-16"));
        dto.setDeadlineMax(LocalDate.parse("2023-08-17"));
        dto.setCreateDateMin(LocalDateTime.parse("2023-06-14T11:30"));
        dto.setCreateDateMax(LocalDateTime.parse("2023-06-20T11:32"));

        TaskDto taskDto = taskService.find(dto).get(0);

        Assertions.assertEquals(expectedTask.getName(), taskDto.getName());
        Assertions.assertEquals(expectedTask.getTaskStatus(), taskDto.getTaskStatus());
        Assertions.assertEquals(expectedTask.getEmployee(), taskDto.getEmployee());
        Assertions.assertEquals(expectedTask.getAuthor(), taskDto.getAuthor());
        Assertions.assertEquals(expectedTask.getDeadline(), taskDto.getDeadline());
        Assertions.assertEquals(expectedTask.getCreateDate().substring(0,16), taskDto.getCreateDate().substring(0,16));
    }

}