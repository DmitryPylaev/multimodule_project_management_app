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
        if (editEmployeeDto.getLastName() != null) employeeOld.setLastName(editEmployeeDto.getLastName());
        if (editEmployeeDto.getName() != null) employeeOld.setName(editEmployeeDto.getName());
        if (editEmployeeDto.getPatronymic() != null) employeeOld.setPatronymic(editEmployeeDto.getPatronymic());
        if (editEmployeeDto.getPosition() != null) employeeOld.setPosition(editEmployeeDto.getPosition());
        if (editEmployeeDto.getAccount() != null) employeeOld.setAccount(editEmployeeDto.getAccount());
        if (editEmployeeDto.getEmail() != null) employeeOld.setEmail(editEmployeeDto.getEmail());
        return employeeOld;
    }



    public static EmployeeDto mapFromEntity(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setDisplayName(employee.getLastName()
                + " "
                + employee.getName()
                + " "
                + employee.getPatronymic()
        );
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setAccount(employee.getAccount());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setEmployeeStatus(employee.getEmployeeStatus());
        return employeeDto;
    }
}
