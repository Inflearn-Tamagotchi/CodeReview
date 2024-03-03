package com.inflean.miniproject.controller;

import com.inflean.miniproject.dto.response.work.WorkTimeByDateResponseDTO;
import com.inflean.miniproject.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/work")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @GetMapping("/to-work/{employeeId}")
    public void toWork(@PathVariable("employeeId") Long employeeId){
        workService.toWork(employeeId);
    }

    @PutMapping("/off-work/{workId}")
    public void offWork(@PathVariable("workId") Long workId){
        workService.offWork(workId);
    }

    @GetMapping("/work-time-by-date/{employeeId}/{selectDate}")
    public WorkTimeByDateResponseDTO workTimeByDate(@PathVariable("employeeId") Long employeeId,
                                                    @PathVariable("selectDate") String selectDate){
        return workService.workTimeByDate(employeeId, selectDate);
    }

}
