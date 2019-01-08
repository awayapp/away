package com.awayapp.core.domain;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="LEAVE")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private LeaveType type;

    @Column(name="START")
    private Instant start;

    @Column(name="END")
    private Instant end;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
