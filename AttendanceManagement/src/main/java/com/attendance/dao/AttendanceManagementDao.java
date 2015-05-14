package com.attendance.dao;

import java.util.List;

import com.attendance.entity.TodayLecture;

public interface AttendanceManagementDao {

    public List<TodayLecture> getTodayLectureList(Integer studentId);

}
