package com.awayapp.core.service;

import com.awayapp.core.controller.dto.LeaveDTO;
import com.awayapp.core.domain.Leave;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {

    private final EmployeeService employeeService;

    public LeaveMapper(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Leave toEntity(final LeaveDTO dto) {
        if (dto == null) {
            return null;
        }

        Leave entity = new Leave();
        entity.setId(dto.getId());
        entity.setEmployee(employeeService.findEmployeeById(dto.getEmployeeId()));
        entity.setType(dto.getType());
//        entity.setStart(dto.getStart());//TODO George
//        entity.setEnd(dto.getEnd());//TODO George

        return entity;
    }

    public LeaveDTO toDto(final Leave entity) {
        if (entity == null) {
            return null;
        }

        LeaveDTO dto = new LeaveDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setType(entity.getType());
//        dto.setStart(entity.getStart());//TODO George
//        dto.setEnd(entity.getEnd());//TODO George

        return dto;
    }
}
