package api.employee.model;

import api.employee.domain.Member;
import api.employee.domain.Role;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Getter
public class MemberForm {
    private Long teamId;
    private String name;
    private String role;
    private LocalDate birthday;
    private LocalDate workStartDate;

    public MemberForm(Long teamId, String name, String role, LocalDate birthday, LocalDate workStartDate) {
        this.teamId = teamId;
        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    public Member convertToMember() {
        Assert.notNull(role, "Role은 필수 값입니다.");
        Assert.notNull(workStartDate, "workStartDate는 필수 값입니다.");
        return Member.builder()
                .name(name)
                .role(Role.valueOf(this.role.toUpperCase()))
                .birthday(birthday)
                .workStartDate(workStartDate)
                .build();
    }
}
