package api.employee.controller;

import api.employee.model.WorkRecordResponse;
import api.employee.service.WorkTimeRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WorkRecordController {

    private final WorkTimeRecordService workTimeRecordService;

    @GetMapping("/member/working-records/{memberId}")
    public WorkRecordResponse findMemberWorkingRecord(@PathVariable(value = "memberId") Long memberId) {
        return workTimeRecordService.memberWorkingRecord(memberId);
    }

}
