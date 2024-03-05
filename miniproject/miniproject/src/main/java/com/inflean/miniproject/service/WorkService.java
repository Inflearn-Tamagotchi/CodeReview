package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.response.work.WorkTimeByDateResponseDTO;
import com.inflean.miniproject.entity.Work;
import com.inflean.miniproject.enums.State;
import com.inflean.miniproject.repository.EmployeeRepository;
import com.inflean.miniproject.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    private final WorkRepository workRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void toWork(Long employeeId){
        Work work = Work.builder()
                .state(State.TO_WORK)
                .workStartTime(format(LocalDateTime.now()))
                .employee(employeeRepository.getReferenceById(employeeId))
                .build();
        workRepository.save(work);
    }

    @Transactional()
    public void offWork(Long workId){
        // 예외처리 및 id값으로 work객체 찾아오기
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new RuntimeException("등록되지 않거나 유효하지 않은 직원입니다."));

        // 업데이트 메소드
        work.updateWork(State.OFF_WORK, format(LocalDateTime.now()));
    }

    public WorkTimeByDateResponseDTO workTimeByDate(Long employeeId, String selectDate) {

        List<Work> workList = workRepository.findByEmployee(employeeRepository.getReferenceById(employeeId))
                .orElseThrow(() -> new RuntimeException("등록되지 않거나 유효하지 않은 직원입니다."));

        // 빈 객체 생성
        WorkTimeByDateResponseDTO dto = new WorkTimeByDateResponseDTO();

        for (Work work : workList){
            log.info(work.toString());
             /* 매개변수 selectDate과 해당되는 회원 id의
            날짜가 일치하는 지 확인하는 조건문 */
            if(formatMonthTime(work.getWorkStartTime()).equals(selectDate)){

                // wokingTime, date 세팅해주기
                Long workingMinutes = startTimeMinusEndTime(work.getWorkStartTime(), work.getWorkEndTime());
                String date = formatDayMonthTime(work.getWorkStartTime());

                WorkTimeByDateResponseDTO.Detail detail = new WorkTimeByDateResponseDTO.Detail();
                // addDetails 를 통해 반환할 객체의 details 리스트에 detail 을 add 해준다.
                detail.setDate(date);
                detail.setWorkingMinutes(workingMinutes);
                dto.addDetails(detail);

                // sum을 해주는 메소드사용
                dto.isSum(workingMinutes);
            }
        }

        return dto;
    }

    /* LocalDate 를 연월일시분초로 변환해주는 메소드
       ex) 2024-03-04T19:45:38.78016 -> 2024-03-04 19:45 */
    public String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    /* 연월일시분초를 연월로 변환해주는 메소드
       ex) 2024-03-04 12:00 -> 2024-03 */
    public String formatMonthTime(String entityTime) {
        entityTime = entityTime.substring(0, 7);
        return entityTime;
    }

    /* 연월일시분을 연월일로 변환해주는 메소드
       ex) */
    public String formatDayMonthTime(String time){
        return time.split(" ")[0];
    }

    /**
     * workStartTime 와 workEndTime 을 빼서
     * 분단위로 converting 후 반환하는 메소드
     * @return Long타입의 분단위 숫자반환
     */
    public Long startTimeMinusEndTime(String workStartTime, String workEndTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(workStartTime, formatter);
        LocalDateTime end = LocalDateTime.parse(workEndTime, formatter);
        return Duration.between(start, end).toMinutes();
    }
}
