package com.digdes.java2023.model.crew;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "project_assignment")
@Getter
@Setter
@RequiredArgsConstructor
public class ProjectAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_assignment_id_seq")
    private long id;

    @Column(name = "fk_project_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private long fk_project_id;

    @Column(name = "fk_employee_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private long fk_employee_id;

    @Column(name = "project_role")
    private String project_role;
}
