package com.example.company.dto.employee.response;

import com.example.company.domain.employee.Employee;
import com.example.company.domain.employee.EmployeeRole;

import java.time.LocalDate;

public class EmployeeResponse {

    private String name;
    private String teamName;
    private EmployeeRole role;
    private LocalDate birthday;
    private LocalDate workStartDate;

    public EmployeeResponse(Employee employee) {
        this.name = employee.getName();
        this.teamName = employee.getTeamName();
        this.role = employee.getRole();
        this.birthday = employee.getBirthday();
        this.workStartDate = employee.getWorkStartDate();
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }
}
