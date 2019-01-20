package com.awayapp.core.controller;

import com.awayapp.core.controller.dto.EmployeeDTO;
import com.awayapp.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(EmployeeController.BASE_URL)
public class EmployeeController {

    static final String BASE_URL = "/api/employees";

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable final Long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping
    public EmployeeDTO saveEmployee(@RequestBody final EmployeeDTO employee) {
        return employeeService.saveEmployee(employee);

    }
}
