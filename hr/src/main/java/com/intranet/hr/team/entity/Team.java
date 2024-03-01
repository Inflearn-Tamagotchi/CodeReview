package com.intranet.hr.team.entity;

import com.intranet.hr.employee.entity.Employee;
import com.intranet.hr.employee.entity.TeamRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "team")
    private List<Employee> employees;

    @Builder
    public Team(String name){
        this.name = name;
    }

    public String getManager() {
        for (Employee employee : employees) {
            if (employee.getRole().equals(TeamRole.MANAGER)) {
                return employee.getName();
            }
        }
        return null;
    }

}
