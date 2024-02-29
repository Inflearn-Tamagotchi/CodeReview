package api.employee.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;

    private String name;
    private String manager;
    private Integer leavePolicy;
    private Integer memberCount = 0;

    // ==== 생성자 ==== //
    @Builder
    public Team(String name, Integer leavePolicy) {
        this.name = name;
        this.leavePolicy = leavePolicy;
    }

    // ==== 변경자 ==== //
    public Team changeName(String name) {
        this.name = name;
        return this;
    }

    public Team changeManager(String managerName) {
        this.manager = managerName;
        return this;
    }

    public Team changeLeavePolicy(Integer leavePolicy) {
        this.leavePolicy = leavePolicy;
        return this;
    }

    // ==== 편의 메서드 ==== //
    public boolean hasManager() {
        return this.manager != null;
    }

    public void increaseMemberCount() {
        this.memberCount++;
    }

    public void decreaseMemberCount() {
        this.memberCount--;
    }
}
