package com.attendance.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Lecture;
import com.attendance.form.LectureForm;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.LessonRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LectureController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private LessonRepository lesson_repository;
	@Autowired
	private LectureRepository repository;

	@RequestMapping(value = "/lectureList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(Model model) {
		String sql = "Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id ";
		List<LectureForm> al = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<LectureForm>(LectureForm.class));
		model.addAttribute("datalist", al);
		return "/lectureList";
	}

	@RequestMapping(value = "/lectureList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String search(HttpServletRequest request, Model model) {
		String param = request.getParameter("lessonName");
		String param2 = request.getParameter("teacherName");
		String param3=request.getParameter("date");
		if (param3 == null) {
			param3 = "1800-1-1";
		}
		String param4=request.getParameter("date2");
		if (param4.length() == 0) {
			param4 = "2100-1-1";
		}
		String param5 = request.getParameter("lectureHour");
		model.addAttribute("find1", param);
		model.addAttribute("find2", param2);
		model.addAttribute("find3", param3);
		model.addAttribute("find4", param4);
		model.addAttribute("find5", param5);
		// あいまい検索
		List<Lecture> list = repository.findAll();
		if (param3 != null && param4 != null) {
			String sql = "Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id "
					+ "where  b.lesson_name like ? and c.teacher_name like ? and a.lecture_date between ? and ? and a.lecture_hour like ?";
			List<LectureForm> al = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<LectureForm>(LectureForm.class),
					"%" + param + "%", "%" + param2 + "%", param3, param4, "%"
							+ param5 + "%");
			model.addAttribute("datalist", al);
		}

		return "/lectureList";
	}
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, editor);
	}


}