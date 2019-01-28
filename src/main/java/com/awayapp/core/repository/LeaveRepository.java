package com.awayapp.core.repository;

import com.awayapp.core.domain.Employee;
import com.awayapp.core.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployeeAndLeaveEndBefore(Employee employee, Instant leaveEnd);

}
