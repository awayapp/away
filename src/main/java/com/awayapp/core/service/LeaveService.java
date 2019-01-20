package com.awayapp.core.service;

import com.awayapp.core.controller.dto.LeaveDTO;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.repository.LeaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;
    private final MaxVacationDaysValdidationService maxVacationDaysValdidationService;

    public LeaveService(final LeaveRepository leaveRepository, final LeaveMapper leaveMapper, final MaxVacationDaysValdidationService maxVacationDaysValdidationService) {
        this.leaveRepository = leaveRepository;
        this.leaveMapper = leaveMapper;
        this.maxVacationDaysValdidationService = maxVacationDaysValdidationService;
    }


    @Transactional
    public LeaveDTO saveLeave(LeaveDTO leaveDTO) {
        Leave leave = leaveMapper.toEntity(leaveDTO);
        if (!isValidStartEnd(leave))
            throw new RuntimeException("The end date of the leave must be at least equal to the start date!");
        if (!maxVacationDaysValdidationService.isLeavePossible(leaveDTO))
            throw new RuntimeException("You don't have enough vacation days!");
        return leaveMapper.toDto(leaveRepository.save(leave));
    }

    public List<Leave> findAllLeaves() {
        return leaveRepository.findAll();
    }

    private Boolean isValidStartEnd(Leave leave) {
        return (!Duration.between(leave.getLeaveStart(), leave.getLeaveEnd()).isNegative());
    }

}
