package com.inflean.miniproject.repository;

import com.inflean.miniproject.dto.response.SelectAllTeamDTO;
import com.inflean.miniproject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByTeamName(String teamName);


}
