package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.response.work.WorkTimeByDateResponseDTO;
import com.inflean.miniproject.entity.Work;
import com.inflean.miniproject.enums.State;
import com.inflean.miniproject.repository.EmployeeRepository;
import com.inflean.miniproject.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        WorkTimeByDateResponseDTO.Detail detail = new WorkTimeByDateResponseDTO.Detail();
        List<WorkTimeByDateResponseDTO.Detail> details = new ArrayList<>();
        Long sum = 0L;

        for(int i = 0; i< workList.size(); i++){
            Long workingTime = workList.get(i).startTimeMinusEndTime();
            if(formatMonthTime(workList.get(i).getWorkStartTime()).equals(selectDate)){
                detail.setDate(workList.get(i).getWorkStartTime().split(" ")[0]);
                detail.setWorkingMinutes(workingTime);
                details.add(detail);
            }
            sum += workingTime;
        }

        WorkTimeByDateResponseDTO dto = new WorkTimeByDateResponseDTO();
        dto.setDetail(details);
        dto.setSum(sum);

        return dto;
    }

    public String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    public String formatMonthTime(String entityTime) {
        entityTime = entityTime.substring(0, 7);
        return entityTime;
    }
}
