package com.intranet.hr.employee.controller;

import com.intranet.hr.employee.dto.EmployeeRequest;
import com.intranet.hr.employee.dto.EmployeeResponse;
import com.intranet.hr.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * 직원 등록 기능
     * @param requestDto 입력받은 직원 정보
     */
    @PostMapping(value = "/create")
    public void createEmployee(@RequestBody EmployeeRequest requestDto){
        employeeService.createEmployee(requestDto);
    }

    /**
     * 전체 직원 목록 조회 기능
     * @return 전체 직원 목록
     */
    @GetMapping(value = "/list")
    public List<EmployeeResponse> readEmployeeList(){
        return employeeService.readEmployeeList();
    }


}
