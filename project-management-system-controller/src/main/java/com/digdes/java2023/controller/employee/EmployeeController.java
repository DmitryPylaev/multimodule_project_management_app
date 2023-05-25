package com.digdes.java2023.controller.employee;

import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    EmployeeService employeeService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto create(@RequestBody EditEmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto edit(@RequestBody EditEmployeeDto employeeDto) {
        return employeeService.edit(employeeDto);
    }

    @GetMapping(value = "/get_by_id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto get(@PathVariable long id) {
        return employeeService.get(id);
    }

    @GetMapping(value = "/get_by_account/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto get(@PathVariable String account) {
        return employeeService.get(account);
    }

    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> find(@RequestParam String input) {
        return employeeService.find(input);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto delete(@PathVariable long id) {
        return employeeService.delete(id);
    }
}
