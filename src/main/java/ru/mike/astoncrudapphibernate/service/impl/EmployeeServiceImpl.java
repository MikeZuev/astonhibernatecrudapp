package ru.mike.astoncrudapphibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mike.astoncrudapphibernate.dao.EmployeeDaoInterface;
import ru.mike.astoncrudapphibernate.entities.Employee;
import ru.mike.astoncrudapphibernate.entities.Project;
import ru.mike.astoncrudapphibernate.service.EmployeeServiceInterface;

import java.util.List;
import java.util.Set;


@Service
public class EmployeeServiceImpl implements EmployeeServiceInterface {


    private final EmployeeDaoInterface employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDaoInterface employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee updatedEmployee) {
        employeeDao.updateEmployee(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Employee getEmployeeByName(String employeeName) {
        return employeeDao.getEmployeeByName(employeeName);
    }

    @Override
    public Set<Project> getProjectsThisEmployee(long employeeId) {
        return employeeDao.getProjectsThisEmployee(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String positionName) {
        return employeeDao.getEmployeeByPosition(positionName);
    }

    @Override
    public Set<Project> getProjectsByEmployeeName(String employeeName) {
        return employeeDao.getProjectsByEmployeeName(employeeName);
    }


}
