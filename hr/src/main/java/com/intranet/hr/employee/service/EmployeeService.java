package com.intranet.hr.employee.service;

import com.intranet.hr.employee.dto.EmployeeRequest;
import com.intranet.hr.employee.dto.EmployeeResponse;
import com.intranet.hr.employee.entity.Employee;
import com.intranet.hr.employee.entity.TeamRole;
import com.intranet.hr.employee.repository.EmployeeRepository;
import com.intranet.hr.team.entity.Team;
import com.intranet.hr.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    public void createEmployee(EmployeeRequest requestDto){
        Team team = teamRepository.findByTeamNum(requestDto.getTeamNum())
                .orElseThrow(IllegalArgumentException::new); // 요청된 팀 번호로 팀을 조회

        Employee employee = Employee.builder()
                .name(requestDto.getName())
                .role(TeamRole.MEMBER)
                .hireDate(requestDto.getHireDate())
                .birth(requestDto.getBirth())
                .team(team) // 조회한 팀을 설정
                .build();

        employeeRepository.save(employee);
    }

    public List<EmployeeResponse> readEmployeeList(){
        List<EmployeeResponse> result = new ArrayList<>();
        List<Employee> list = employeeRepository.findAll();
        for(Employee employee : list){
            EmployeeResponse response = EmployeeResponse.builder()
                    .name(employee.getName())
                    .teamName(employee.getTeam().getName())
                    .role(employee.getRole())
                    .birthday(employee.getBirth())
                    .workStartDate(employee.getHireDate())
                    .build();
            result.add(response);
        }
        return result;
    }

}
