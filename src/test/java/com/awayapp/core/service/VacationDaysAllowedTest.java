package com.awayapp.core.service;

import com.awayapp.core.domain.Employee;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;

public class VacationDaysAllowedTest {

    //Subject under test
    private LeaveService leaveService;

    @Before
    public void setup() {
        leaveService = new LeaveService(null, null, null);
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
