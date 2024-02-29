package com.example.company.dto.team.request;

public class TeamRequest {

    private String name;
    private String manager;
    private Long memberCount;

    public TeamRequest(String name, String manager, Long memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount == null ? 0 : memberCount;
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
