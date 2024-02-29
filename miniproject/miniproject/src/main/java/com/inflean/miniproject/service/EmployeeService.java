package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.request.EmployeeSaveDTO;
import com.inflean.miniproject.dto.response.SelectAllTeamDTO;
import com.inflean.miniproject.entity.Employee;
import com.inflean.miniproject.repository.DepartmentRepository;
import com.inflean.miniproject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    /**
     * 현재 래포지토리를 2개를 필드변수로 받는데
     * 이렇게 되면 결속성이 올라간다고 생각이 드는데,
     * 이렇게 만들어도 상관이 없는 걸까요?
     */
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public void saveEmployee(EmployeeSaveDTO request) {
        Employee employee = Employee.builder()
                .name(request.getName())
                .role(request.getRole())
                .birthday(request.getBirthday())
                .workStartDate(request.getWorkStartDate())
                .department(departmentRepository.findByTeamName(request.getTeamName()))
                .build();
        employeeRepository.save(employee);
    }

    public List<SelectAllTeamDTO> selectAllTeam() {
        return employeeRepository.SelectAllTeams();
    }
}
