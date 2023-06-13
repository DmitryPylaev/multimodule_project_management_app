package com.digdes.java2023.dto.crew;

import com.digdes.java2023.model.crew.ProjectRole;
import lombok.Data;
//Данные на вход по операции добавления
@Data
public class EditCrewDto {
    private String code;
    private String account;
    private ProjectRole projectRole;
}
