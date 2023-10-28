package com.example.SimpleCrudWithPostGreSQL.Service;

import com.example.SimpleCrudWithPostGreSQL.DTO.EmployeeDTO;
import com.example.SimpleCrudWithPostGreSQL.DTO.ProjectDTO;
import com.example.SimpleCrudWithPostGreSQL.Entity.Department;
import com.example.SimpleCrudWithPostGreSQL.Entity.Employee;
import com.example.SimpleCrudWithPostGreSQL.Entity.Project;
import com.example.SimpleCrudWithPostGreSQL.ExceptionHandler.EmployeeNotFoundException;
import com.example.SimpleCrudWithPostGreSQL.ExceptionHandler.InvalidEmployeeException;
import com.example.SimpleCrudWithPostGreSQL.ExceptionHandler.MethodArgumentNotValidException;
import com.example.SimpleCrudWithPostGreSQL.ExceptionHandler.ResourceNotFoundException;
import com.example.SimpleCrudWithPostGreSQL.Repository.DepartmentRepository;
import com.example.SimpleCrudWithPostGreSQL.Repository.EmployeeRepository;
import com.example.SimpleCrudWithPostGreSQL.Repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Validated
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<String> saveEmployee( EmployeeDTO employeeDTO){

        try {
            String firstName = employeeDTO.getFirstName();
            String lastName = employeeDTO.getLastName();
            String email = employeeDTO.getEmail();
            Department department = employeeDTO.getDepartment();

//            if (firstName == null || lastName == null || email == null || department == null) {
//                return ResponseEntity.badRequest().body("The attribute values should not be null!");
//            } else {

            Employee employee = Employee.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .department(department)
                    .build();

            Set<Long> strProject = employeeDTO.getProjectIds();

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
//          }

        }

        catch (MethodArgumentNotValidException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
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

            Set<Long> strProject = employeeDTO.getProjectIds();

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

        return ResponseEntity.ok("Employee updated successfully!");
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {


        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            log.info("No employees found! Please register some employees.");
            throw new EmployeeNotFoundException("No employees found! Please register some employees.");
        }

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee, employeeDTO);

            Department department = employee.getDepartment();
            employeeDTO.setDepartment(department);

            Set<ProjectDTO> projectDTOs = new HashSet<>();
            for (Project project : employee.getProjects()) {
                ProjectDTO projectDTO = new ProjectDTO();
                BeanUtils.copyProperties(project, projectDTO);
                projectDTOs.add(projectDTO);
            }
            employeeDTO.setProjectDTOs(projectDTOs);

            Set<Long> projectIds = employee.getProjects().stream()
                    .map(Project::getProjectId)
                    .collect(Collectors.toSet());
            employeeDTO.setProjectIds(projectIds);

            employeeDTOList.add(employeeDTO);
        }

        return employeeDTOList;
    }

    @Override
    public EmployeeDTO getASingleEmployee(Long employeeId) {
        if (employeeId == null || employeeId <= 0) {
            throw new InvalidEmployeeException("Invalid employee ID. Please provide a valid ID.");
        }

        Optional<Employee> singleEmployee = employeeRepository.findById(employeeId);

        if (singleEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found!");
        }

        Employee employee = singleEmployee.get();

        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);

        Department department = employee.getDepartment();
        employeeDTO.setDepartment(department);

        Set<ProjectDTO> projectDTOs = new HashSet<>();
        for (Project project : employee.getProjects()) {
            ProjectDTO projectDTO = new ProjectDTO();
            BeanUtils.copyProperties(project, projectDTO);
            projectDTOs.add(projectDTO);
        }
        employeeDTO.setProjectDTOs(projectDTOs);

        Set<Long> projectIds = employee.getProjects().stream()
                .map(Project::getProjectId)
                .collect(Collectors.toSet());
        employeeDTO.setProjectIds(projectIds);

        return employeeDTO;
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
