package com.awayapp.core.service;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.repository.LeaveRepository;
import com.awayapp.core.service.mapper.LeaveMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.time.Instant;

import static com.awayapp.core.TestUtil.getEmployee;
import static com.awayapp.core.TestUtil.getInstantAt;

public class SaveLeaveTest {

    //Subject under test
    private LeaveService leaveService;

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private LeaveMapper leaveMapper;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        leaveService = new LeaveService(leaveRepository, leaveMapper);
    }

    @Test
    public void saveLeave_startFirstDay_shouldThrowException() {
        //given
        Instant start = getInstantAt(2019, 1, 1);
        Employee employee = getEmployee(2019, 1, 1, 30);

        //then
        exception.expect(NullPointerException.class);

        //when
        leaveService.saveLeave(null);

    }

}
