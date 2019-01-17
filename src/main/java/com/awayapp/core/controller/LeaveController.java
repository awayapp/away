package com.awayapp.core.controller;

import com.awayapp.core.controller.dto.LeaveDTO;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.service.LeaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.awayapp.core.controller.LeaveController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class LeaveController {

    static final String BASE_URL = "/api/leaves";

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public LeaveDTO saveLeave(@RequestBody LeaveDTO dto) {
        return leaveService.saveLeave(dto);
    }

    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.findAllLeaves();
    }

}
