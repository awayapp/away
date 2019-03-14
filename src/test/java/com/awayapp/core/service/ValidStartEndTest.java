package com.awayapp.core.service;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.domain.Leave;
import com.awayapp.core.repository.LeaveRepository;
import com.awayapp.core.service.mapper.LeaveMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.Instant;

import static com.awayapp.core.TestUtil.getEmployee;
import static com.awayapp.core.TestUtil.getInstantAt;
import static com.awayapp.core.TestUtil.getLeave;
import static org.junit.Assert.assertEquals;


public class ValidStartEndTest {

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
    public void isValidStartEnd_withStartLessThanEnd_shouldReturnTrue() {
        //given

        Instant start = getInstantAt(2019, 6, 15);
        Instant end = getInstantAt(2019, 6, 16);
        Leave leave = getLeave(start, end);


        //when
        Boolean validStartEnd = leaveService.isValidStartEnd(leave);

        //then
        assertEquals(Boolean.TRUE, validStartEnd);
    }

    @Test
    public void isValidStartEnd_withStartEqualsEnd_shouldReturnTrue() {
        //given

        Instant start = getInstantAt(2019, 6, 15);
        Instant end = getInstantAt(2019, 6, 15);
        Leave leave = getLeave(start, end);


        //when
        Boolean validStartEnd = leaveService.isValidStartEnd(leave);

        //then
        assertEquals(Boolean.TRUE, validStartEnd);
    }

    @Test
    public void isValidStartEnd_withStartGraterThanEnd_shouldReturnFalse() {
        //given

        Instant start = getInstantAt(2019, 6, 16);
        Instant end = getInstantAt(2019, 6, 15);
        Leave leave = getLeave(start, end);


        //when
        Boolean validStartEnd = leaveService.isValidStartEnd(leave);

        //then
        assertEquals(Boolean.FALSE, validStartEnd);
    }


}
