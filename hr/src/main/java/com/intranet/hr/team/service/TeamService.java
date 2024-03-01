package com.intranet.hr.team.service;

import com.intranet.hr.employee.entity.Employee;
import com.intranet.hr.employee.entity.TeamRole;
import com.intranet.hr.employee.repository.EmployeeRepository;
import com.intranet.hr.team.dto.TeamResponse;
import com.intranet.hr.team.entity.Team;
import com.intranet.hr.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    public void createTeam(String name){
        Team team = Team.builder().name(name).build();
        teamRepository.save(team);
    }

    public List<TeamResponse> readTeamList(){
        List<TeamResponse> result = new ArrayList<>();
        List<Team> list = teamRepository.findAll();
        for(Team team : list){
            TeamResponse response = TeamResponse.builder()
                    .name(team.getName())
                    .manager(team.getManager())
                    .memberCount(employeeRepository.countByTeam(team))
                    .build();
            result.add(response);
        }
        return result;
    }

}
