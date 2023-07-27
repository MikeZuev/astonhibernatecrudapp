package ru.mike.astoncrudapphibernate.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mike.astoncrudapphibernate.entities.Employee;
import ru.mike.astoncrudapphibernate.entities.Project;
import ru.mike.astoncrudapphibernate.service.EmployeeServiceInterface;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceInterface employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return ResponseEntity.status((HttpStatus.CREATED))
                    .body("Employee was added.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error happened while trying to add employee.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id,
                                                 @RequestBody Employee updatedEmployee) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            if (existingEmployee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Employee not found");
            }

            updatedEmployee.setId(id);
            employeeService.updateEmployee(updatedEmployee);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Employee was updated");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during updating employee");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            if (existingEmployee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Employee not found.");
            }

            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Employee was deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during deleting employee");
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/byname/{name}")
    public ResponseEntity<?> getEmployeeByName(@PathVariable String name) {
        try {
            Employee employee = employeeService.getEmployeeByName(name);
            if (employee != null) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while getting employee by name.");
        }
    }

    @GetMapping("/byname/{name}/projects")
    public ResponseEntity<?> getProjectsByEmployeeName(@PathVariable String name) {
        try {
            Set<Project> projects = employeeService.getProjectsByEmployeeName(name);
            if (!projects.isEmpty()) {
                return ResponseEntity.ok(projects);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No projects found for the employee.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while getting projects of employee.");
        }
    }

    @GetMapping("/byposition/{positionName}")
    public ResponseEntity<List<Employee>> getEmployeeByPosition(@PathVariable
                                                                String positionName) {
        try {
            List<Employee> employees = employeeService.getEmployeeByPosition(positionName);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
