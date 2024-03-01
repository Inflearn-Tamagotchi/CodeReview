package com.intranet.hr.work.dto;

import com.intranet.hr.work.entity.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkCheckRequest {

    private Long id;
    private WorkStatus workStatus;

}
