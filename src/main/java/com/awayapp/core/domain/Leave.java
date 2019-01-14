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

    @Column(name = "START")
    private Instant start;

    @Column(name = "END")
    private Instant end;

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

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", employee=" + employee +
                ", type=" + type +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public Leave() {
    }

    public Leave(Long id, Employee employee, LeaveType type, Instant start, Instant end) {
        this.id = id;
        this.employee = employee;
        this.type = type;
        this.start = start;
        this.end = end;
    }
}
