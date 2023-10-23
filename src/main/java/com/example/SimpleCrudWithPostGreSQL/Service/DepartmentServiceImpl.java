package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.Controller.DepartmentController;
import com.example.SimpleCrudWithPostGreSQL.DTO.DepartmentDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import com.example.SimpleCrudWithPostGreSQL.Entity.Employee;
import com.example.SimpleCrudWithPostGreSQL.Repository.DepartmentRepository;
import com.example.SimpleCrudWithPostGreSQL.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<String> saveDepartment(DepartmentDTO departmentDTO){
        Optional<Department> user = departmentRepository.findById(departmentDTO.getDepartmentId());

        if(user.isPresent()){

            user.get().setDepartmentName(departmentDTO.getDepartmentName());
            departmentRepository.save(user.get());
        }
        else{
            Department department = Department.builder()
                    .departmentName(departmentDTO.getDepartmentName())
                    .build();

            departmentRepository.save(department);
        }
        return ResponseEntity.ok("department added successfully!");
    }
}
