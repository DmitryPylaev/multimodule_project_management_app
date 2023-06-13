package com.digdes.java2023.dto.task;

import com.digdes.java2023.model.task.TaskStatus;
import lombok.Data;
//Данные на выход по операциям поиска
@Data
public class TaskDto {
    private String name;
    private String description;
    private String employee;
    private int estimate;
    private String deadline;
    private TaskStatus taskStatus;
    private String author;
    private String createDate;
    private String changeDate;
}
