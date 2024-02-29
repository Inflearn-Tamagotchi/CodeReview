package api.employee.service;

import api.employee.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceStatusService {

    private final MemberService memberService;
    private final WorkTimeRecordService workTimeRecordService;

    @Transactional
    public void recordArrivalTime(Long memberId, LocalDate attendanceDate, LocalTime startTime) {
        Member member = memberService.findMemberById(memberId);
        workTimeRecordService.recordArrivalTime(member, attendanceDate, startTime);
    }
}
