package com.inflean.miniproject.repository;

import com.inflean.miniproject.dto.response.SelectAllTeamDTO;
import com.inflean.miniproject.entity.Department;
import com.inflean.miniproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(Department department);



}
