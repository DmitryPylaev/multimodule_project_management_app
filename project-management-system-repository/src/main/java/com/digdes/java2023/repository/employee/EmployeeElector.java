package com.digdes.java2023.repository.employee;

import com.digdes.java2023.model.employee.Employee;

@FunctionalInterface
public interface EmployeeElector<T> {
    boolean elect (Employee employee, T o1);
}
