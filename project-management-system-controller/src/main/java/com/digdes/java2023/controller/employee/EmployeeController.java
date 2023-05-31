package com.digdes.java2023.controller.employee;

import com.digdes.java2023.dto.employee.CreateEmployeeDto;
import com.digdes.java2023.dto.employee.EditEmployeeDto;
import com.digdes.java2023.dto.employee.EmployeeDto;
import com.digdes.java2023.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
@Log4j2
@Tag(name = "EmployeeController", description = "Контроллер сотрудника")
public class EmployeeController {
    EmployeeService employeeService;

    @Operation(summary = "Внесение сотрудника")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto create(@RequestBody CreateEmployeeDto employeeDto) {
        log.info("В методе контроллера Employee::create");
        return employeeService.create(employeeDto);
    }

    @Operation(summary = "Изменение существующей записи о сотруднике")
    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto edit(@RequestBody EditEmployeeDto employeeDto) {
        log.info("В методе контроллера Employee::edit");
        return employeeService.edit(employeeDto);
    }

    @Operation(summary = "Получение профиля по идентефикатору")
    @GetMapping(value = "/get_by_id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto get(@PathVariable long id) {
        log.info("В методе контроллера Employee::getById");
        return employeeService.get(id);
    }

    @Operation(summary = "Получение профиля по учетной записи")
    @GetMapping(value = "/get_by_account/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto get(@PathVariable String account) {
        log.info("В методе контроллера Employee::getByAccount");
        return employeeService.get(account);
    }

    @Operation(summary = "Поиск сотрудника по текстовому значению, которое проверяется по атрибутам:" +
            " Фамилия, Имя, Отчество, учетной записи, адресу электронной почты" +
            " и только среди активных сотрудников")
    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> find(@RequestParam String input) {
        log.info("В методе контроллера Employee::find");
        return employeeService.find(input);
    }

    @Operation(summary = "Удаление сотрудника, сотрудник переводится в статус REMOVED")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto delete(@PathVariable long id) {
        log.info("В методе контроллера Employee::delete");
        return employeeService.delete(id);
    }
}
