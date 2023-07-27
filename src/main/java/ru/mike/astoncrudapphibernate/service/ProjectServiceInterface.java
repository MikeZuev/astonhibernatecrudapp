package ru.mike.astoncrudapphibernate.service;


import ru.mike.astoncrudapphibernate.entities.Employee;
import ru.mike.astoncrudapphibernate.entities.Project;

import java.util.List;
import java.util.Set;

public interface ProjectServiceInterface {

    void addProject(Project project);

    void updateProject(Project updatedProject);

    void deleteProject(long projectId);

    List<Project> getAllProjects();

    Project getProjectById(long projectId);

    Set<Employee> getEmployeesByProjectName(String projectName);
}
