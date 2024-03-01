package com.inflean.miniproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Work {
    @Id
    @Column(name = "WORK_IDX")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workIdx;

    @Column
    private String state;

    @Column
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_IDX")
    private Employee employee;

    @Builder
    public Work(Long workIdx, String state, LocalDateTime time, Employee employee) {
        this.workIdx = workIdx;
        this.state = state;
        this.time = time;
        this.employee = employee;
    }
}
