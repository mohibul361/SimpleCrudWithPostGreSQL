package com.example.SimpleCrudWithPostGreSQL.Controller;

import com.example.SimpleCrudWithPostGreSQL.DTO.ProjectDTO;
import com.example.SimpleCrudWithPostGreSQL.Repository.ProjectRepository;
import com.example.SimpleCrudWithPostGreSQL.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @PostMapping("/add")
    public ResponseEntity<String> saveProject(@RequestBody ProjectDTO projectDTO){
        return projectService.saveProject(projectDTO);
    }
}
