package com.digdes.java2023.repository;

import com.digdes.java2023.model.project.Project;
import com.digdes.java2023.model.project.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByCode(String code);

    @Query(value = "SELECT p FROM Project p WHERE p.projectStatus IN :statuses AND (p.code LIKE %:input% OR p.name LIKE %:input%)")
    List<Project> find(
            @Param("statuses") Collection<ProjectStatus> statuses,
            @Param("input") String input);

}
