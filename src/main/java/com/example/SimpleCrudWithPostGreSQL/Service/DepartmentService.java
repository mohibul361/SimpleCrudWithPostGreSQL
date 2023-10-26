package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.DepartmentDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DepartmentService {


    ResponseEntity<String> saveDepartment(DepartmentDTO departmentDTO);

    ResponseEntity<String> editDepartment(DepartmentDTO departmentDTO);

    public List<Department> getAllDepartment();

}
