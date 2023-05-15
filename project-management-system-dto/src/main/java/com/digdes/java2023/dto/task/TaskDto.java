package com.digdes.java2023.dto.task;

import lombok.Data;
//Данные на выход по операциям поиска
@Data
public class TaskDto {
    private String name;
    private String description;
    private String employee;
    private int estimate;
    private String deadline;
    private String taskStatus;
    private String author;
    private String createDate;
    private String changeDate;
}
