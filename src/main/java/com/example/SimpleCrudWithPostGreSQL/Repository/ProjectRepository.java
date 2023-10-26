package com.example.SimpleCrudWithPostGreSQL.Repository;

import com.example.SimpleCrudWithPostGreSQL.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE p.projectId = :projectId")
    Project findProjectById(Long projectId);
}
