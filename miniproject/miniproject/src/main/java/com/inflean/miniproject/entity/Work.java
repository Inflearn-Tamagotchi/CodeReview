package com.inflean.miniproject.entity;

import com.inflean.miniproject.enums.State;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity @Getter
@Table(name = "WORK")
@NoArgsConstructor
public class Work {

    @Id @Column(name = "WORK_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workId;

    @Enumerated(EnumType.STRING)
    private State state;

    private String workStartTime;

    private String workEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    /**
     * work객체를 update할 수 있는 메소드
     * @param state
     * @param workEndTime
     */
    public void updateWork(State state, String workEndTime){
        this.state = state;
        this.workEndTime = workEndTime;
    }

    @Builder
    public Work(Long workId, State state, String workStartTime, String workEndTime, Employee employee) {
        this.workId = workId;
        this.state = state;
        this.workStartTime = workStartTime;
        if(workEndTime == null){
            this.workEndTime = "no yet";
        }else {
            this.workEndTime = workEndTime;
        }
        this.employee = employee;
    }
}
