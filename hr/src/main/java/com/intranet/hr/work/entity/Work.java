package com.intranet.hr.work.entity;

import com.intranet.hr.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT COMMENT '근태 번호'")
    private Long workSeq;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255) COMMENT '출퇴근 상태'")
    private WorkStatus status;

    @Column(columnDefinition = "DATE COMMENT '출근 시간'")
    private LocalDate onDutyTime;

    @Column(columnDefinition = "DATE COMMENT '퇴근 시간'")
    private LocalDate offDutyTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
