package api.employee.model;

import api.employee.domain.attendanceRecordType.WorkRecord;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class WorkRecordResponse {

    private List<Detail> detail;
    private Long sum;

    public WorkRecordResponse(List<WorkRecord> workRecords) {
        this.detail = workRecords.stream()
                .map(Detail::new)
                .toList();
        this.sum = workRecords.stream()
                .mapToLong(WorkRecord::getWorkingMinute)
                .sum();
    }

    @Getter
    public static class Detail {
        private LocalDate date;
        private Long workingMinutes;

        public Detail(WorkRecord workRecord) {
            this.date = workRecord.getAttendanceDate();
            this.workingMinutes = workRecord.getWorkingMinute();
        }
    }
}
