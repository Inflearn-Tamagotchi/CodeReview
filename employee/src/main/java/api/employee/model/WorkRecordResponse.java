package api.employee.model;

import api.employee.domain.AttendanceStatus;
import api.employee.visitor.AttendanceVisitor;
import api.employee.visitor.Visitor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class WorkRecordResponse {

    private List<Detail> detail;
    private Long sum;

    public WorkRecordResponse(List<WorkRecordResponse.Detail> detail) {
        this.detail = detail;
        this.sum = detail.stream()
                        .mapToLong(Detail::getWorkingMinutes)
                        .sum();
    }

    @Getter
    public static class Detail {
        private LocalDate date;
        private Long workingMinutes;
        private boolean usingDayOff;

        public Detail(LocalDate date, Long workingMinutes, boolean usingDayOff) {
            this.date = date;
            this.workingMinutes = workingMinutes;
            this.usingDayOff = usingDayOff;
        }
    }
}
