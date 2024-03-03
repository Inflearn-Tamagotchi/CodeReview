package com.inflean.miniproject.dto.response.team;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveTeamResponseDTO {

    private String name;
    private String manager;
    private Long employeeCount;

    @Builder
    public SaveTeamResponseDTO(String name, String manager, Long employeeCount) {
        this.name = name;
        this.manager = manager;
        this.employeeCount = employeeCount;
    }
}
