package com.digdes.java2023.model.project;

import lombok.Data;

import java.io.Serializable;

@Data
public class Project implements Serializable {
    private long id;
    private String code;
    private String name;
    private String description;
    private ProjectStatus projectStatus;
}
