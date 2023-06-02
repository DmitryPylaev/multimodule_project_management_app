package com.digdes.java2023.repository;

import com.digdes.java2023.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByAccount(String account);
    Optional<Employee> findByUsername(String username);

    List<Employee> findByEmployeeStatusAndLastNameContainingOrNameContainingOrPatronymicContainingOrAccountContainingOrEmailContaining(
            String employeeStatus,
            String infixLastname,
            String infixName,
            String infixPatronymic,
            String infixAccount,
            String infixEmail);

    Page<Employee> findByEmployeeStatusAndLastNameContainingOrNameContainingOrPatronymicContainingOrAccountContainingOrEmailContaining(
            String employeeStatus,
            String infixLastname,
            String infixName,
            String infixPatronymic,
            String infixAccount,
            String infixEmail,
            Pageable pageable);
}
