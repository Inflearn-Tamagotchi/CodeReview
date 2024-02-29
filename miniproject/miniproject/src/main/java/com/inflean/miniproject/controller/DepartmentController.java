package com.inflean.miniproject.controller;

import com.inflean.miniproject.dto.request.DepartmentSaveDTO;
import com.inflean.miniproject.dto.response.SelectAllTeamDTO;
import com.inflean.miniproject.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/save")
    public void saveDepartment(@RequestBody DepartmentSaveDTO request){
        departmentService.saveDepartment(request);
    }


}
