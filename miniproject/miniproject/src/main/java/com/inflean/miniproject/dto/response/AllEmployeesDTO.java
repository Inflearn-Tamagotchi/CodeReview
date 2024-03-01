package com.inflean.miniproject.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AllEmployeesDTO {
    private String name;
    private String teamName;
    private String role;
    private LocalDate birthday;
    private LocalDate workStartday;

    public AllEmployeesDTO(String name, String teamName, String role, LocalDate birthday, LocalDate workStartday) {
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
        this.workStartday = workStartday;
    }
}
