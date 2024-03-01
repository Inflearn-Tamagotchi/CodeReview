package com.inflean.miniproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Getter @Setter
@Table(name = "DEPARTMENTS")
@NoArgsConstructor
public class Department {

    @Id @Column(name = "DEPARTMENT_IDX")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentIdx;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Employee> employees;

    @Builder
    public Department(Long departmentIdx, String teamName, List<Employee> employees) {
        this.departmentIdx = departmentIdx;
        this.teamName = teamName;
        this.employees = employees;
    }
}
