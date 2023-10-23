package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.ProjectDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Project;
import com.example.SimpleCrudWithPostGreSQL.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ResponseEntity<String> saveProject(ProjectDTO projectDTO){
        Optional<Project> user = projectRepository.findById(projectDTO.getProjectId());

        if(user.isPresent()){
            user.get().setProjectName(projectDTO.getProjectName());

            projectRepository.save(user.get());
        }
        else{
            Project project = Project.builder()
                    .projectName(projectDTO.getProjectName())
                    .build();
            projectRepository.save(project);
        }

        return ResponseEntity.ok("Project created successfully!");
    }

}
