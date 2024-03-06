package com.inflean.miniproject.controller;

import com.inflean.miniproject.dto.request.team.SaveTeamRequestDTO;
import com.inflean.miniproject.dto.response.team.SaveTeamResponseDTO;
import com.inflean.miniproject.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    /**
     * 팀 저장 API와 통신
     * @param request
     */
    @PostMapping("/save")
    public void saveTeam(@RequestBody SaveTeamRequestDTO request){
        teamService.saveTeam(request);
    }

    @GetMapping("/select-team")
    public List<SaveTeamResponseDTO> selectTeam(){
        return teamService.selectTeam();
    }
}
