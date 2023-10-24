package com.example.SimpleCrudWithPostGreSQL.DTO;

import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private Department department;

    private Set<Long> projects;

}
