package com.intranet.hr.employee.repository;

import com.intranet.hr.employee.entity.Employee;
import com.intranet.hr.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Long countByTeam(Team tam);
}
