package com.awayapp.core.service;

import com.awayapp.core.controller.dto.LeaveDTO;
import com.awayapp.core.domain.Employee;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.repository.LeaveRepository;
import com.awayapp.core.service.mapper.LeaveMapper;
import org.assertj.core.util.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class LeaveService {

    private static Logger logger = LoggerFactory.getLogger(LeaveService.class);

    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;

    @Autowired
    public LeaveService(final LeaveRepository leaveRepository, final LeaveMapper leaveMapper) {
        this.leaveRepository = leaveRepository;
        this.leaveMapper = leaveMapper;
    }

    @Transactional
    public LeaveDTO saveLeave(final LeaveDTO leaveDTO) {
        Leave leave = leaveMapper.toEntity(leaveDTO);

        if (!isValidStartEnd(leave)) {
            throw new RuntimeException("The end date of the leave must be at least equal to the start date!");
        }

        return leaveMapper.toDto(leaveRepository.save(leave));
    }

    public List<LeaveDTO> findAllLeaves() {
        return leaveMapper.toDtos(leaveRepository.findAll());
    }

    @VisibleForTesting
    Long getVacationDaysAllowedAt(final Instant start, final Employee employee) {
        logger.info("Getting MaxVacationDays Allowed for " + employee.getEmail());
        int year = ZonedDateTime.ofInstant(start, UTC).getYear();
        int daysInYear = Year.of(year).length();

        double vacationDaysProducedPerDay = (double) employee.getMaxVacationDays() / (double) daysInYear;
        double daysSinceHire = (double) DAYS.between(employee.getHireDate(), start);

        long maxVacationDays = (long) Math.ceil(daysSinceHire * vacationDaysProducedPerDay);
        long vacationDaysTaken = getLeaveDaysUntil(start, employee);

        return maxVacationDays - vacationDaysTaken;
    }

    private Long getLeaveDaysUntil(final Instant until, final Employee employee) {
        // Query the DB and get all Leaves that are taken before the 'until' date
        List<Leave> leaves = leaveRepository.findByEmployeeAndLeaveEndBefore(employee, until);

        // Iterate over these Leaves and sum up their number of vacation days, in-memory
        return leaves
                .stream()
                .mapToLong(l -> DAYS.between(l.getLeaveStart(), l.getLeaveEnd()) + 1)
                .sum();
    }


    private Boolean isValidStartEnd(Leave leave) {
        return (!Duration.between(leave.getLeaveStart(), leave.getLeaveEnd()).isNegative());
    }

}
