package com.attendance.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.attendance.form.AttendForm;


@Service
public class SerchAttend {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    List<AttendForm> al ;
    public List<AttendForm> getList(String studentName,String lectureName,String date1,String date2,String hour){
         // あいまい検索
        if (date1 != null && date2 != null) {
            String sql = "Select * from lecture_attendance as a inner join students as b on a.student_id=b.student_id "
                    + "inner join lectures as c on a.lecture_id=c.lecture_id inner join lessons as d on c.lesson_id=d.lesson_id "
                    + "where b.student_name like ? and d.lesson_name like ? and c.lecture_date between ? and ? and c.lecture_hour like ?;";
            al = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<AttendForm>(AttendForm.class), "%"
                            + studentName + "%", "%" + lectureName + "%", date1, date2, "%"
                            + hour + "%");
        }
        return al;
    }
}
