package com.awayapp.core.repository;

import com.awayapp.core.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
