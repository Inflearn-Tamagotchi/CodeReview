package com.intranet.hr.team.entity;

import com.intranet.hr.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT COMMENT '팀 번호'")
    private Long teamNum;

    @Column(columnDefinition = "VARCHAR(50) COMMENT '팀 이름'")
    private String name;

    @OneToOne(mappedBy = "team", fetch = FetchType.LAZY)
    private Employee employee;
}
