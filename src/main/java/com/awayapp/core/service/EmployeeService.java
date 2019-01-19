package com.awayapp.core.service;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.repository.EmployeeRepository;
import org.apache.commons.validator.routines.EmailValidator;
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
        if (!isValidEmail(employee)) throw new RuntimeException("Please insert a valid Email address!");
        return employeeRepository.save(employee);
    }

    private Boolean isValidEmail(Employee employee) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(employee.getEmail());
    }

}
