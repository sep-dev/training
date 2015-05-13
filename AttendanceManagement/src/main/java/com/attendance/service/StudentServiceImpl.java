package com.attendance.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.dao.AttendanceManagementDao;
import com.attendance.entity.Lecture;
import com.attendance.entity.TodayLecture;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentReposutory;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private AttendanceManagementDao amd;

    @Override
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Override
    public List<Lecture> findByLectureDate(Date targetDate) {
        return lectureRepository.findByLectureDate(targetDate);
    }

    @Override
    public List<TodayLecture> getTodayLectureList(Integer studentId) {
        return amd.getTodayLectureList(studentId);
    }

}
