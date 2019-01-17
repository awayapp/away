package com.awayapp.core.service;

import com.awayapp.core.controller.dto.LeaveDTO;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.repository.LeaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;

    public LeaveService(final LeaveRepository leaveRepository, final LeaveMapper leaveMapper) {
        this.leaveRepository = leaveRepository;
        this.leaveMapper = leaveMapper;
    }


    @Transactional
    public LeaveDTO saveLeave(LeaveDTO leaveDTO) {

        Leave leave = leaveMapper.toEntity(leaveDTO);

        return leaveMapper.toDto(leaveRepository.save(leave));
    }

    public List<Leave> findAllLeaves() {

        return leaveRepository.findAll();
    }

}
