package com.example.company.dto.employee.response;

import java.time.LocalDate;

public class AttendanceDateResponse {

    private LocalDate date;
    private Long workingMinutes;

    public AttendanceDateResponse(LocalDate date, Long workingMinutes) {
        this.date = date;
        this.workingMinutes = workingMinutes;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getWorkingMinutes() {
        return workingMinutes;
    }
}
