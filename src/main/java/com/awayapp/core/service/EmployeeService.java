package com.awayapp.core.service;

import com.awayapp.core.controller.dto.EmployeeDTO;
import com.awayapp.core.domain.Employee;
import com.awayapp.core.repository.EmployeeRepository;
import com.awayapp.core.service.mapper.EmployeeMapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(final EmployeeRepository employeeRepository,
                           final EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public Optional<EmployeeDTO> findEmployeeById(final Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    public List<EmployeeDTO> findAllEmployees() {
        return employeeMapper.toDtos(employeeRepository.findAll());
    }

    public EmployeeDTO saveEmployee(final EmployeeDTO dto) {
        Employee employee = employeeMapper.toEntity(dto);

        if (!isValidEmail(employee)) {
            throw new RuntimeException("Please insert a valid Email address!");
        }

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public Long getVacationDaysAllowedAt(final Instant start, final Employee employee) {
        int year = ZonedDateTime.ofInstant(start, UTC).getYear();
        int daysInYear = Year.of(year).length();
        return DAYS.between(employee.getHireDate(), start) * (employee.getMaxVacationDays() / daysInYear);
    }

    private Boolean isValidEmail(final Employee employee) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(employee.getEmail());
    }

}
