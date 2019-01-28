package com.awayapp.core.service;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.domain.LeaveType;
import com.awayapp.core.repository.LeaveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VacationDaysAllowedTest {

    //Subject under test
    private LeaveService leaveService;

    @Mock
    private LeaveRepository leaveRepository;

    @Before
    public void setup() {
        leaveService = new LeaveService(leaveRepository, null, null);
    }

    @Test
    public void getVacationDaysAllowedAt_day0_hireDay0_shouldReturn0() {
        //given
        Instant start = getInstantAt(2019, 1, 1);
        Employee employee = getEmployee(2019, 1, 1, 30);

        //when
        Long vacationDaysAllowed = leaveService.getVacationDaysAllowedAt(start, employee);

        //then
        assertEquals(Long.valueOf(0), vacationDaysAllowed);
    }


    @Test
    public void getVacationDaysAllowedAt_day365_hireDay0_shouldReturnMaxVacationDays() {
        //given
        Instant start = getInstantAt(2019, 12, 31);
        Employee employee = getEmployee(2019, 1, 1, 30);

        //when
        Long vacationDaysAllowed = leaveService.getVacationDaysAllowedAt(start, employee);

        //then
        assertEquals(Long.valueOf(employee.getMaxVacationDays()), vacationDaysAllowed);
    }

    @Test
    public void getVacationDaysAllowedAt_July1_hireDay0_3vacationsAlready_shouldReturn12() {
        //given
        Instant start = getInstantAt(2019, 7, 1);
        Employee employee = getEmployee(2019, 1, 1, 30);

        Leave leave2Days = new Leave();
        Leave leave1Day = new Leave();

        leave2Days.setEmployee(employee);
        leave2Days.setType(LeaveType.VACATION);
        leave2Days.setLeaveStart(Instant.parse("2019-06-01T00:00:00.00Z"));
        leave2Days.setLeaveEnd(Instant.parse("2019-06-02T00:00:00.00Z"));

        leave1Day.setEmployee(employee);
        leave1Day.setType(LeaveType.VACATION);
        leave1Day.setLeaveStart(Instant.parse("2019-06-15T00:00:00.00Z"));
        leave1Day.setLeaveEnd(Instant.parse("2019-06-15T00:00:00.00Z"));

        List<Leave> l = new ArrayList<>();
        l.add(leave2Days);
        l.add(leave1Day);

        when(leaveRepository.findByEmployeeAndLeaveEndBefore(any(), any()))
                .thenReturn(l); //Collections.emptyList()

        //when
        Long vacationDaysAllowed = leaveService.getVacationDaysAllowedAt(start, employee);

        //then
        assertEquals(Long.valueOf(12), vacationDaysAllowed);
    }

    private Employee getEmployee(int hireYear, int hireMonth, int hireDay, int maxVacationDays) {
        Instant hireDate = getInstantAt(hireYear, hireMonth, hireDay);
        Employee employee = new Employee();
        employee.setHireDate(hireDate);
        employee.setMaxVacationDays(maxVacationDays);
        return employee;
    }

    private Instant getInstantAt(int year, int month, int day) {
        ZoneOffset offset = ZoneOffset.UTC;
        return LocalDate
                .of(year, month, day)
                .atStartOfDay(offset)
                .toInstant();
    }
}
