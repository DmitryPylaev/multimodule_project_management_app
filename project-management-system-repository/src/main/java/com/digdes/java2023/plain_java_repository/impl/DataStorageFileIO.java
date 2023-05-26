package com.digdes.java2023.plain_java_repository.impl;

import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.plain_java_repository.DataStorage;
import com.digdes.java2023.plain_java_repository.EmployeeElector;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStorageFileIO implements DataStorage {
    private final String path = "C:\\YandexDisk\\employee.bin";

    @Override
    public void create(Employee employee) {
        var employeeList = findEmployee("addAll", (t1, o1) -> true);
        employeeList.add(employee);
        try (var objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(path), StandardOpenOption.CREATE))) {
            for (var listItem : employeeList) objectOutputStream.writeObject(listItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee changes) {
        Employee employee = Optional.ofNullable(findEmployee(changes.getAccount(), (employee1, o1) -> employee1.getAccount().equals(o1)).get(0)).orElseThrow();
        if (changes.getLastName() != null) employee.setLastName(changes.getLastName());
        if (changes.getName() != null) employee.setName(changes.getName());
        if (changes.getPatronymic() != null) employee.setPatronymic(changes.getPatronymic());
        if (changes.getPosition() != null) employee.setPosition(changes.getPosition());
        if (changes.getAccount() != null) employee.setAccount(changes.getAccount());
        if (changes.getEmail() != null) employee.setEmail(changes.getEmail());
        deleteById(employee.getId());
        create(employee);
    }

    @Override
    public Optional<Employee> getById(long id) {
        return Optional.ofNullable(findEmployee(id, (employee, o1) -> employee.getId()==o1).get(0));
    }

    @Override
    public List<Employee> getAll() {
        return findEmployee("addAll", (t1, o1) -> true);
    }

    @Override
    public void deleteById(long id) {
        List<Employee> employeeList = findEmployee(id, (employee, o1) -> employee.getId() != o1);
        new File(path).delete();
        for (var listItem : employeeList) create(listItem);
    }

    //Метод поиска по нужному совпадению
    private <T> List<Employee> findEmployee(T target, EmployeeElector<T> employeeElector) {
        var result = new ArrayList<Employee>();
        if (Files.exists(Paths.get(path))) {
            try (var fileInputStream = new FileInputStream(path)) {
                var objectInputStream = new ObjectInputStream(fileInputStream);
                while (fileInputStream.available() != 0) {
                    var employee = (Employee) objectInputStream.readObject();
                    if (employeeElector.elect(employee, target)) {
                        result.add(employee);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
