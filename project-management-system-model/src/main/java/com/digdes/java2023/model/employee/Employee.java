package com.digdes.java2023.model.employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@Getter
@Setter
@RequiredArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "position")
    private String position;

    @Column(name = "account")
    private String account;

    @Column(name = "email")
    private String email;

    @Column(name = "employee_status")
    private String employeeStatus;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
