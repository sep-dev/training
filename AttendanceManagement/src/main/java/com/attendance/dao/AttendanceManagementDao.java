package com.attendance.dao;

import java.util.List;
import java.util.Map;

import com.attendance.entity.AttendancePastData;
import com.attendance.entity.TodayLecture;
import com.attendance.form.SearchAttendancePastDataForm;

public interface AttendanceManagementDao {

	List<TodayLecture> getTodayLectureList(Integer studentId);

	List<AttendancePastData> getAttendancePastData(Integer studentId,
			SearchAttendancePastDataForm sapdf);

	String getMinDate(Integer studentId);

	// 過去情報検索欄用
	Map<String, String> getSearcHourMap();

	Map<String, String> getSearchLessonMap();

}
