package com.inflean.miniproject.dto.response.work;

import lombok.Data;

import java.util.List;

@Data
public class WorkTimeByDateResponseDTO {
    private List<Detail> detail;
    private Long sum;

    @Data
    public static class Detail{
        private String date;
        private Long workingMinutes;
    }
}
