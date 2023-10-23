package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.ProjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ProjectService {
    public ResponseEntity<String> saveProject(ProjectDTO projectDTO);

}
