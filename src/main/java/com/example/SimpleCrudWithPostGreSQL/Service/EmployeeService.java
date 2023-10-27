package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.EmployeeDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface EmployeeService {
    ResponseEntity<String> saveEmployee( EmployeeDTO employeeDTO);

    ResponseEntity<String> updateEmployee(EmployeeDTO employeeDTO);

    public List<EmployeeDTO> getAllEmployee();

    EmployeeDTO getASingleEmployee(Long employeeId);

    public ResponseEntity<String> deleteEmployee(Long employeeId);
}
