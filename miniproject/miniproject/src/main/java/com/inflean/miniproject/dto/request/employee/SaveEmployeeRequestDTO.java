package com.inflean.miniproject.dto.request.employee;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveEmployeeRequestDTO {
    private String name;
    private String role;
    private String birthday;
    private String teamName;

    @Builder
    public SaveEmployeeRequestDTO(String name, String role, String birthday, String teamName) {
        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.teamName = teamName;
    }
}
