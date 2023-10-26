package com.example.SimpleCrudWithPostGreSQL.DTO;

import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long employeeId;

    @Size(min = 2, max = 15,
            message = "First name must be between 2 and 15 characters.")
    private String firstName;

    @Size(min = 2, max = 15,
            message = "Last name must be between 2 and 15 characters.")
    private String lastName;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "Please provide a valid email address.")
    private String email;

    private Department department;

    private Set<Long> projects;

}
