package ru.mike.astoncrudapphibernate.entities;


import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="birthday", nullable = false)
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "salary")
    private BigDecimal salary;

    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();

    public Employee() {

    }

    public Employee(String name, Date birthday, Position position, BigDecimal salary) {
        this.name = name;
        this.birthday = birthday;
        this.position = position;
        this.salary = salary;
    }

    public Employee(String name, Date birthday, Position position,
                    BigDecimal salary, Set<Project> projects) {
        this.name = name;
        this.birthday = birthday;
        this.position = position;
        this.salary = salary;
        this.projects = projects;
    }

    public Employee(Long id, String name, Date birthday, Position position,
                    BigDecimal salary, Set<Project> projects) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.position = position;
        this.salary = salary;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(birthday, employee.birthday) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(salary, employee.salary);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday, position, salary);
    }
}
