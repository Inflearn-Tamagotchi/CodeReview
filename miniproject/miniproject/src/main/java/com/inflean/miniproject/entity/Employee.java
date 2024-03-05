package com.inflean.miniproject.entity;

import com.inflean.miniproject.enums.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
