package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.request.annualLeave.AnnualLeaveRequestDTO;
import com.inflean.miniproject.entity.Employee;
import com.inflean.miniproject.entity.EmployeeAnnualLeave;
import com.inflean.miniproject.repository.EmployeeAnnualLeaveRepository;
import com.inflean.miniproject.repository.EmployeeRepository;
import com.inflean.miniproject.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeAnnualLeaveService {

    private final EmployeeAnnualLeaveRepository employeeAnnualLeaveRepository;
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void requestAnnualLeave(AnnualLeaveRequestDTO request){
        EmployeeAnnualLeave employeeAnnualLeave = EmployeeAnnualLeave.builder()
                .startAnnualLeaveDay(request.getStartDate())
                .endAnnualLeaveDay(request.getEndDate())
                .employee(employeeRepository.getReferenceById(request.getEmployeeIdx()))
                .build();

        // 자신의 연차에서 마이너스해야함
        long day = employeeAnnualLeave.startMinusEnd(request.getStartDate(), request.getEndDate());
        Employee employee = employeeRepository.findById(request.getEmployeeIdx()).orElseThrow(IllegalArgumentException::new);
        employee.modifyAnnualLeave((int) day);

        // 신청일
        LocalDate employeeRequestDay = format(request.getStartDate()); // 2024-01-01

        // 팀의 최소 신청 연차일
        LocalDate teamRequestDay = LocalDate.now().plusDays(getTeamDay(request.getEmployeeIdx())); // 2024-01-01

        // 신청일이 팀의 최소 신청 연차일 이후인지 판별
        boolean judgement = employeeRequestDay.isAfter(teamRequestDay);

        if(judgement){
            employeeAnnualLeaveRepository.save(employeeAnnualLeave);
        }else{
            throw new IllegalArgumentException(String.format("신청일은 %s 이후여야합니다.", teamRequestDay));
        }
    }

    // 편의메소드
    public Integer getTeamDay(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 유저입니다."));

        return employee.getTeam().getTeamAnnualLeaveDays();
    }

    public LocalDate format(String startDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(startDate, formatter);
    }

}
