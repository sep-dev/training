package com.attendance.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.dao.AttendanceManagementDao;
import com.attendance.entity.AttendancePastData;
import com.attendance.entity.Lecture;
import com.attendance.entity.LectureAttendance;
import com.attendance.entity.LectureAttendancePK;
import com.attendance.entity.Student;
import com.attendance.entity.TodayLecture;
import com.attendance.form.SearchAttendancePastDataForm;
import com.attendance.helper.ShareHelper;
import com.attendance.repository.LectureAttendanceRepository;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureAttendanceRepository lectureAttendanceRepository;

    @Autowired
    private AttendanceManagementDao amd;

    @Override
    public List<Lecture> getAllLecture() {
        return lectureRepository.findAll();
    }

    @Override
    public List<Lecture> getLectureListfindByLectureDate(Date targetDate) {
        return lectureRepository.findByLectureDate(targetDate);
    }

    @Override
    public List<TodayLecture> getTodayLectureList(Integer studentId) {
        return amd.getTodayLectureList(studentId);
    }

    //出席情報の登録
    @Override
    public boolean addLectureAtttendance(LectureAttendancePK id) {
        if(lectureAttendanceRepository.exists(id)) return false;
        lectureAttendanceRepository.saveAndFlush(new LectureAttendance(id));
        return true;
    }

    //生徒パスワード変更
    @Override
    public boolean updateStudentPassword(Integer studentId,String password) {
        Student student = studentRepository.findByStudentId(studentId);
        String hashedNewPassword = ShareHelper.hashingMd5(password);
        if(student.getStudentPassword().equals(hashedNewPassword)) return false;
        student.setStudentPassword(hashedNewPassword);
        return studentRepository.saveAndFlush(student) != null ? true : false;
    }

    @Override
    public List<AttendancePastData> getAttendancePastData(Integer studentId, SearchAttendancePastDataForm sapdf){
        return amd.getAttendancePastData(studentId, sapdf);
    }

    @Override
    public Map<String, String> getSearchLessonMap() {
        return amd.getSearchLessonMap();
    }

    @Override
    public Map<String, String> getSearcHourMap() {
       return amd.getSearcHourMap();
    }

    @Override
    public String getMinDate(Integer studentId) {
        return amd.getMinDate(studentId);
    }

    @Override
    public Student studentFindByStudentId(Integer studentId) {
        return studentRepository.findByStudentId(studentId);
    }
}
