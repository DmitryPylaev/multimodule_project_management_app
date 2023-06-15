package com.digdes.java2023.service.filter;

import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.task.TaskStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskFilter {
    private String name;
    private TaskStatus taskStatus;
    private Employee employee;
    private Employee author;
    private LocalDate deadlineMin;
    private LocalDate deadlineMax;
    private LocalDateTime createDateMin;
    private LocalDateTime createDateMax;
}
