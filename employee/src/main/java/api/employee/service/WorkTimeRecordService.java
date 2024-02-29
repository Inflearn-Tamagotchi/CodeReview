package api.employee.service;

import api.employee.domain.Member;
import api.employee.domain.attendanceRecordType.WorkRecord;
import api.employee.model.WorkRecordResponse;
import api.employee.repository.AttendanceStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkTimeRecordService {

    private final AttendanceStatusRepository attendanceRepository;

    public void recordArrivalTime(Member member, LocalDate attendanceDate, LocalTime startTime) {
        WorkRecord workRecord = WorkRecord.recordStartTime(member, attendanceDate, startTime);
        attendanceRepository.save(workRecord);
    }

    @Transactional
    public void recordDepartureTime(Long memberId, LocalDate attendanceDate, LocalTime endTime) {
        log.info("memberId = {}, attendanceDate = {}, endTime = {}", memberId, attendanceDate, endTime);
        WorkRecord workRecord = attendanceRepository.findUnrecordedData(memberId, attendanceDate)
                .orElseThrow(() -> new IllegalArgumentException("출근기록이 없습니다."));
        log.info(workRecord.getStartTime().toString());
        workRecord.recordEndTime(endTime);
    }

    public WorkRecordResponse memberWorkingRecord(Long memberId) {
        List<WorkRecord> workRecords = attendanceRepository.findAllWorkingRecordById(memberId);
        return new WorkRecordResponse(workRecords);
    }

}
