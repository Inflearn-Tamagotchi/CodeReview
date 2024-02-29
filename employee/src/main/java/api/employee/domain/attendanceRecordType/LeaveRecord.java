package api.employee.domain.attendanceRecordType;

import api.employee.domain.AttendanceStatus;
import api.employee.domain.Member;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@DiscriminatorValue("leave")
public class LeaveRecord extends AttendanceStatus {

    private String reason;

    private LeaveRecord(Member member, LocalDate attendanceDate, String reason) {
        super(member, attendanceDate);
        this.reason = reason;
        super.getMember().usingLeave();
    }

    public static LeaveRecord reason(Member member, LocalDate attendanceDate, String reason) {
        return new LeaveRecord(member, attendanceDate, reason);
    }

}
