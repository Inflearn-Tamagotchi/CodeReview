package com.example.company.domain.employee;

import com.example.company.domain.employee.workhistory.AttendanceRecord;
import com.example.company.dto.employee.request.EmployeeRequest;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(length = 20)
    private String teamName;

    @Column(nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private EmployeeRole role;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private LocalDate workStartDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    protected Employee() {}

    public Employee(String name, String teamName, EmployeeRole role, LocalDate birthday, LocalDate workStartDate) {
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }

    public void startWork() {
        this.attendanceRecords.add(new AttendanceRecord(this));
    }

    public void endWork() {
        AttendanceRecord attendanceRecord = this.attendanceRecords.stream()
                .filter(record -> record.getWorkDate().isEqual(LocalDate.now()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        attendanceRecord.endWork();
    }
}
