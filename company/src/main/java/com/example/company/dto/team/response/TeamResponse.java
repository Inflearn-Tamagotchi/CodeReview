package com.example.company.dto.team.response;

import com.example.company.domain.team.Team;

public class TeamResponse {

    private String name;
    private String manager;
    private Long memberCount;

    public TeamResponse(Team team) {
        this.name = team.getName();
        this.manager = team.getManager();
        this.memberCount = team.getMemberCount();
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public Long getMemberCount() {
        return memberCount;
    }
}
