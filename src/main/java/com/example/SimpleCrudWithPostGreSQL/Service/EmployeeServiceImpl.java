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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ResponseEntity<String> saveEmployee(EmployeeDTO employeeDTO){

        Optional<Employee> user = employeeRepository.findById(employeeDTO.getEmployeeId());

        List<Project> projectList = projectRepository.findAllByIdIn(employeeDTO.getProjectIds());

        if(projectList.size() > 0){
            projectList = new ArrayList<>(projectList);
        }

        if(user.isPresent()){
            user.get().setFirstName(employeeDTO.getFirstName());
            user.get().setLastName(employeeDTO.getLastName());
            user.get().setEmail(employeeDTO.getEmail());
            user.get().setDepartment(employeeDTO.getDepartment());
            user.get().setProjects((Set<Project>) projectList);
            employeeRepository.save(user.get());
        }
        else{

            Employee employee = Employee.builder()
                    .firstName(employeeDTO.getFirstName())
                    .lastName(employeeDTO.getLastName())
                    .email(employeeDTO.getEmail())
                    .department(employeeDTO.getDepartment())
                    .projects((Set<Project>) projectList)
                    .build();

            employeeRepository.save(employee);
        }

        return ResponseEntity.ok("Employee created successfully!");
    }

}
