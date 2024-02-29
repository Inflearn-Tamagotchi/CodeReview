package com.inflean.miniproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Getter @Setter
@Table(name = "EMPLOYEES")
@NoArgsConstructor
public class Employee {
    @Id
    @Column(name = "EMPLOYEE_IDX")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeIdx;

    @Column(nullable = false)
    private String name;

    @Column
    private String role;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private LocalDate workStartDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_IDX")
    private Department department;

    @Builder
    public Employee(Long employeeIdx, String name, String role, LocalDate birthday, LocalDate workStartDate, Department department) {
        this.employeeIdx = employeeIdx;
        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
        this.department = department;
    }
}
