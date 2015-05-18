package com.attendance.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.attendance.entity.AttendancePastData;
import com.attendance.entity.Lecture;
import com.attendance.entity.LectureAttendancePK;
import com.attendance.entity.Student;
import com.attendance.entity.TodayLecture;
import com.attendance.form.SearchAttendancePastDataForm;

public interface StudentService {

	Student studentFindByStudentId(Integer studentId);

	List<Lecture> getAllLecture();

	List<Lecture> getLectureListfindByLectureDate(Date lectureDate);

	List<TodayLecture> getTodayLectureList(Integer studentId); // 引数に渡された生徒IDから、その生徒が本日受講可能講義一覧取得を取得する

	boolean addLectureAtttendance(LectureAttendancePK id);

	boolean updateStudentPassword(Integer studentId, String password);

	List<AttendancePastData> getAttendancePastData(Integer studentId,
			SearchAttendancePastDataForm sapdf);

	String getMinDate(Integer studentId);

	Map<String, String> getSearcHourMap();

	Map<String, String> getSearchLessonMap();
}
