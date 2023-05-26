package com.digdes.java2023.plain_java_repository;

import com.digdes.java2023.model.employee.Employee;

@FunctionalInterface
public interface EmployeeElector<T> {
    boolean elect (Employee employee, T o1);
}
