package com.attendance.service;

import java.util.Date;
import java.util.List;

import com.attendance.entity.Lecture;
import com.attendance.entity.TodayLecture;

public interface StudentService {

    public List<Lecture> findAll();
    public List<Lecture> findByLectureDate(Date lectureDate);
    public List<TodayLecture> getTodayLectureList(Integer studentId); //本日受講可能な授業一覧を取得
}
