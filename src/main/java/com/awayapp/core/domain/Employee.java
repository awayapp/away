package com.awayapp.core.domain;

import java.time.LocalDate;

public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate hireDate;
    private int maxVacantionDays;

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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getMaxVacantionDays() {
        return maxVacantionDays;
    }

    public void setMaxVacantionDays(int maxVacantionDays) {
        this.maxVacantionDays = maxVacantionDays;
    }
}
