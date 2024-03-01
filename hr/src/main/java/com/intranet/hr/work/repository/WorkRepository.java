package com.intranet.hr.work.repository;

import com.intranet.hr.employee.entity.Employee;
import com.intranet.hr.work.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {

    Optional<Work> findByDutyDateAndEmployee(LocalDate dutyDate, Employee employee);
}
