package com.inflean.miniproject.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeSaveDTO {

    private String name;

    private String role;

    private LocalDate birthday;

    private LocalDate workStartDate;

    private String teamName;
}
