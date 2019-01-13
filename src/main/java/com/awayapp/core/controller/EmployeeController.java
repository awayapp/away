package com.awayapp.core.controller;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/employees/")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.get(id);
    }
}
