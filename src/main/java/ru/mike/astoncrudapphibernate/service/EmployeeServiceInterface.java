package ru.mike.astoncrudapphibernate.service;

import ru.mike.astoncrudapphibernate.entities.Employee;
import ru.mike.astoncrudapphibernate.entities.Project;

import java.util.List;
import java.util.Set;

public interface EmployeeServiceInterface {

    void addEmployee(Employee employee);

    void updateEmployee(Employee updatedEmployee);

    void deleteEmployee(long employeeId);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long employeeId);

    Employee getEmployeeByName(String employeeName);

    Set<Project> getProjectsThisEmployee(long employeeId);

    List<Employee> getEmployeeByPosition(String positionName);

    Set<Project> getProjectsByEmployeeName(String employeeName);
}
