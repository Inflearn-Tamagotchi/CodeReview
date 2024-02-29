package api.employee.service;
import api.employee.domain.Member;
import api.employee.domain.Role;
import api.employee.domain.Team;
import api.employee.model.MemberForm;
import api.employee.model.MemberUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrganizationService {

    private final MemberService memberService;
    private final TeamService teamService;

    @Transactional
    public void joinMember(MemberForm memberForm) {
        Member member = memberForm.convertToMember();
        if (memberForm.getTeamId() != null) {
            addTeamInfo(memberForm.getTeamId(), member);
        }
        memberService.saveMember(member);
    }

    @Transactional
    public void updateMember(MemberUpdateForm memberUpdateForm) {
        Member member = memberService.findMemberById(memberUpdateForm.getMemberId());
        if (memberUpdateForm.getTeamId() != null) {
            Team team = teamService.findTeamById(memberUpdateForm.getTeamId());
            member.changeTeam(team);
        }
        memberUpdateForm.update(member);
    }

    private void addTeamInfo(Long teamId, Member member) {
        Team team = teamService.findTeamById(teamId);
        member.changeTeam(team);
        if (member.isManager()) {
            registerManager(team, member);
        }
    }

    private void registerManager(Team team, Member member) {
        if (team.hasManager()) {
            Member currentManager = memberService.findMemberByName(team.getManager());
            currentManager.changeRole(Role.MEMBER);
        }
        member.registerManager();
    }
}
