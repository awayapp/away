package com.awayapp.core.service;

import com.awayapp.core.controller.dto.LeaveDTO;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.service.mapper.LeaveMapper;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class MaxVacationDaysValdidationService {

    public MaxVacationDaysValdidationService(LeaveMapper leaveMapper) {
        this.leaveMapper = leaveMapper;
    }

    private final LeaveMapper leaveMapper;

    public Boolean isLeavePossible(LeaveDTO leaveDTO) {
        return true;
//        Leave leave = leaveMapper.toEntity(leaveDTO);
//
//        Integer soldVacationDays = leave.getEmployee().getMaxVacationDays();
//
//        Duration duration = Duration.between(leave.getLeaveStart(), leave.getLeaveEnd()).minusDays(soldVacationDays);
//        return !duration.isNegative();
    }

/*
    public Integer soldVacationDays(LeaveDTO leaveDTO) {
        Leave leave = leaveMapper.toEntity(leaveDTO);

        soldVacationDays(leaveDTO) =
        Duration duration = Duration.between(leave.getLeaveStart(), leave.getLeaveEnd()).minusDays(leave.getEmployee().getMaxVacationDays());

        duration.
    }
*/
}
