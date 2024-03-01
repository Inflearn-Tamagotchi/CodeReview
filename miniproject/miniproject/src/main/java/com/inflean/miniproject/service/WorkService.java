package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.response.WorkTimeMonthDTO;
import com.inflean.miniproject.entity.Employee;
import com.inflean.miniproject.entity.Work;
import com.inflean.miniproject.repository.EmployeeRepository;
import com.inflean.miniproject.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WorkService {
    private final WorkRepository workRepository;
    private final EmployeeRepository employeeRepository;

    public WorkService(WorkRepository workRepository, EmployeeRepository employeeRepository) {
        this.workRepository = workRepository;
        this.employeeRepository = employeeRepository;
    }

    public void startWork(Long idx) {
        Optional<Employee> employee = employeeRepository.findById(idx);
        Work work = Work.builder()
                .state("to work")
                .time(LocalDateTime.now())
                .employee(employee.get())
                .build();
        workRepository.save(work);
    }

    public void endWork(Long idx){
        Optional<Employee> employee = employeeRepository.findById(idx);
        Work work = Work.builder()
                .state("off work")
                .time(LocalDateTime.now())
                .employee(employee.get())
                .build();
        workRepository.save(work);
    }

    public Map<String, Object> workTimeMonth(Long idx, LocalDateTime month){
        Optional<Employee> employee = employeeRepository.findById(idx);
        List<Work> workTimes = workRepository.findByEmployee(employee.get());
        List<Work> monthWorkTimes = new ArrayList<>();

        for (Work workTime : workTimes){
            if (format(workTime.getTime()).equals(month)){
                monthWorkTimes.add(workTime);
            }
        }

        List<Work> toWorks = new ArrayList<>();
        List<Work> offWorks = new ArrayList<>();

        for (Work workTime : monthWorkTimes){
            if(workTime.getState().equals("to work")){
                toWorks.add(workTime);
            }else{
                offWorks.add(workTime);
            }
        }

        Map<String, Object> response = new HashMap<>();
        List<WorkTimeMonthDTO> dtos = new ArrayList<>();
        Long sum = 0L;

        for(int i = 0; i< toWorks.size(); i++){
            LocalDateTime toWork = toWorks.get(i).getTime();
            LocalDateTime offWork = offWorks.get(i).getTime();
            if (format(toWork).equals(format(offWork))){
                WorkTimeMonthDTO dto = new WorkTimeMonthDTO();

                Duration duration = Duration.between(toWork, offWork);
                dto.setDate(format(toWork));
                dto.setWorkMinutes(duration.toMinutes());

                dtos.add(dto);
                sum += duration.toMinutes();
            }
        }
        response.put("detail", dtos);
        response.put("sum", sum);

        return response;

    }

    public String format(LocalDateTime month){
        return month.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
