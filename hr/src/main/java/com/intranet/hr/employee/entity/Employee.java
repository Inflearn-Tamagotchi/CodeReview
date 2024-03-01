package com.intranet.hr.employee.entity;


import com.intranet.hr.team.entity.Team;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT COMMENT '직원 아이디'")
    private Long id;

    @Column(columnDefinition = "VARCHAR(50) COMMENT '직원 이름'")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255) COMMENT '매니저 여부'")
    private TeamRole role;

    @Column(columnDefinition = "DATE COMMENT '입사일자'")
    private LocalDate hireDate;

    @Column(columnDefinition = "DATE COMMENT '생일'")
    private LocalDate birth;

    @ManyToOne
    @JoinColumn(name="team_num")
    private Team team;

    @Builder
    public Employee(String name, TeamRole role, LocalDate hireDate, LocalDate birth, Team team){
        this.name = name;
        this.role = role;
        this.hireDate = hireDate;
        this.birth = birth;
        this.team = team;
    }

}
