package com.inflean.miniproject.entity;

import com.inflean.miniproject.enums.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Getter
@Table(name = "EMPLOYEE")
@NoArgsConstructor
public class Employee {

    @Id @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    private String employeeName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String birthday;

    private String workStartDate;

    private Integer remainingAnnualLeaveDays = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Builder
    public Employee(Long employeeId, String employeeName, Role role, String birthday, String workStartDate, Team team) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
        this.team = team;
    }

    /**
     * role이 MANAGER라면 Team객체의
     * isManager 메소드를 실행시켜주는 메소드
     * @param role
     * @param employeeName
     */
    public void judgmentManager(Role role, String employeeName){
        if(role.equals(Role.MANAGER)){
            this.team.isManager(employeeName);
        }
    }

    /**
     * 해당 employee의 team에 직원수를 count해주는 메소드
     */
    public void teamEmployeeCount(){
        this.team.isCount();
    }

    /**
     * 현재 연도와 회원입사 연도를 판별해서 11일과 15일로 세팅하는 메소드
     * @param workStartDate
     */
    public void settingEmployeeAnnualLeaveDays(String workStartDate){
        // 현재연도
        String now = LocalDateTime.now().toString().substring(0,4);

        // 직원이 입사한 연도
        String startDate = workStartDate.substring(0,4);

        if(now.equals(startDate)){
            this.remainingAnnualLeaveDays = 11;
        }else {
            this.remainingAnnualLeaveDays = 15;
        }
    }

    /**
     * 사용한 연차일을 받아서 현재 연차일에서 마이너스해주는 메소드
     * @param usingAnnual
     */
    public void modifyAnnualLeave(Integer usingAnnual){
        this.remainingAnnualLeaveDays -= usingAnnual;
    }

}
