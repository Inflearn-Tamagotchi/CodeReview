package api.employee.controller;

import api.employee.model.WorkRecordResponse;
import api.employee.service.AttendanceManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WorkRecordController {

    private final AttendanceManageService attendanceManageService;

    @GetMapping("/member/{memberId}/attendance-records")
    public WorkRecordResponse findMemberAttendanceRecord(@PathVariable(value = "memberId") Long memberId,
                                                      @RequestParam(name = "month") int month) {
        return attendanceManageService.memberMonthAttendanceRecord(memberId, month);
    }

}
