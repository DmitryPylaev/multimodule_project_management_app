package com.digdes.java2023.dto.task;

import lombok.Data;
//Данные на вход по операциям редактирования/создания
@Data
public class EditTaskDto {
    private String name;
    private String description;
    private String employee;
    private int estimate;
    private String deadline;
}
