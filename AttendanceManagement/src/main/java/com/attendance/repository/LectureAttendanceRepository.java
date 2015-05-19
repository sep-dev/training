package com.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.LectureAttendance;
import com.attendance.entity.LectureAttendancePK;

@Repository
public interface LectureAttendanceRepository extends
		JpaRepository<LectureAttendance, LectureAttendancePK> {

	List<LectureAttendance> findById(LectureAttendancePK id);
}
