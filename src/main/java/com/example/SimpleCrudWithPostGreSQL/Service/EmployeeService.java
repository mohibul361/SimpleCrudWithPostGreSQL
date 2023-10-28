package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {
    ResponseEntity<String> saveEmployee(EmployeeDTO employeeDTO);

    ResponseEntity<String> updateEmployee(EmployeeDTO employeeDTO);

    public List<EmployeeDTO> getAllEmployee();

    EmployeeDTO getASingleEmployee(Long employeeId);

    public ResponseEntity<String> deleteEmployee(Long employeeId);
}
