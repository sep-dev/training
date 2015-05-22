package com.attendance.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.attendance.form.LectureForm;


@Service
public class SerchLecture {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    List<LectureForm> al ;
    public List<LectureForm> getList(String lessonName,String teacherName,String time1,String time2,String hour){
         // あいまい検索
        if (time1 != null && time2 != null) {
            String sql = "Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id "
                    + "where  b.lesson_name like ? and c.teacher_name like ? and a.lecture_date between ? and ? and a.lecture_hour like ?";
            al = jdbcTemplate.query(sql,new BeanPropertyRowMapper<LectureForm>(LectureForm.class),
                    "%" + lessonName + "%", "%" +teacherName + "%", time1, time2, "%"
                            + hour + "%");
        }
        return al;
    }
}
