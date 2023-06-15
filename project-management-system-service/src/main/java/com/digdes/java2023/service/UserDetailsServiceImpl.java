package com.digdes.java2023.service;

import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.employee.EmployeeStatus;
import com.digdes.java2023.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsernameAndEmployeeStatus(username, EmployeeStatus.ACTIVE)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(username, employee.getPassword(), Collections.emptyList());
    }

    @PostConstruct
    public void initAdmin() {
        Employee employee = new Employee();
        employee.setLastName("admin");
        employee.setName("admin");
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);
        employee.setUsername("root");
        employee.setAccount("admin");
        employee.setPassword(passwordEncoder.encode("root"));
        Optional<Employee> admin = employeeRepository.findByUsername("root");
        if (admin.isEmpty()) {
            employeeRepository.save(employee);
        }
    }
}
