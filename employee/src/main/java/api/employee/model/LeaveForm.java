package api.employee.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LeaveForm {

    private LocalDate requestLeaveDay;
    private String reason;

}
