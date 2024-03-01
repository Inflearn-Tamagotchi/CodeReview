package com.intranet.hr.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String name;
    private LocalDate hireDate;
    private LocalDate birth;
    private Long teamNum;

}
