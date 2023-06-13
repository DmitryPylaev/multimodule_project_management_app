package com.digdes.java2023.repository;

import com.digdes.java2023.model.crew.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrewRepository extends JpaRepository<ProjectAssignment, Long> {
    List<ProjectAssignment> findByProjectId(long project);
    Optional<ProjectAssignment> findByProjectIdAndEmployeeId(long project, long employee);
}
