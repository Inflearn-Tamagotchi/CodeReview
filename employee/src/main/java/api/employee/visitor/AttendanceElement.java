package api.employee.visitor;

import api.employee.model.WorkRecordResponse;

public interface AttendanceElement {

    WorkRecordResponse.Detail accept(Visitor visitor);

}
