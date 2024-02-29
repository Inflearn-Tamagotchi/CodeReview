package com.example.company.service.employee;

import com.example.company.domain.employee.Employee;
import com.example.company.domain.employee.EmployeeRepository;
import com.example.company.domain.employee.EmployeeRole;
import com.example.company.domain.employee.workhistory.AttendanceRecord;
import com.example.company.domain.employee.workhistory.AttendanceRecordRepository;
import com.example.company.dto.employee.request.EmployeeRequest;
import com.example.company.dto.employee.request.EmployeeWorkEndRequest;
import com.example.company.dto.employee.request.EmployeeWorkStartRequest;
import com.example.company.dto.employee.response.AttendanceDateResponse;
import com.example.company.dto.employee.response.AttendanceDetailResponse;
import com.example.company.dto.employee.response.EmployeeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;

    public EmployeeService(EmployeeRepository employeeRepository, AttendanceRecordRepository attendanceRecordRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRecordRepository = attendanceRecordRepository;
    }

    @Transactional
    public void registerEmployee(EmployeeRequest request) {
        employeeRepository.save(new Employee(request.getName(), request.getTeamName(), request.getRole(), request.getBirthday(), request.getWorkStartDate()));
    }

    @Transactional
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public void startWork(EmployeeWorkStartRequest request) {
        // 등록되지 않은 직원이 출근하려는 경우
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(IllegalArgumentException::new);

        // 출근한 직원이 또 다시 출근하려는 경우
        // 그 날, 출근했다 퇴근한 직원이 다시 출근하려는 경우
        attendanceRecordRepository.findByEmployeeIdAndWorkDate(employee.getId(), LocalDate.now())
                .ifPresent(record -> { throw new IllegalArgumentException("이미 오늘 출근한 직원입니다."); });

        employee.startWork();
    }

    @Transactional
    public void endWork(EmployeeWorkEndRequest request) {
        // 등록되지 않은 직원이 퇴근하려는 경우
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(IllegalArgumentException::new);

        // 퇴근한 직원이 또 다시 퇴근하려는 경우
        attendanceRecordRepository.findByEmployeeIdAndWorkDate(employee.getId(), LocalDate.now())
                .stream().filter(record -> record.getWorkEnd() != null)
                .findFirst()
                .ifPresent(record -> { throw new IllegalArgumentException("이미 퇴근한 직원입니다."); });

        // 퇴근하려는 직원이 출근하지 않았던 경우
        // 해당 직원의 오늘 날짜 출근 기록 찾기
        employee.endWork();
    }

    @Transactional
    public AttendanceDetailResponse getAttendanceDetail(Long employeeId, YearMonth date) {
        // 등록 되지 않은 직원인 경우
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        // 직원 ID와 연/월을 통해 출근 기록 조회
        List<AttendanceRecord> workHistories = attendanceRecordRepository.findAllAttendanceDetail(
                        employee.getId(),
                        date.getYear(),
                        date.getMonthValue())
                .orElseThrow(IllegalArgumentException::new);

        // 날짜별 근무시간을 조회
        List<AttendanceDateResponse> workDate = new ArrayList<>();
        Long sum = 0L;
        for (AttendanceRecord history : workHistories) {
            // 근무 시간
            Long workingMinutes = Duration.between(history.getWorkStart(), history.getWorkEnd()).toMinutes();
            workDate.add(new AttendanceDateResponse(history.getWorkDate(), workingMinutes));
            sum += workingMinutes;
        }
        return new AttendanceDetailResponse(workDate, sum);
    }
}
