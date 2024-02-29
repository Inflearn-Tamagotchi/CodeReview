package com.example.company.service.team;

import com.example.company.domain.team.Team;
import com.example.company.domain.team.TeamRepository;
import com.example.company.dto.team.request.TeamRequest;
import com.example.company.dto.team.response.TeamResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void registerTeam(TeamRequest request) {
        teamRepository.save(new Team(request.getName(), request.getManager(), request.getMemberCount()));
    }

    @Transactional
    public List<TeamResponse> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(TeamResponse::new).collect(Collectors.toList());
    }
}
