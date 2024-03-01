package com.intranet.hr.team.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamResponse {

    private String name;
    private String manager;
    private Long memberCount;

    @Builder
    public TeamResponse(String name, String manager, Long memberCount){
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
    }

}
