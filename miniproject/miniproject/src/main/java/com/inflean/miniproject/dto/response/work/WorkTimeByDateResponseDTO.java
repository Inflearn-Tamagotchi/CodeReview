package com.inflean.miniproject.dto.response.work;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkTimeByDateResponseDTO {
    private List<Detail> details = new ArrayList<>();
    private Long sum = 0L; // 해당 월간 총 일한 시간

    @Data
    public static class Detail{
        private String date;
        private Long workingMinutes;
        private boolean usingDayOff = false;

        public void usingDayOff(boolean usingDayOff){
            this.usingDayOff = usingDayOff;
        }

    }


    /**
     * detail이란 하나의 객체를 detatils라는
     * 리스트에 add를 해주는 메소드
     * @param detail
     */
    public void addDetails(Detail detail){
        this.details.add(detail);
    }

    /**
     * workingTime이란 minutes를 넣어주면 알아서
     * sum의 값을 구해주는 메소드
     * @param workingMinutes 하루동안 일한 시간
     */
    public void isSum(Long workingMinutes){
        this.sum += workingMinutes;
    }


}
