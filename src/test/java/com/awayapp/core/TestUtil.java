package com.awayapp.core;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.domain.Leave;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class TestUtil {

    public static Employee getEmployee(int hireYear, int hireMonth, int hireDay, int maxVacationDays) {
        Instant hireDate = getInstantAt(hireYear, hireMonth, hireDay);
        Employee employee = new Employee();
        employee.setHireDate(hireDate);
        employee.setMaxVacationDays(maxVacationDays);
        employee.setEmail("default@default.test");
        return employee;
    }

    public static Instant getInstantAt(int year, int month, int day) {
        ZoneOffset offset = ZoneOffset.UTC;
        return LocalDate
                .of(year, month, day)
                .atStartOfDay(offset)
                .toInstant();
    }

    public static Leave getLeave(Instant start, Instant end) {
        Leave leave = new Leave();
        leave.setLeaveStart(start);
        leave.setLeaveEnd(end);
        return leave;
    }

}
