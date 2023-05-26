package com.digdes.java2023.model.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name")
    private String description;

    @Column(name = "name")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_employee_id")
    private long fk_employee_id;

    @Column(name = "name")
    private int estimate;

    @Column(name = "name")
    private LocalDate deadline;

    @Column(name = "name")
    private TaskStatus taskStatus;

    @Column(name = "name")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_author_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private long fk_author_id;

    @Column(name = "name")
    private LocalDate createDate;

    @Column(name = "name")
    private LocalDate changeDate;
}
