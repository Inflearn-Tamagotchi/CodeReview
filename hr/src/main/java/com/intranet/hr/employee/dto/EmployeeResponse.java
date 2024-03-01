package com.intranet.hr.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intranet.hr.employee.entity.TeamRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class EmployeeResponse {

    private String name;
    private String teamName;
    private TeamRole role;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate workStartDate;

    @Builder
    public EmployeeResponse(String name, String teamName, TeamRole role, LocalDate birthday, LocalDate workStartDate){
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

}
