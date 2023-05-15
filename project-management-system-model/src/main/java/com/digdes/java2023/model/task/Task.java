package com.digdes.java2023.model.task;

import com.digdes.java2023.model.employee.Employee;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Task implements Serializable {
    private long id;
    private String name;
    private String description;
    private Employee employee;
    private int estimate;
    private LocalDate deadline;
    private TaskStatus taskStatus;
    private Employee author;
    private LocalDate createDate;
    private LocalDate changeDate;
}
