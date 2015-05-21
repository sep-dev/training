package com.attendance.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.domain.AccessUser;
import com.attendance.form.AttendForm;
import com.attendance.helper.ShareHelper;
import com.attendance.repository.AttendanceListRepository;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.StudentRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/manager")
public class AttendanceListController extends AccessController{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private LectureRepository lecture_repository;
    @Autowired
    private StudentRepository student_repository;
    @Autowired
    private AttendanceListRepository repository;

    @RequestMapping(value = "/attendList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String showList(Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        String sql = "Select * from lecture_attendance as a inner join students as b on a.student_id=b.student_id inner join lectures as c on a.lecture_id=c.lecture_id inner join lessons as d on c.lesson_id=d.lesson_id;";
        List<AttendForm> al = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<AttendForm>(AttendForm.class));
        model.addAttribute("datalist", al);
        model.addAttribute("count", al.size());
        return "/attendList";
    }

    @RequestMapping(value = "/attendList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String searchList(HttpServletRequest request, Model model) {
        String param = request.getParameter("student_name");
        String param2 = request.getParameter("lecture_name");
        String param3 = request.getParameter("lecture_date");
        String param4 = request.getParameter("lecture_date2");
        if (param4.length() == 0) {
            param4 = ShareHelper.getToday();
        }
        String param5 = request.getParameter("lecture_hour");
        model.addAttribute("find1", param);
        model.addAttribute("find2", param2);
        model.addAttribute("find3", param3);
        model.addAttribute("find4", param4);
        model.addAttribute("find5", param5);
        // 名前・住所であいまい検索
        String sql = "Select * from lecture_attendance as a inner join students as b on a.student_id=b.student_id "
                + "inner join lectures as c on a.lecture_id=c.lecture_id inner join lessons as d on c.lesson_id=d.lesson_id "
                + "where b.student_name like ? and d.lesson_name like ? and c.lecture_date between ? and ? and c.lecture_hour like ?;";
        List<AttendForm> al = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<AttendForm>(AttendForm.class), "%"
                        + param + "%", "%" + param2 + "%", param3, param4, "%"
                        + param5 + "%");
        model.addAttribute("datalist", al);
        model.addAttribute("count", al.size());
        return "/attendList";
    }
}
