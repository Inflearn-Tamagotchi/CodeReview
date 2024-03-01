package com.inflean.miniproject.dto.response;

import lombok.Data;

@Data
public class SelectAllTeamDTO {
    private String teamName;
    private String manager;
    private Long memberCount;


}
