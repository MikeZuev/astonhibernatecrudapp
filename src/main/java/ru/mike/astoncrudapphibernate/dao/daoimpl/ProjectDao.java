package ru.mike.astoncrudapphibernate.dao.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mike.astoncrudapphibernate.HibernateConfig;
import ru.mike.astoncrudapphibernate.entities.Employee;
import ru.mike.astoncrudapphibernate.entities.Project;
import ru.mike.astoncrudapphibernate.dao.ProjectDaoInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProjectDao implements ProjectDaoInterface {

    private final HibernateConfig hibernateConfig;

    @Autowired
    public ProjectDao(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Override
    public void addProject(Project project) {
        try(Session session = hibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(project);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateProject(Project updatedProject) {
        try(Session session = hibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(updatedProject);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteProject(long projectId) {
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Project project = session.get(Project.class, projectId);
            if(project != null) {
                session.remove(project);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            projects =  session.createQuery("FROM Project", Project.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Project getProjectById(long projectId) {
        Transaction transaction = null;
        Project project = null;
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            project = session.get(Project.class, projectId);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public Set<Employee> getEmployeesByProjectName(String projectName) {
        Set<Employee> employees = new HashSet<>();

        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            String hql = "SELECT e FROM Employee e " +
                    "JOIN e.projects p " +
                    "WHERE p.name = :projectName";

            List<Employee> employeeList = session.createQuery(hql, Employee.class)
                    .setParameter("projectName", projectName)
                    .getResultList();

            employees = new HashSet<>(employeeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

}
