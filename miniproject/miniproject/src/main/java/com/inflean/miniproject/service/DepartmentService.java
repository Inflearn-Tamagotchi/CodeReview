package com.inflean.miniproject.service;

import com.inflean.miniproject.dto.request.DepartmentSaveDTO;
import com.inflean.miniproject.dto.response.SelectAllTeamDTO;
import com.inflean.miniproject.entity.Department;
import com.inflean.miniproject.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void saveDepartment(DepartmentSaveDTO request) {
        Department department = Department.builder()
                .teamName(request.getTeamName())
                .build();
        departmentRepository.save(department);
    }

}
