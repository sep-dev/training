package com.attendance.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.attendance.form.LectureForm;


@Component
public class SerchLecture {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LectureForm> getList(String lessonName,String teacherName,String date1,String date2,String hour){
        List<LectureForm> lf = null ;
         // あいまい検索
        if (date1.length() == 0) {
            date1 = "2010-01-01";
        }
        if (date2.length() == 0) {
            date2 = "2100-01-01";
        }
        if (date1 != null && date2 != null) {
            String sql = "Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id "
                    + "where  b.lesson_name like ? and c.teacher_name like ? and a.lecture_date between ? and ? and a.lecture_hour like ?";
            lf = jdbcTemplate.query(sql,new BeanPropertyRowMapper<LectureForm>(LectureForm.class),
                    "%" + lessonName + "%", "%" +teacherName + "%", date1, date2, "%"
                            + hour + "%");
        }
        return lf;
    }
    public List<LectureForm> getAll(){
        List<LectureForm> lf =null;
        // 全検索
        String sql = "Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id ";
        lf = jdbcTemplate.query(sql,
                 new BeanPropertyRowMapper<LectureForm>(LectureForm.class));
        return lf;
    }
    public List<LectureForm> getOne(int id){
        List<LectureForm> lf =null;
        // ID該当者検索
        String sql = "Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id where lecture_id=?";
        lf = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<LectureForm>(LectureForm.class),id);
        return lf;
    }
}
