package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.request.employee.SaveEmployeeRequestDTO;
import com.inflean.miniproject.dto.response.employee.SelectAllEmployeeResponseDTO;
import com.inflean.miniproject.entity.Employee;
import com.inflean.miniproject.enums.Role;
import com.inflean.miniproject.repository.EmployeeRepository;
import com.inflean.miniproject.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void saveEmployee(SaveEmployeeRequestDTO request) {
        Employee employee = Employee.builder()
                .employeeName(request.getName())
                .role(judgmentRole(request.getRole()))
                .birthday(request.getBirthday())
                .workStartDate(LocalDateTime.now().toString())
                .team(teamRepository.findByTeamName(request.getTeamName())
                        .orElseThrow(()-> new RuntimeException("해당 팀은 존재하지않습니다")))
                .build();

        employee.teamEmployeeCount();
        employee.judgmentManager(employee.getRole(), employee.getEmployeeName());

        teamRepository.save(employee.getTeam());
        employeeRepository.save(employee);
    }

    public List<SelectAllEmployeeResponseDTO> selectAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        List<SelectAllEmployeeResponseDTO> dtos = new ArrayList<>();
        for (Employee employee : employees){
            SelectAllEmployeeResponseDTO dto = SelectAllEmployeeResponseDTO.builder()
                    .name(employee.getEmployeeName())
                    .teamName(employee.getTeam().getTeamName())
                    .role(employee.getRole().toString())
                    .birthday(employee.getBirthday())
                    .workStartDate(employee.getWorkStartDate())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }


    /**
     * DTO에서 요청이 들어온 teamName이 String타입의
     * "manager" 혹은 "MANAGER"라면 Role.MANAGER를 반환하고
     * 아니라면 Role.MEMBER을 반환하는 메소드
     * @param role
     * @return Role.MANAGER || Role.MEMBER
     */
    public Role judgmentRole(String role){
        if(role.equals("manager") || role.equals("MANAGER")){
            return Role.MANAGER;
        }else{
            return Role.MEMBER;
        }
    }
}
