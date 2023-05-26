package com.digdes.java2023.service;

import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.employee.EmployeeStatus;
import com.digdes.java2023.repository.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(username, employee.getPassword(), Collections.emptyList());
    }

    @PostConstruct
    public void initAdmin() {
        Employee employee = new Employee();
        employee.setLastName("admin");
        employee.setName("admin");
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE.toString());
        employee.setUsername("root");
        employee.setPassword(passwordEncoder.encode("root"));
        Employee admin = employeeRepository.findByUsername("root")
                .orElse(employeeRepository.save(employee));
    }
}
