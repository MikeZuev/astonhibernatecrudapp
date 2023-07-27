package ru.mike.astoncrudapphibernate.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mike.astoncrudapphibernate.entities.Employee;
import ru.mike.astoncrudapphibernate.entities.Project;
import ru.mike.astoncrudapphibernate.service.ProjectServiceInterface;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectServiceInterface projectService;

    @Autowired
    public ProjectController(ProjectServiceInterface projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<String> addProject(@RequestBody Project project) {
        try {
            projectService.addProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body("Project added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding project.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProject(@PathVariable long id, @RequestBody Project updatedProject) {
        try {
            Project existingProject = projectService.getProjectById(id);
            if (existingProject == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found.");
            }

            updatedProject.setId(id);
            projectService.updateProject(updatedProject);
            return ResponseEntity.status(HttpStatus.OK).body("Project updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating project.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable long id) {
        try {
            Project existingProject = projectService.getProjectById(id);
            if (existingProject == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found.");
            }

            projectService.deleteProject(id);
            return ResponseEntity.status(HttpStatus.OK).body("Project deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting project.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        try {
            List<Project> projects = projectService.getAllProjects();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable long id) {
        try {
            Project project = projectService.getProjectById(id);
            if (project == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{projectName}/employees")
    public ResponseEntity<?> getEmployeesByProjectName(@PathVariable String projectName) {
        try {
            Set<Employee> employees = projectService.getEmployeesByProjectName(projectName);
            if (!employees.isEmpty()) {
                return ResponseEntity.ok(employees);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No employees found for the project.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while getting employees for the project.");
        }
    }
}
