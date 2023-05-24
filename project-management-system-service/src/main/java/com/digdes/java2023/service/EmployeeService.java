package com.digdes.java2023.service;

import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.mapping.EmployeeMapper;
import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.employee.EmployeeStatus;
import com.digdes.java2023.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Service
public class EmployeeService {
    private static EmployeeRepository employeeRepository;

    @Autowired
    private void setEmployeeRepository (EmployeeRepository repo) {
        employeeRepository = repo;
    }

    public static EmployeeDto create(EditEmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.createEntity(employeeDto);
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE.toString());
        employeeRepository.save(employee);
        return EmployeeMapper.mapFromEntity(employee);
    }

    public static EmployeeDto edit(EditEmployeeDto employeeDto) {
        Optional<Employee> employeeFromBase = employeeRepository.findByAccount(employeeDto.getAccount());
        if (employeeFromBase.isPresent()) {
            Employee employeeToSave = EmployeeMapper.editEntity(employeeDto, employeeFromBase.get());
            employeeRepository.save(employeeToSave);
            return EmployeeMapper.mapFromEntity(employeeToSave);
        }
        return new EmployeeDto();
    }

    public static EmployeeDto delete(long id) {
        Employee employee = employeeRepository.getReferenceById(id);
        employee.setEmployeeStatus(EmployeeStatus.REMOVED.toString());
        employeeRepository.save(employee);
        return EmployeeMapper.mapFromEntity(employee);
    }

    public static EmployeeDto get(long id) {
        return EmployeeMapper.mapFromEntity(employeeRepository.getReferenceById(id));
    }

    public static EmployeeDto get(String account) {
        return EmployeeMapper.mapFromEntity(employeeRepository.findByAccount(account).orElseThrow());
    }

    public static List<EmployeeDto> find(String input) {
        List<Employee> employeeList = employeeRepository.findAll().stream().filter(o-> !EmployeeStatus.valueOf(o.getEmployeeStatus()).equals(EmployeeStatus.REMOVED)).toList();
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
