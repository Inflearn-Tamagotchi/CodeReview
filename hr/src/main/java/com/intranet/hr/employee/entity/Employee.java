package com.intranet.hr.employee.entity;


import com.intranet.hr.team.entity.Team;
import jakarta.persistence.*;
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

    @OneToOne
    @JoinColumn(name="team_num")
    private Team team;

}
