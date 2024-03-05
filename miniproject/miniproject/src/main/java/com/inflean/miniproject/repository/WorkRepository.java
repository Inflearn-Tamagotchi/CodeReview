package com.inflean.miniproject.repository;

import com.inflean.miniproject.entity.Employee;
import com.inflean.miniproject.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    Optional<List<Work>> findByEmployee(Employee employee);
}
