package com.digdes.java2023.repository.plain_java_repository;

import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.employee.EmployeeStatus;
import com.digdes.java2023.plain_java_repository.DataStorage;
import com.digdes.java2023.plain_java_repository.impl.DataStorageJDBC;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class DataStorageJDBCTest {
    DataStorage dataStorage = new DataStorageJDBC();

    @Test
    void create() {
        Employee employee = new Employee();
        employee.setLastName("Иванов");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Java Developer");
        employee.setAccount("ivi");
        employee.setEmail("iv@mail.ru");
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE.toString());
        dataStorage.create(employee);
    }

    @Test
    void update() {
        Employee employee = new Employee();
        employee.setPatronymic("Павлович");
        employee.setPosition("Senior Java Developer");
        employee.setAccount("ivi");
        dataStorage.update(employee);
    }

    @Test
    void getById() {
        Optional<Employee> employee = dataStorage.getById(6);
        System.out.println(employee);
    }

    @Test
    void getAll() {
        Employee employee = new Employee();
        employee.setLastName("Петров");
        employee.setName("Петр");
        employee.setPatronymic("Сергеевич");
        employee.setPosition("Developer");
        employee.setAccount("petr");
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE.toString());
        dataStorage.create(employee);

        List<Employee> employeeList = dataStorage.getAll();
    }

    @Test
    void deleteById() {
        dataStorage.deleteById(6);

        List<Employee> employeeList = dataStorage.getAll();

        dataStorage.deleteById(7);

        employeeList = dataStorage.getAll();
    }
}