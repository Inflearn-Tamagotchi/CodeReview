package com.intranet.hr.work.service;

import com.intranet.hr.employee.entity.Employee;
import com.intranet.hr.employee.repository.EmployeeRepository;
import com.intranet.hr.work.dto.WorkCheckRequest;
import com.intranet.hr.work.entity.Work;
import com.intranet.hr.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final EmployeeRepository employeeRepository;

    public void checkWork(WorkCheckRequest requestDto) {
        Employee employee = employeeRepository.findById(requestDto.getId())
                .orElseThrow(IllegalArgumentException::new);

        Optional<Work> existingWorkOptional = workRepository.findByDutyDateAndEmployee(LocalDate.now(), employee);
        if (existingWorkOptional.isPresent()) { //퇴근
            Work existingWork = existingWorkOptional.get();
            existingWork.updateDuty();
            workRepository.save(existingWork);
        } else { //출근
            Work work = Work.builder()
                    .status(requestDto.getWorkStatus())
                    .employee(employee)
                    .build();
            workRepository.save(work);
        }
    }
}
