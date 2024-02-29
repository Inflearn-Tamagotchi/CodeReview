package api.employee.service;

import api.employee.domain.Team;
import api.employee.model.TeamForm;
import api.employee.model.TeamResponse;
import api.employee.model.TeamUpdateForm;
import api.employee.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void saveTeam(TeamForm teamForm) {
        teamRepository.save(teamForm.convertToTeam());
    }

    public List<Team> findAllTeam() {
        return teamRepository.findAll().stream()
                .toList();
    }

    public TeamResponse findOneTeam(Long teamId) {
        return teamRepository.findById(teamId)
                .map(TeamResponse::fromTeam)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
    }

    public Team findTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
    }

    @Transactional
    public void updateTeam(TeamUpdateForm teamUpdateForm) {
        Team team = findTeamById(teamUpdateForm.getTeamId());
        teamUpdateForm.update(team);
    }

    @Transactional
    public void deleteTeam(Long teamId) {
        Team team = findTeamById(teamId);
        teamRepository.delete(team);
    }
}
