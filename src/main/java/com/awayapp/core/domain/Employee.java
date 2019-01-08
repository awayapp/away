package com.awayapp.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    @ManyToMany
    @JoinTable(name = "EMPLOYEE_TEAM",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID"))
    private Set<Team> teams;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "HIRE_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Instant hireDate;

    @Column(name = "MAX_VACANTION_DAYS")
    private int maxVacantionDays;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getHireDate() {
        return hireDate;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public int getMaxVacantionDays() {
        return maxVacantionDays;
    }

    public void setMaxVacantionDays(int maxVacantionDays) {
        this.maxVacantionDays = maxVacantionDays;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", manager=" + manager +
                ", teams=" + teams +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", maxVacantionDays=" + maxVacantionDays +
                '}';
    }
}
