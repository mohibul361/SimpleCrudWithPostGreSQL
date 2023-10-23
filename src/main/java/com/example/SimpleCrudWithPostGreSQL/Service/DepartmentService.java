package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.DepartmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface DepartmentService {


    ResponseEntity<String> saveDepartment(DepartmentDTO departmentDTO);

}
