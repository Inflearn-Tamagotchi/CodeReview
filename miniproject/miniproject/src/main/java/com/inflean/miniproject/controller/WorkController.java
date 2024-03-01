package com.inflean.miniproject.controller;

import com.inflean.miniproject.service.WorkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/work")
public class WorkController {
    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/startWork")
    public void startWork(@RequestParam("employee_idx") Long idx){
        workService.startWork(idx);
    }

    @GetMapping("/endWork")
    public void endWork(@RequestParam("employee_idx") Long idx){
        workService.endWork(idx);
    }

    @GetMapping("/workTimeMonth")
    public Map<String, Object> workTimeMonth(@RequestParam("employee_idx") Long idx,
                                             @RequestParam("month") LocalDateTime month){
        return workService.workTimeMonth(idx, month);
    }
}
