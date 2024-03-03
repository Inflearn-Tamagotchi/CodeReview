package com.inflean.miniproject.dto.response.employee;

import com.inflean.miniproject.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
public class SelectAllEmployeeResponseDTO {
    private String name;
    private String teamName;
    private String role;
    private String birthday;
    private String workStartDate;

    @Builder
    public SelectAllEmployeeResponseDTO(String name, String teamName, String role, String birthday, String workStartDate) {
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }
}
