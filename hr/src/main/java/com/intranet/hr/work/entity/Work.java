package com.intranet.hr.work.entity;

import com.intranet.hr.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(columnDefinition = "DATE COMMENT '근무일자'")
    private LocalDate dutyDate;

    @Column(columnDefinition = "TIME COMMENT '출근 시간'")
    private LocalTime onDutyTime;

    @Column(columnDefinition = "TIME COMMENT '퇴근 시간'")
    private LocalTime offDutyTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Builder
    public Work(WorkStatus status, Employee employee){
        this.status = status;
        this.dutyDate = LocalDate.now();
        this.onDutyTime = LocalTime.now();
        this.employee = employee;
    }

    public void updateDuty(){
        this.status = WorkStatus.OFF_DUTY;
        this.offDutyTime = LocalTime.now();
    }

}
