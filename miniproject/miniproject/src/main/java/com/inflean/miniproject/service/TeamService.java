package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.request.team.SaveTeamRequestDTO;
import com.inflean.miniproject.dto.response.team.SaveTeamResponseDTO;
import com.inflean.miniproject.entity.Team;
import com.inflean.miniproject.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void saveTeam(SaveTeamRequestDTO request) {
        Team team = Team.builder()
                .teamName(request.getTeamName())
                .build();
        teamRepository.save(team);
    }

    public List<SaveTeamResponseDTO> selectTeam() {

        List<Team> teams = teamRepository.findAll();
        List<SaveTeamResponseDTO> response = new ArrayList<>();

        for(Team team : teams){
            SaveTeamResponseDTO dto = SaveTeamResponseDTO.builder()
                    .name(team.getTeamName())
                    .manager(team.getManager())
                    .employeeCount(team.getEmployeeCount())
                    .build();
            response.add(dto);
        }

        return response;
    }

}
