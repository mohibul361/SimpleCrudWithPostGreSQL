package com.example.SimpleCrudWithPostGreSQL.Repository;

import com.example.SimpleCrudWithPostGreSQL.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
List<Project> findAllByIdIn(List<Integer> projectId);
}
