package com.intranet.hr.work.controller;

import com.intranet.hr.work.dto.WorkCheckRequest;
import com.intranet.hr.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    /**
     * 출퇴근 저장 기능
     * @param requestDto 입력받는 출퇴근자 관련 정보
     */
    @PostMapping(value = "/check")
    public void checkWork(@RequestBody WorkCheckRequest requestDto) {
        workService.checkWork(requestDto);
    }

}
