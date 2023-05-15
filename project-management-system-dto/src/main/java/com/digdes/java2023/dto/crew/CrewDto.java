package com.digdes.java2023.dto.crew;

import lombok.Data;

import java.util.Map;
//Данные на выход по операции "Получить всех участников проекта"
@Data
public class CrewDto {
    private Map<String, String> members;
}
