package ru.mike.astoncrudapphibernate.service.impl;

import org.springframework.stereotype.Service;
import ru.mike.astoncrudapphibernate.dao.ProjectDaoInterface;
import ru.mike.astoncrudapphibernate.entities.Employee;
import ru.mike.astoncrudapphibernate.entities.Project;
import ru.mike.astoncrudapphibernate.service.ProjectServiceInterface;

import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectServiceInterface {

    private final ProjectDaoInterface projectDao;

    public ProjectServiceImpl(ProjectDaoInterface projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public void addProject(Project project) {
        projectDao.addProject(project);
    }

    @Override
    public void updateProject(Project updatedProject) {
        projectDao.updateProject(updatedProject);

    }

    @Override
    public void deleteProject(long projectId) {
        projectDao.deleteProject(projectId);

    }

    @Override
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    @Override
    public Project getProjectById(long projectId) {
        return projectDao.getProjectById(projectId);
    }

    @Override
    public Set<Employee> getEmployeesByProjectName(String projectName) {
        return projectDao.getEmployeesByProjectName(projectName);
    }
}
