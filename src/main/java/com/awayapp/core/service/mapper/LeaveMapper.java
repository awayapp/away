package com.awayapp.core.service.mapper;

import com.awayapp.core.controller.dto.LeaveDTO;
import com.awayapp.core.domain.Employee;
import com.awayapp.core.domain.Leave;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class LeaveMapper extends AbstractMapper<Leave, LeaveDTO> {

    @Override
    public Leave toEntity(final LeaveDTO dto) {
        Leave entity = new Leave();
        entity.setId(dto.getId());
        entity.setEmployee(new Employee(dto.getEmployeeId()));
//        entity.setEmployee(employeeMapper.toDto(employeeService.findEmployeeById(dto.getEmployeeId())));
        entity.setType(dto.getType());
        entity.setLeaveStart(Instant.parse(dto.getStart()));
        entity.setLeaveEnd(Instant.parse(dto.getEnd()));

        return entity;
    }

    @Override
    public LeaveDTO toDto(final Leave entity) {
        LeaveDTO dto = new LeaveDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setType(entity.getType());
        dto.setStart(entity.getLeaveStart().toString());
        dto.setEnd(entity.getLeaveEnd().toString());

        return dto;
    }
}
