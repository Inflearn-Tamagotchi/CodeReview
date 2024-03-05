package com.inflean.miniproject.controller;

import com.inflean.miniproject.dto.request.annualLeave.AnnualLeaveRequestDTO;
import com.inflean.miniproject.service.EmployeeAnnualLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employeeAnnualLeave")
@RequiredArgsConstructor
public class EmployeeAnnualLeaveController {

    private final EmployeeAnnualLeaveService employeeAnnualLeaveService;

    @PostMapping("/requestAnnualLeave")
    public void requestAnnualLeave(@RequestBody AnnualLeaveRequestDTO request){
        employeeAnnualLeaveService.requestAnnualLeave(request);
    }

}
