package com.digdes.java2023.model.task;

import com.digdes.java2023.model.employee.Employee;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "project")
@Getter
@Setter
@RequiredArgsConstructor
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;

    @Column(name = "estimate")
    private int estimate;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_author_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee author;

    @Column(name = "createDate")
    private LocalDate createDate;

    @Column(name = "changeDate")
    private LocalDate changeDate;
}
