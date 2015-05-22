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

import com.attendance.form.AttendForm;
import com.attendance.helper.ShareHelper;
import com.attendance.repository.AttendanceListRepository;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.StudentRepository;
import com.attendance.search.SerchAttend;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/manager")
public class AttendanceListController {
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
	@Autowired
	private SerchAttend search;

	@RequestMapping(value = "/attendList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(Model model) {
		AttendForm af = new AttendForm();

		model.addAttribute("title", "出席生徒画面");
		model.addAttribute("message", "出席生徒一覧から目的の生徒を検索可能");
		String sql = "Select * from lecture_attendance as a inner join students as b on a.student_id=b.student_id inner join lectures as c on a.lecture_id=c.lecture_id inner join lessons as d on c.lesson_id=d.lesson_id;";
		List<AttendForm> al = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AttendForm>(AttendForm.class));
		model.addAttribute("datalist", al);

		return "/attendList";
	}

	@RequestMapping(value = "/attendList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String search(HttpServletRequest request, Model model) {
		String studentName = request.getParameter("student_name");
		String lectureName = request.getParameter("lecture_name");
		String date1 = request.getParameter("lecture_date");
		String date2 = request.getParameter("lecture_date2");
		if (date2.length() == 0) {
			date2 = ShareHelper.getToday();
		}
		String hour = request.getParameter("lecture_hour");
		model.addAttribute("find1", studentName);
        model.addAttribute("find2", lectureName);
        model.addAttribute("find3", date1);
        model.addAttribute("find4", date2);
        model.addAttribute("find5", hour);
		// 名前・住所であいまい検索
		model.addAttribute("datalist", search.getList(studentName, lectureName, date1, date2, hour));
		return "/attendList";
	}
}
