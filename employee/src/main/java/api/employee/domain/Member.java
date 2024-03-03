package api.employee.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private LocalDate birthday;
    private LocalDate workStartDate;
    @Embedded
    private Leave leave;

    // ==== 생성자 ==== //
    public static MemberBuilder builder() {
        return new MemberBuilder();
    }

    public static class MemberBuilder {
        private String name;
        private Role role;
        private LocalDate birthday;
        private LocalDate workStartDate;

        public MemberBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MemberBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public MemberBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public MemberBuilder workStartDate(LocalDate workStartDate) {
            this.workStartDate = workStartDate;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

    private Member(MemberBuilder builder) {

        this.name = builder.name;
        this.role = builder.role;
        this.birthday = builder.birthday;
        this.workStartDate = builder.workStartDate;
        this.leave = new Leave(this.workStartDate);
    }

    // ==== 접근자 ==== //
    public String getTeamName() {
        return team.getName();
    }

    public String getRoleName() {
        return role.name();
    }

    // ==== 변경자 ==== //
    public void changeTeam(Team team) {
        reSignTeam();
        signTeam(team);
    }

    public void signTeam(Team team) {
        this.team = team;
        this.team.increaseMemberCount();
    }

    public void reSignTeam() {
        if (this.team != null) {
            this.team.decreaseMemberCount();
        }
        this.team = null;
    }

    public void changeRole(Role role) {
        // 역할이 기존과 같으면 return;
        if (this.role == role) {
            return;
        }
        this.role = role; // 역할 부여

        // 변경되는 역할이 멤버면 팀의 매니저를 null로 변경
        boolean roleCondition = this.role == Role.MEMBER;
        boolean nameCondition = this.team.getManager().equals(this.name);
        if (roleCondition && nameCondition) {
            this.team.changeManager(null);
        }
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void changeWorkStartDate(LocalDate workStartDate) {
        this.workStartDate = workStartDate;
    }

    // ==== 편의 메서드 ==== //
    public void usingLeave() {
        this.leave.decreaseRemainingDays();
    }

    public boolean isManager() {
        return this.role == Role.MANAGER;
    }

    public boolean hasTeam() {
        return this.team != null;
    }

    public void registerManager() {
        Assert.isTrue(this.role == Role.MANAGER, "The role must be MANAGER.");
        team.changeManager(this.name);
    }
}
