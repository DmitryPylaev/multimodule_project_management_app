package com.digdes.java2023.model.crew;

import com.digdes.java2023.model.employee.Employee;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Crew implements Serializable {
    private long projectId;
    private Map<Employee, ProjectRole> members;
}
