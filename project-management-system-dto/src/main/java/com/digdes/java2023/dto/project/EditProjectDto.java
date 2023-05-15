package com.digdes.java2023.dto.project;

import lombok.Data;
//Данные на вход по операциям редактирования/создания
@Data
public class EditProjectDto {
    private String code;
    private String name;
    private String description;
}
