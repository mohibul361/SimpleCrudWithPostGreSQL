package com.example.SimpleCrudWithPostGreSQL.DTO;

import com.example.SimpleCrudWithPostGreSQL.Entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {


    private Long departmentId;

    private String departmentName;

}
