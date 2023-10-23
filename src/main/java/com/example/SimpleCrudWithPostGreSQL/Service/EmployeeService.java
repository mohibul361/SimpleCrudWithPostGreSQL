package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {
    ResponseEntity<String> saveEmployee( EmployeeDTO employeeDTO);
}
