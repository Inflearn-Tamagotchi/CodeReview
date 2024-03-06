package com.inflean.miniproject.controller;

import com.inflean.miniproject.dto.request.annualLeave.AnnualLeaveRequestDTO;
import com.inflean.miniproject.service.EmployeeAnnualLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/employeeAnnualLeave")
@RequiredArgsConstructor
public class EmployeeAnnualLeaveController {

    private final EmployeeAnnualLeaveService employeeAnnualLeaveService;

    @PostMapping("/requestAnnualLeave")
    public void requestAnnualLeave(@RequestBody AnnualLeaveRequestDTO request){
        employeeAnnualLeaveService.requestAnnualLeave(request);
    }

    @GetMapping("/selectAnnualLeave/{employeeId}")
    public Map<String, Object> selectAnnualLeave(@PathVariable("employeeId") Long employeeId){
        return employeeAnnualLeaveService.selectAnnualLeave(employeeId);
    }

}
