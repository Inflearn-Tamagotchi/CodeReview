package com.inflean.miniproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "TEAM")
@NoArgsConstructor
public class Team {

    @Id @Column(name = "TEAM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamId;

    private String teamName;

    //  인스턴스에 올라간 객체의 숫자를 카운트해야하기 때문에
    private Long employeeCount = 0L;

    private String manager = null;

    private Integer teamAnnualLeaveDays = 1; // 몇일전에 신청해야하는 지 나타내는 일수

    @Builder
    public Team(Long teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.employeeCount = 0L; // new team을 했을 때 0이여야하기 때문에
        this.manager = "no manager";
    }

    public Team(Integer teamAnnualLeaveDays){
        this.teamAnnualLeaveDays = teamAnnualLeaveDays;
    }

    /**
     * 메소드 호출 시에 employeeCount를 1씩 plus시킨다.
     * Employee를 save할 때마다 메소드를 동작시켜주면 된다.
     */
    public void isCount(){
        this.employeeCount++;
    }

    /**
     * 해당 팀의 MANAGER가 누군지를 명시해준다.
     * @param name
     */
    public void isManager(String name){
        this.manager = name;
    }

    public void Team(Integer teamAnnualLeaveDays){
        this.teamAnnualLeaveDays = teamAnnualLeaveDays;
    }
}
