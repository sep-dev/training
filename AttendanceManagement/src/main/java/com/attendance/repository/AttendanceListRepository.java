package com.attendance.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.LectureAttendance;
import com.attendance.entity.LectureAttendancePK;

@Repository
public interface AttendanceListRepository extends JpaRepository<LectureAttendance,Integer> {

    public LectureAttendancePK findById(LectureAttendancePK id);

}
