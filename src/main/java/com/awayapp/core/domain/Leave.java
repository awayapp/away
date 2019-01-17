package com.awayapp.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "LEAVE")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private LeaveType type;

    @Column(name = "LEAVE_START")
    private Instant leaveStart;

    @Column(name = "LEAVE_END")
    private Instant leaveEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public Instant getLeaveStart() {
        return leaveStart;
    }

    public void setLeaveStart(Instant leaveStart) {
        this.leaveStart = leaveStart;
    }

    public Instant getLeaveEnd() {
        return leaveEnd;
    }

    public void setLeaveEnd(Instant leaveEnd) {
        this.leaveEnd = leaveEnd;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", employee=" + employee +
                ", type=" + type +
                ", leaveStart=" + leaveStart +
                ", leaveEnd=" + leaveEnd +
                '}';
    }

    public Leave() {
    }

    public Leave(Long id, Employee employee, LeaveType type, Instant leaveStart, Instant leaveEnd) {
        this.id = id;
        this.employee = employee;
        this.type = type;
        this.leaveStart = leaveStart;
        this.leaveEnd = leaveEnd;
    }
}
