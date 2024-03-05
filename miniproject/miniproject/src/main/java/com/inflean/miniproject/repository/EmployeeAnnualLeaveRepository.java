package com.inflean.miniproject.repository;

import com.inflean.miniproject.entity.EmployeeAnnualLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAnnualLeaveRepository extends JpaRepository<EmployeeAnnualLeave, Long> {

}
