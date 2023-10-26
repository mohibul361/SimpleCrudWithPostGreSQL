package com.example.SimpleCrudWithPostGreSQL.Controller;

import com.example.SimpleCrudWithPostGreSQL.DTO.DepartmentDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import com.example.SimpleCrudWithPostGreSQL.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<String> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.saveDepartment(departmentDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> editDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.editDepartment(departmentDTO);
    }

    @GetMapping("/all")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

}
