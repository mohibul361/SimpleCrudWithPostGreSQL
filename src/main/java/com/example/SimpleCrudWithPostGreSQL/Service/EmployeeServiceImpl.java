package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.EmployeeDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import com.example.SimpleCrudWithPostGreSQL.Entity.Employee;
import com.example.SimpleCrudWithPostGreSQL.Entity.Project;
import com.example.SimpleCrudWithPostGreSQL.ExceptionHandler.EmployeeNotFoundException;
import com.example.SimpleCrudWithPostGreSQL.ExceptionHandler.InvalidEmployeeException;
import com.example.SimpleCrudWithPostGreSQL.ExceptionHandler.ResourceNotFoundException;
import com.example.SimpleCrudWithPostGreSQL.Repository.EmployeeRepository;
import com.example.SimpleCrudWithPostGreSQL.Repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ResponseEntity<String> saveEmployee( EmployeeDTO employeeDTO){

        try {
            String firstName = employeeDTO.getFirstName();
            String lastName = employeeDTO.getLastName();
            String email = employeeDTO.getEmail();
            Department department = employeeDTO.getDepartment();

            if (firstName == null || lastName == null || email == null || department == null) {
                return ResponseEntity.badRequest().body("The attribute values should not be null!");
            } else {

                Employee employee = Employee.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .department(department)
                        .build();

                Set<Long> strProject = employeeDTO.getProjects();

                Set<Project> projects = new HashSet<>();


                for (Long projectId : strProject) {
                    Project project = projectRepository.findProjectById(projectId);

                    if (project != null) {
                        projects.add(project);
                    } else {
                        return ResponseEntity.badRequest().body("Project with id " + projectId + " not found.");
                    }
                }

                employee.setProjects(projects);

                employeeRepository.save(employee);

                return ResponseEntity.ok("Employee created successfully!");
            }

        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Can not created employee!");
        }
    }

    @Override
    public ResponseEntity<String> updateEmployee(EmployeeDTO employeeDTO){

        Optional<Employee> employee = employeeRepository.findById(employeeDTO.getEmployeeId());

        if(employee.isPresent()){
            employee.get().setEmployeeId(employeeDTO.getEmployeeId());
            employee.get().setFirstName(employeeDTO.getFirstName());
            employee.get().setLastName(employeeDTO.getLastName());
            employee.get().setEmail(employeeDTO.getEmail());
            employee.get().setDepartment(employeeDTO.getDepartment());

            Set<Long> strProject = employeeDTO.getProjects();

            Set<Project> projects = new HashSet<>();

            for(Long projectId : strProject){
                Project project = projectRepository.findProjectById(projectId);

                if(project != null){
                    projects.add(project);
                }
                else{
                   return ResponseEntity.badRequest().body("Project with this " + projectId +  " is not found!");
                }
            }

            employeeRepository.save(employee.get());
        }
        else{
            return ResponseEntity.badRequest().body("Employee with this id " + employeeDTO.getEmployeeId() +" not found!");
        }

        return ResponseEntity.ok("Student updated successfully!");
    }

    @Override
    public List<Employee> getAllEmployee (){

        List<Employee> employeeList = employeeRepository.findAll();

        if(employeeList.isEmpty()){
            log.info("No employee is found! Please registered employees!");

            throw new EmployeeNotFoundException("No employees found! Please registered some employees!");
        }
        else{
            return employeeList;
        }

    }
    @Override
    public Employee getASingleEmployee(Long employeeId){

            if(employeeId == null || employeeId <= 0){
                throw new InvalidEmployeeException("Invalid employee ID. Please provide a valid id");
            }

            Optional<Employee> singleEmployee = employeeRepository.findById(employeeId);

            if(singleEmployee.isEmpty()){
                throw new EmployeeNotFoundException("Employee with id " +employeeId+ " not found!");
            }

            return singleEmployee.get();
    }

    @Override
    public ResponseEntity<String> deleteEmployee(Long employeeId){

        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);

        if(existingEmployee.isPresent()){
            existingEmployee.get().getProjects().clear();

            employeeRepository.delete(existingEmployee.get());
        }
        else{
            return ResponseEntity.badRequest().body("Employee with " + employeeId + " is not found!");
        }
        return ResponseEntity.ok("Employee deleted successfully");
    }


}
