package com.inflean.miniproject.controller;

import com.inflean.miniproject.entity.Employee;
import com.inflean.miniproject.entity.EmployeeAnnualLeave;
import com.inflean.miniproject.entity.Team;
import com.inflean.miniproject.entity.Work;
import com.inflean.miniproject.enums.Role;
import com.inflean.miniproject.enums.State;
import com.inflean.miniproject.repository.EmployeeAnnualLeaveRepository;
import com.inflean.miniproject.repository.EmployeeRepository;
import com.inflean.miniproject.repository.TeamRepository;
import com.inflean.miniproject.repository.WorkRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkRepository workRepository;
    private final EmployeeAnnualLeaveRepository employeeAnnualLeaveRepository;

    @PostConstruct
    public void saveTeamAndSaveEmployee() {
        Team team1 = Team.builder()
                .teamName("영업부")
                .build();
        teamRepository.save(team1);

        Team team2 = Team.builder()
                .teamName("마케팅부")
                .build();
        teamRepository.save(team2);

        Team team3 = Team.builder()
                .teamName("개발부")
                .build();
        teamRepository.save(team3);

        for (int i = 1; i <= 50; i++) {
            Employee employee = Employee.builder()
                    .employeeName(String.format("testPerson%d", i))
                    .role(Role.MEMBER)
                    .birthday("2024-03-02")
                    .workStartDate(format(LocalDateTime.now()))
                    .team(teamRepository.findById(1L).orElseThrow(null))
                    .build();

            // 해당 employee객체의 team의 employeeCount를 1씩 추가하겠다.
            employee.teamEmployeeCount();
            // 해당 해당 employee객체의 role을 판단해서 team객체의 manager를 이름으로 바꿔줌
            employee.judgmentManager(employee.getRole(), employee.getEmployeeName());

            teamRepository.save(employee.getTeam());
            employeeRepository.save(employee);
        }

        Employee employee = Employee.builder()
                .employeeName("진영준")
                .role(Role.MANAGER)
                .birthday("2024-03-02")
                .workStartDate(format(LocalDateTime.now()))
                .team(teamRepository.findById(1L).orElseThrow(null))
                .build();
        employee.settingEmployeeAnnualLeaveDays(employee.getWorkStartDate());
        // 해당 employee객체의 team의 employeeCount를 1씩 추가하겠다.
        employee.teamEmployeeCount();
        // 해당 해당 employee객체의 role을 판단해서 team객체의 manager를 이름으로 바꿔줌
        employee.judgmentManager(employee.getRole(), employee.getEmployeeName());

        teamRepository.save(employee.getTeam());
        employeeRepository.save(employee);

        EmployeeAnnualLeave employeeAnnualLeave = EmployeeAnnualLeave.builder()
                .startAnnualLeaveDay("2024-03-20")
                .endAnnualLeaveDay("2024-03-24")
                .employee(employee)
                .build();

        // 자신의 연차에서 마이너스해야함
        long day = employeeAnnualLeave.startMinusEnd("2024-03-20", "2024-03-24");
        employee.modifyAnnualLeave((int) day);

        // 신청일
        LocalDate employeeRequestDay = format("2024-03-20"); // 2024-01-01

        // 팀의 최소 신청 연차일
        LocalDate teamRequestDay = LocalDate.now().plusDays(employee.getTeam().getTeamAnnualLeaveDays()); // 2024-01-01

        // 신청일이 팀의 최소 신청 연차일 이후인지 판별
        boolean judgement = employeeRequestDay.isAfter(teamRequestDay);

        if(judgement){
            employeeAnnualLeaveRepository.save(employeeAnnualLeave);
        }else{
            throw new IllegalArgumentException(String.format("신청일은 %s 이후여야합니다.", teamRequestDay));
        }

        for (int i = 10; i<30; i++){
            Work work = Work.builder()
                    .state(State.TO_WORK)
                    .workStartTime(String.format("2024-03-%d 09:00", i))
                    .employee(employeeRepository.getReferenceById(51L))
                    .build();
            workRepository.save(work);

            // 업데이트 메소드
            work.updateWork(State.OFF_WORK,  String.format("2024-03-%d 18:00", i));
            workRepository.save(work);
        }

    }

    public String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    public LocalDate format(String startDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(startDate, formatter);
    }

    public Integer getTeamDay(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 유저입니다."));

        return employee.getTeam().getTeamAnnualLeaveDays();
    }

}
