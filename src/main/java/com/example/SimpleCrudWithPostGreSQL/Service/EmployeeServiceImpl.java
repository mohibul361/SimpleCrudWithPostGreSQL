package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.EmployeeDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import com.example.SimpleCrudWithPostGreSQL.Entity.Employee;
import com.example.SimpleCrudWithPostGreSQL.Entity.Project;
import com.example.SimpleCrudWithPostGreSQL.Repository.DepartmentRepository;
import com.example.SimpleCrudWithPostGreSQL.Repository.EmployeeRepository;
import com.example.SimpleCrudWithPostGreSQL.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ResponseEntity<String> saveEmployee(EmployeeDTO employeeDTO){

        Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .build();

        Set<Long> strProject = employeeDTO.getProjects();

        Set<Project> projects = new HashSet<>();


        for(Long projectId : strProject){
            Project project = projectRepository.findProjectById(projectId);

            if(project != null){
                projects.add(project);
            }
            else{
                return ResponseEntity.badRequest().body("Project with id " + projectId + " not found.");
            }
        }

        employee.setProjects(projects);

        employeeRepository.save(employee);

        return ResponseEntity.ok("Employee created successfully!");
    }

}
