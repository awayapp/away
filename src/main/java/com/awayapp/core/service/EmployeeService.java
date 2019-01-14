package com.awayapp.core.service;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
