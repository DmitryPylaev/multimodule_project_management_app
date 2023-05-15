package com.digdes.java2023.repository.employee;

import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.repository.BaseRepository;

public interface EmployeeRepository extends BaseRepository<Employee> {
    Employee getById(long id);
}
