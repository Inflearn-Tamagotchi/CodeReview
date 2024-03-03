package com.inflean.miniproject.controller;

import com.inflean.miniproject.dto.request.employee.SaveEmployeeRequestDTO;
import com.inflean.miniproject.dto.response.employee.SelectAllEmployeeResponseDTO;
import com.inflean.miniproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/save")
    public void saveEmployee(@RequestBody SaveEmployeeRequestDTO request){
        employeeService.saveEmployee(request);
    }

    @GetMapping("/select-all-employee")
    public List<SelectAllEmployeeResponseDTO> selectAllEmployee(){
        return employeeService.selectAllEmployee();
    }
}
