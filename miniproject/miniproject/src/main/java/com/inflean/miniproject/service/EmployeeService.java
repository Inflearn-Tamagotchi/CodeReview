package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.request.EmployeeSaveDTO;
import com.inflean.miniproject.dto.response.AllEmployeesDTO;
import com.inflean.miniproject.dto.response.SelectAllTeamDTO;
import com.inflean.miniproject.entity.Department;
import com.inflean.miniproject.entity.Employee;
import com.inflean.miniproject.repository.DepartmentRepository;
import com.inflean.miniproject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<String> teamNames = new ArrayList<>();
        List<Long> memberCounts = new ArrayList<>();
        List<String> managers = new ArrayList<>();
        List<SelectAllTeamDTO> dtos = new ArrayList<>();

        /**
         * DB에서 select문을 통해 상위객체에 세팅
         */
        for(Department department : departmentRepository.findAll()){
            Long count = 0L;
            String manager = null;
            teamNames.add(department.getTeamName());
            for(Employee employee : employeeRepository.findByDepartment(department)){
                if(employee.getRole().equals("manager")){
                    manager = employee.getName();
                }
                count++;
            }
            memberCounts.add(count);
            managers.add(manager);
        }

        /**
         * 최상단 객체값을 list배열의 dto에 맞게 세팅
         */
        for(int i = 0; i<teamNames.size(); i++){
            SelectAllTeamDTO dto = new SelectAllTeamDTO();
            dto.setTeamName(teamNames.get(i));
            dto.setManager(managers.get(i));
            dto.setMemberCount(memberCounts.get(i));
            dtos.add(dto);
        }
        return dtos;
    }

    public List<AllEmployeesDTO> allEmployees(){
        List<AllEmployeesDTO> dtos = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for(Employee employee : employees){
            dtos.add(new AllEmployeesDTO(
                    employee.getName(),
                    employee.getDepartment().getTeamName(),
                    employee.getRole(),
                    employee.getBirthday(),
                    employee.getWorkStartDate()
            ));
        }
        return dtos;
    }

}
