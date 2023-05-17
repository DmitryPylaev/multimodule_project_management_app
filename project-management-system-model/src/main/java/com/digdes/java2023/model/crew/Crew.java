package com.digdes.java2023.model.crew;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Crew implements Serializable {
    private long projectId;
    private Map<Integer, Integer> members;
}
