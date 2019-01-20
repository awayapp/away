package com.awayapp.core.controller.dto;

public class EmployeeDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String hireDate;
    private Integer maxVacationDays;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getMaxVacationDays() {
        return maxVacationDays;
    }

    public void setMaxVacationDays(Integer maxVacationDays) {
        this.maxVacationDays = maxVacationDays;
    }
}
