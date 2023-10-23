package com.example.SimpleCrudWithPostGreSQL.Controller;

import com.example.SimpleCrudWithPostGreSQL.DTO.EmployeeDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Employee;
import com.example.SimpleCrudWithPostGreSQL.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.saveEmployee(employeeDTO);
    }
}
