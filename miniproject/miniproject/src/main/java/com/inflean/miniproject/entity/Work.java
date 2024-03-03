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

    /**
     * 현재 객체의 workStartTime 와 workEndTime 을 빼서
     * 분단위로 converting 후 반환하는 메소드
     * @return Long타입의 분단위 숫자반환
     */
    public Long startTimeMinusEndTime(){
        LocalDateTime start = LocalDateTime.parse(workStartTime);
        LocalDateTime end = LocalDateTime.parse(workEndTime);
        return Duration.between(start, end).toMinutes();
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
