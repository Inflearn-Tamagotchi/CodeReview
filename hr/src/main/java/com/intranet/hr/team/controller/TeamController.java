package com.intranet.hr.team.controller;

import com.intranet.hr.team.dto.TeamResponse;
import com.intranet.hr.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    /**
     * 팀 등록 기능
     * @param name 팀명
     */
    @PostMapping(value = "/create")
    public void createTeam(@RequestParam String name){
        teamService.createTeam(name);
    }

    /**
     * 전체 팀 목록 조회 기능
     * @return 전체 팀 목록
     */
    @GetMapping(value = "/list")
    public List<TeamResponse> readTeamList(){
        return teamService.readTeamList();
    }

}
