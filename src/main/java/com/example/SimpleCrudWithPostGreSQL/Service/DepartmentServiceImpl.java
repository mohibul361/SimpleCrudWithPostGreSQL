package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.DepartmentDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import com.example.SimpleCrudWithPostGreSQL.ExceptionHandler.EmployeeNotFoundException;
import com.example.SimpleCrudWithPostGreSQL.Repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public ResponseEntity<String> saveDepartment(@RequestBody DepartmentDTO departmentDTO){


        Department department = Department.builder()
                .departmentName(departmentDTO.getDepartmentName())
                .build();
        departmentRepository.save(department);

        return ResponseEntity.ok("department added successfully!");
    }

    @Override
    public ResponseEntity<String> editDepartment(@RequestBody DepartmentDTO departmentDTO){
        Optional<Department> existingDepartment = departmentRepository.findById(departmentDTO.getDepartmentId());

        if(existingDepartment.isPresent()){
            existingDepartment.get().setDepartmentId(departmentDTO.getDepartmentId());
            existingDepartment.get().setDepartmentName(departmentDTO.getDepartmentName());

            departmentRepository.save(existingDepartment.get());
        }
        else{
            Department department = Department.builder()
                    .departmentId(departmentDTO.getDepartmentId())
                    .departmentName(departmentDTO.getDepartmentName())
                    .build();

            departmentRepository.save(department);
        }

        return ResponseEntity.ok("Department updated successfully!");

    }
    @Override
    public List<Department> getAllDepartment (){

        List<Department> departmentList = departmentRepository.findAll();

        if(departmentList.isEmpty()){
            log.info("No department is found! Please registered department!");

            throw new EmployeeNotFoundException("No department found! Please registered some departments!");
        }
        else{
            return departmentList;
        }

    }
}
