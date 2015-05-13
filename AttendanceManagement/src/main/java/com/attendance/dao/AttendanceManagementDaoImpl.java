package com.attendance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.attendance.entity.TodayLecture;
import com.attendance.helper.Helper;

@Component
public class AttendanceManagementDaoImpl implements AttendanceManagementDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public List<TodayLecture> getTodayLectureList(Integer studentId) {
        List<TodayLecture> list = jdbcTemplate.query(this.getLectureListQuery(), new RowMapper<TodayLecture>(){
            @Override
            public TodayLecture mapRow(ResultSet rs, int rowNum) throws SQLException {
                TodayLecture lecture = new TodayLecture();
                lecture.setLectureId(rs.getInt("lecture_id"));
                lecture.setLessonName(rs.getString("lesson_name"));
                lecture.setHour(rs.getInt("hour"));
                lecture.setTeacherName(rs.getString("teacher_name"));
                lecture.setAttendanceState(rs.getInt("attendance_state"));
                return lecture;
            }
        },Helper.formatHyphenDate(new Date()),studentId);
        return list;
    }

    //本日の受講可能講義一覧を取得するクエリを格納
    private String getLectureListQuery(){
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
        .append("A.lecture_id as 'lecture_id' ,B.lesson_name as 'lesson_name',")
        .append("A.lecture_hour as 'hour',C.teacher_name as 'teacher_name',")
        .append("coalesce(D.lecture_id,0,1) as 'attendance_state' ")
        .append("FROM ")
        .append("(SELECT lecture_id,lesson_id,lecture_hour FROM lectures WHERE lecture_date = ?) as A ")
        .append("INNER JOIN ")
        .append("lessons as B ")
        .append("ON A.lesson_id = B.lesson_id ")
        .append("INNER JOIN ")
        .append("teachers as C ")
        .append("ON B.lesson_teacher_id = C.teacher_id ")
        .append("LEFT OUTER JOIN ")
        .append("(SELECT lecture_id FROM lecture_attendance WHERE student_id = ?) as D ")
        .append("ON A.lecture_id = D.lecture_id ")
        .append("ORDER BY A.lecture_hour ASC");
        return query.toString();
    }
}
