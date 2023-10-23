package com.example.SimpleCrudWithPostGreSQL.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    @ManyToOne
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private Set<Project> projects;


}
