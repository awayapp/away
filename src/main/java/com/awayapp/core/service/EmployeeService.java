package com.awayapp.core.service;

import com.awayapp.core.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private Map<Long, Employee> database = new HashMap<>();

    public void save(final Employee employee) {
        database.put(employee.getId(), employee);
    }

    public Employee get(final Long id) {
        return database.get(id);
    }
}
