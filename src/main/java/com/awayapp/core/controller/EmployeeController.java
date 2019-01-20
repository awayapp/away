package com.awayapp.core.controller;

import com.awayapp.core.controller.dto.EmployeeDTO;
import com.awayapp.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable final Long id) {
        Optional<EmployeeDTO> employee = employeeService.findEmployeeById(id);

        return employee
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public EmployeeDTO saveEmployee(@RequestBody final EmployeeDTO employee) {
        return employeeService.saveEmployee(employee);

    }
}
