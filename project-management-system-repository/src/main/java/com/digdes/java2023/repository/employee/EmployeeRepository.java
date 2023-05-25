package com.digdes.java2023.repository.employee;

import com.digdes.java2023.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByAccount(String account);
    Optional<Employee> findByUsername(String username);
}
