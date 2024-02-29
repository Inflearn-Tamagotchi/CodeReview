package com.inflean.miniproject.dto.response;

import lombok.Data;

@Data
public class SelectAllTeamDTO {
    private String teamName;
    private String manager;
    private Long memberCount;

    public SelectAllTeamDTO(String teamName, String manager, Long memberCount) {
        this.teamName = teamName;
        this.manager = manager;
        this.memberCount = memberCount;
    }
}
