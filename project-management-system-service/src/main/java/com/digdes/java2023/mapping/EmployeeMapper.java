package com.digdes.java2023.mapping;

import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.model.employee.Employee;

public class EmployeeMapper {
    public static Employee createEntity(EditEmployeeDto editEmployeeDto) {
        Employee employee = new Employee();
        employee.setLastName(editEmployeeDto.getLastName());
        employee.setName(editEmployeeDto.getName());
        employee.setPatronymic(editEmployeeDto.getPatronymic());
        employee.setPosition(editEmployeeDto.getPosition());
        employee.setAccount(editEmployeeDto.getAccount());
        employee.setEmail(editEmployeeDto.getEmail());
        employee.setEmployeeStatus(employee.getEmployeeStatus());
        return employee;
    }

    public static Employee editEntity(EditEmployeeDto editEmployeeDto, Employee employeeOld) {
        Employee employee = new Employee();
        if (editEmployeeDto.getLastName() != null) employee.setLastName(editEmployeeDto.getLastName());
        else employee.setLastName(employeeOld.getLastName());
        if (editEmployeeDto.getName() != null) employee.setName(editEmployeeDto.getName());
        else employee.setName(employeeOld.getName());
        if (editEmployeeDto.getPatronymic() != null) employee.setPatronymic(editEmployeeDto.getPatronymic());
        else employee.setPatronymic(employeeOld.getPatronymic());
        if (editEmployeeDto.getPosition() != null) employee.setPosition(editEmployeeDto.getPosition());
        else employee.setPosition(employeeOld.getPosition());
        if (editEmployeeDto.getAccount() != null) employee.setAccount(editEmployeeDto.getAccount());
        else employee.setAccount(employeeOld.getAccount());
        if (editEmployeeDto.getEmail() != null) employee.setEmail(editEmployeeDto.getEmail());
        else employee.setEmail(employeeOld.getEmail());
        return employee;
    }



    public static EmployeeDto mapFromEntity(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setDisplayName(employee.getLastName()
                + " "
                + employee.getName()
                + " "
                + employee.getPatronymic()
        );
        employee.setPosition(employee.getPosition());
        employee.setAccount(employee.getAccount());
        employee.setEmail(employee.getEmail());
        employee.setEmployeeStatus(employee.getEmployeeStatus());
        return employeeDto;
    }
}
