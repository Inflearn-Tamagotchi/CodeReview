package com.inflean.miniproject.dto.request.annualLeave;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnualLeaveRequestDTO {

    private Long employeeIdx;
    private String startDate;
    private String endDate;
}
