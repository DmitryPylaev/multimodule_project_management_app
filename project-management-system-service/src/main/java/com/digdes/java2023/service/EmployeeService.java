package com.digdes.java2023.service;

import com.digdes.java2023.dto.employee.CreateEmployeeDto;
import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.mapping.EmployeeMapper;
import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.employee.EmployeeStatus;
import com.digdes.java2023.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    public EmployeeDto create(CreateEmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.createEntity(employeeDto);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE.toString());
        employeeRepository.save(employee);
        return EmployeeMapper.mapFromEntity(employee);
    }

    public EmployeeDto edit(EditEmployeeDto employeeDto) {
        Optional<Employee> employeeFromBase = employeeRepository.findByAccount(employeeDto.getAccount());
        if (employeeFromBase.isPresent()) {
            Employee employeeToSave = EmployeeMapper.editEntity(employeeDto, employeeFromBase.get());
            employeeRepository.save(employeeToSave);
            return EmployeeMapper.mapFromEntity(employeeToSave);
        }
        return new EmployeeDto();
    }

    public EmployeeDto delete(long id) {
        Employee employee = employeeRepository.getReferenceById(id);
        employee.setEmployeeStatus(EmployeeStatus.REMOVED.toString());
        employeeRepository.save(employee);
        return EmployeeMapper.mapFromEntity(employee);
    }

    public EmployeeDto get(long id) {
        return EmployeeMapper.mapFromEntity(employeeRepository.getReferenceById(id));
    }

    public EmployeeDto get(String account) {
        return EmployeeMapper.mapFromEntity(employeeRepository.findByAccount(account).orElseThrow());
    }

    public List<EmployeeDto> find(String input) {
        List<Employee> employeeList = employeeRepository.findByEmployeeStatusAndLastNameContainingOrNameContainingOrPatronymicContainingOrAccountContainingOrEmailContaining(
                "ACTIVE",
                input,
                input,
                input,
                input,
                input);
        List<EmployeeDto> result = new ArrayList<>();
        for (Employee employee : employeeList) {
            StringJoiner joiner = new StringJoiner(" ");
            joiner.add(employee.getLastName());
            joiner.add(employee.getName());
            joiner.add(employee.getPatronymic());
            joiner.add(employee.getAccount());
            joiner.add(employee.getEmail());
            if (joiner.toString().contains(input)) result.add(EmployeeMapper.mapFromEntity(employee));
        }
        return result;
    }

}
