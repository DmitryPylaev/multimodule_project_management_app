package com.digdes.java2023.dto.crew;

import lombok.Data;

//Данные на вход по операции удаления
@Data
public class DeleteCrewDto {
    private long projectId;
    private long employeeId;
}
