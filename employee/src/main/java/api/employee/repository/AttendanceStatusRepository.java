package api.employee.repository;

import api.employee.domain.AttendanceStatus;
import api.employee.domain.attendanceRecordType.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceStatusRepository extends JpaRepository<AttendanceStatus, Long> {

    @Query("select a " +
            " from AttendanceStatus a " +
            "where a.member.id = :memberId " +
            "and a.attendanceDate = :attendanceDate " +
            "and a.endTime is null")
    Optional<WorkRecord> findUnrecordedData(@Param(value = "memberId") Long memberId, @Param(value = "attendanceDate") LocalDate attendanceDate);

    @Query("select a " +
            " from AttendanceStatus a " +
            "where a.member.id = :memberId ")
    List<WorkRecord> findAllWorkingRecordById(@Param(value = "memberId") Long memberId);
}
