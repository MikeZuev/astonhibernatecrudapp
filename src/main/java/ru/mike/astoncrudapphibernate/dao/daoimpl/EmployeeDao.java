package ru.mike.astoncrudapphibernate.dao.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mike.astoncrudapphibernate.HibernateConfig;
import ru.mike.astoncrudapphibernate.entities.Employee;
import ru.mike.astoncrudapphibernate.entities.Project;
import ru.mike.astoncrudapphibernate.dao.EmployeeDaoInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EmployeeDao implements EmployeeDaoInterface {

    private final HibernateConfig hibernateConfig;


    @Autowired
    public EmployeeDao (HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;

    }
    @Override
    public void addEmployee(Employee employee) {
        try(Session session = hibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateEmployee(Employee updatedEmployee) {
        try(Session session = hibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(updatedEmployee);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEmployee(long employeeId) {
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if(employee != null) {
                session.remove(employee);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = null;
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            String hqlQuery = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.projects";
            employees = session.createQuery(hqlQuery, Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }


    @Override
    public Employee getEmployeeById(long employeeId) {
       Transaction transaction = null;
       Employee employee = null;
       try (Session session = hibernateConfig.getSessionFactory().openSession()) {
           transaction = session.beginTransaction();
           employee = session.get(Employee.class, employeeId);
           transaction.commit();
       } catch (Exception e) {
           if(transaction != null) {
               transaction.rollback();
           }
           e.printStackTrace();
       }
       return employee;
    }

    @Override
    public Employee getEmployeeByName(String employeeName) {
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee WHERE name = :name", Employee.class)
                    .setParameter("name", employeeName)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Project> getProjectsThisEmployee(long employeeId) {
        try(Session session = hibernateConfig.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, employeeId);
            if(employee != null) {
                return employee.getProjects();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    @Override
    public List<Employee> getEmployeeByPosition(String positionName) {
        List<Employee> employees = new ArrayList<>();
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            String queryString = "SELECT e FROM Employee e JOIN e.position p WHERE p.name = :positionName";
            employees = session.createQuery(queryString, Employee.class)
                    .setParameter("positionName", positionName)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return employees;
    }

    @Override
    public Set<Project> getProjectsByEmployeeName(String employeeName) {
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            Employee employee = session.createQuery("SELECT e FROM Employee e WHERE e.name = :employeeName", Employee.class)
                    .setParameter("employeeName", employeeName)
                    .uniqueResult();

            if (employee != null) {
                return employee.getProjects();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HashSet<>();

    }
}
