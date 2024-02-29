package com.inflean.miniproject.repository;

import com.inflean.miniproject.dto.response.SelectAllTeamDTO;
import com.inflean.miniproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT NEW com.inflean.miniproject.dto.response.SelectAllTeamDTO(d.teamName, " +
            "CASE WHEN e.name = 'manager' THEN e.name ELSE null END, " +
            "COUNT(e.name)) " +
            "FROM Employee e JOIN e.department d " +
            "GROUP BY d.teamName")
    List<SelectAllTeamDTO> SelectAllTeams();
}
