package com.digdes.java2023.repository;

import com.digdes.java2023.model.crew.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends JpaRepository<ProjectAssignment, Long> {
}
