package api.employee.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    public Member changeTeam(Team team) {
        if (this.team != null) {
            this.team.decreaseMemberCount();
        }
        this.team = team;
        if (team != null) {
            this.team.increaseMemberCount();
        }
        return this;
    }

    public Member changeRole(Role role) {
        this.role = role;
        return this;
    }

    public Member changeName(String name) {
        this.name = name;
        return this;
    }

    public Member changeBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public Member changeWorkStartDate(LocalDate workStartDate) {
        this.workStartDate = workStartDate;
        return this;
    }

    // ==== 편의 메서드 ==== //
    public void usingLeave() {
        this.leave.decreaseRemainingDays();
    }

    public boolean isManager() {
        return this.role == Role.MANAGER;
    }

    public void registerManager() {
        team.changeManager(this.name);
    }
}
