package api.employee.visitor;

import api.employee.domain.attendanceRecordType.LeaveRecord;
import api.employee.domain.attendanceRecordType.WorkRecord;
import api.employee.model.WorkRecordResponse;

public interface Visitor {

    WorkRecordResponse.Detail visitWorkRecord(WorkRecord workRecord);
    WorkRecordResponse.Detail visitLeaveRecord(LeaveRecord leaveRecord);

}
