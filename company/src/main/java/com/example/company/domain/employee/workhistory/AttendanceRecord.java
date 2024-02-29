package com.example.company.domain.employee.workhistory;

import com.example.company.domain.employee.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    private LocalDate workDate;

    private LocalTime workStart;

    private LocalTime workEnd;

    protected AttendanceRecord() {}

    public AttendanceRecord(Employee employee) {
        this.employee = employee;
        this.workDate = LocalDate.now();
        this.workStart = LocalTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public LocalTime getWorkStart() {
        return workStart;
    }

    public LocalTime getWorkEnd() {
        return workEnd;
    }

    public void endWork() {
        this.workEnd = LocalTime.now();
    }

}
