package com.inflean.miniproject.controller;

import com.inflean.miniproject.entity.Employee;
import com.inflean.miniproject.entity.Team;
import com.inflean.miniproject.entity.Work;
import com.inflean.miniproject.enums.Role;
import com.inflean.miniproject.enums.State;
import com.inflean.miniproject.repository.EmployeeRepository;
import com.inflean.miniproject.repository.TeamRepository;
import com.inflean.miniproject.repository.WorkRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkRepository workRepository;

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

        for (int i = 10; i<30; i++){
            Work work = Work.builder()
                    .state(State.TO_WORK)
                    .workStartTime(String.format("2024-03-%d 09:00", i))
                    .employee(employeeRepository.getReferenceById(1L))
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

}
