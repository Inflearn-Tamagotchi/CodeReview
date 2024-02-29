package api.employee.model;

import api.employee.domain.Member;
import api.employee.domain.Role;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberUpdateForm {
    private Long teamId;
    private Long memberId;
    private String name;
    private String role;
    private LocalDate birthday;
    private LocalDate workStartDate;

    public void update(Member member) {
        if (!name.isBlank()) {
            member.changeName(name);
        }
        if (!role.isBlank()) { // 검증로직이 필요합니다.
            member.changeRole(Role.valueOf(role.toUpperCase()));
        }
        if (birthday != null) {
            member.changeBirthday(birthday);
        }
        if (workStartDate != null) {
            member.changeWorkStartDate(workStartDate);
        }
    }
}
