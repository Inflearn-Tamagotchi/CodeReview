package com.inflean.miniproject.entity;

import com.inflean.miniproject.enums.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity @Getter
@Table(name = "EMPLOYEE_ANNUAL_LEAVE")
@NoArgsConstructor
public class EmployeeAnnualLeave {

    @Id @Column(name = "EMPLOYEE_ANNUAL_LEAVE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeAnnualLeaveId;

    private String startAnnualLeaveDay;

    private String endAnnualLeaveDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Builder
    public EmployeeAnnualLeave(String startAnnualLeaveDay, String endAnnualLeaveDay, Employee employee) {
        this.startAnnualLeaveDay = startAnnualLeaveDay;
        this.endAnnualLeaveDay = endAnnualLeaveDay;
        this.employee = employee;
    }

    /**
     * 연차 시작날과 끝날을 빼서 일수로 변환하는 메소드
     * @param startDate
     * @param endDate
     * @return
     */
    public long startMinusEnd(String startDate, String endDate){
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        long day = ChronoUnit.DAYS.between(start, end);

        return day;
    }


}
