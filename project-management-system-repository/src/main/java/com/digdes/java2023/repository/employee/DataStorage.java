package com.digdes.java2023.repository.employee;

import com.digdes.java2023.model.employee.Employee;

import java.util.List;
import java.util.Optional;

public interface DataStorage {
    void create(Employee employee);
    void update(Employee employee);
    Optional<Employee> getById(long id);
    List<Employee> getAll();
    void deleteById(long id);
}
