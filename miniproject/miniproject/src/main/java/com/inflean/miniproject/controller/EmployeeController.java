package com.inflean.miniproject.controller;

import com.inflean.miniproject.dto.request.EmployeeSaveDTO;
import com.inflean.miniproject.dto.response.AllEmployeesDTO;
import com.inflean.miniproject.dto.response.SelectAllTeamDTO;
import com.inflean.miniproject.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public void saveEmployee(@RequestBody EmployeeSaveDTO request){
        employeeService.saveEmployee(request);
    }

    @GetMapping("/selectAllTeam")
    public List<SelectAllTeamDTO> selectAllTeam(){
        return employeeService.selectAllTeam();
    }

    @GetMapping("/allEmployees")
    public List<AllEmployeesDTO> allEmployees(){
        return employeeService.allEmployees();
    }
}
