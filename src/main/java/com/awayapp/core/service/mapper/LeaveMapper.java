package com.awayapp.core.service.mapper;

import com.awayapp.core.controller.dto.LeaveDTO;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class LeaveMapper extends AbstractMapper<Leave, LeaveDTO> {

    private final EmployeeService employeeService;

    public LeaveMapper(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Leave toEntity(final LeaveDTO dto) {
        if (dto == null) {
            return null;
        }

        Leave entity = new Leave();
        entity.setId(dto.getId());
        entity.setEmployee(employeeService.findEmployeeById(dto.getEmployeeId()));
        entity.setType(dto.getType());
        entity.setLeaveStart(Instant.parse(dto.getStart()));
        entity.setLeaveEnd(Instant.parse(dto.getEnd()));

        return entity;
    }

    @Override
    public LeaveDTO toDto(final Leave entity) {
        if (entity == null) {
            return null;
        }

        LeaveDTO dto = new LeaveDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setType(entity.getType());
        dto.setStart(entity.getLeaveStart().toString());
        dto.setEnd(entity.getLeaveEnd().toString());

        return dto;
    }
}
