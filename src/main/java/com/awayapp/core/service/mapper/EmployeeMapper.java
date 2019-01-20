package com.awayapp.core.service.mapper;

import com.awayapp.core.controller.dto.EmployeeDTO;
import com.awayapp.core.domain.Employee;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDTO> {

    @Override
    public Employee toEntity(final EmployeeDTO dto) {
        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setHireDate(Instant.parse(dto.getHireDate()));
        employee.setMaxVacationDays(dto.getMaxVacationDays());

        return employee;
    }

    @Override
    public EmployeeDTO toDto(final Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(entity.getId().toString());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setHireDate(entity.getHireDate().toString());
        dto.setMaxVacationDays(entity.getMaxVacationDays());

        return dto;
    }

//    public List<EmployeeDTO> toDtoList(final List<Employee> entities) {
//        List<EmployeeDTO> dtos = new ArrayList<>();
//
//        for (Employee entity : entities) {
//            dtos.add(toDto(entity));
//        }
//
//        return dtos;
//    }
//
//    public List<Employee> toEntities(final List<EmployeeDTO> dtos) {
//        List<Employee> entities = new ArrayList<>();
//
//        for (EmployeeDTO dto : dtos) {
//            entities.add(toEntity(dto));
//        }
//
//        return entities;
//    }
}
