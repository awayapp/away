package com.awayapp.core.service;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.domain.LeaveType;
import com.awayapp.core.repository.LeaveRepository;
import com.awayapp.core.service.mapper.LeaveMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.awayapp.core.TestUtil.getEmployee;
import static com.awayapp.core.TestUtil.getInstantAt;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VacationDaysAllowedTest {

    //Subject under test
    private LeaveService leaveService;

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private LeaveMapper leaveMapper;

    @Before
    public void setup() {
        leaveService = new LeaveService(leaveRepository, leaveMapper);
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


    @Test
    public void getVacationDaysAllowedAt_Sep10_hireDay115_shouldReturn12() {
        //given
        Instant start = getInstantAt(2019, 9, 10);
        Employee employee = getEmployee(2019, 4, 25, 30);

        //when
        Long vacationDaysAllowed = leaveService.getVacationDaysAllowedAt(start, employee);

        //then
        assertEquals(Long.valueOf(12), vacationDaysAllowed);
    }

    @Test
    public void getVacationDaysAllowedAt_Day365_hireDay115_shouldReturn21() {
        //given
        Instant start = getInstantAt(2019, 12, 31);
        Employee employee = getEmployee(2019, 4, 25, 30);

        //when
        Long vacationDaysAllowed = leaveService.getVacationDaysAllowedAt(start, employee);

        //then
        assertEquals(Long.valueOf(21), vacationDaysAllowed);
    }


    @Test
    public void getVacationDaysAllowedAt_Sep10_hireDay115_3vacationsAlready_shouldReturn9() {
        //given
        Instant start = getInstantAt(2019, 9, 10);
        Employee employee = getEmployee(2019, 4, 25, 30);

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
        assertEquals(Long.valueOf(9), vacationDaysAllowed);
    }


    @Test
    public void getVacationDaysAllowedAt_Sep10_hireDay115_9vacationsAlready_shouldReturn0() {
        //given
        Instant start = getInstantAt(2019, 9, 10);
        Employee employee = getEmployee(2019, 4, 25, 30);

        Leave l1 = new Leave();

        l1.setEmployee(employee);
        l1.setType(LeaveType.VACATION);
        l1.setLeaveStart(Instant.parse("2019-06-01T00:00:00.00Z"));
        l1.setLeaveEnd(Instant.parse("2019-06-12T00:00:00.00Z"));

        List<Leave> l = new ArrayList<>();
        l.add(l1);

        when(leaveRepository.findByEmployeeAndLeaveEndBefore(any(), any()))
                .thenReturn(l); //Collections.emptyList()

        //when
        Long vacationDaysAllowed = leaveService.getVacationDaysAllowedAt(start, employee);

        //then
        assertEquals(Long.valueOf(0), vacationDaysAllowed);
    }
}
