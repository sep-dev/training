package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Lecture;
import com.attendance.form.LectureForm;
import com.attendance.repository.LectureRepository;

@Controller
public class LectureDeleteController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private LectureRepository repository;

	@RequestMapping(value = "/lectureDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));

		model.addAttribute("title", "科目削除画面");
		model.addAttribute("message", "本当に削除しますか？");

		Lecture lecture = repository.findOne(id);
		model.addAttribute("id", id);
		model.addAttribute("lecture", lecture);
		String sql = "Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id where lecture_id=?";
		List<LectureForm> al = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<LectureForm>(LectureForm.class),id);
		model.addAttribute("datalist", al);
		return "/lectureDelete";
	}

	@RequestMapping(value = "/lectureDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String repo(Model model, HttpServletRequest request) {
		System.out.println("id=" + request.getParameter("id"));
		Lecture lecture = repository.findOne(Integer.parseInt(request
				.getParameter("id")));
		repository.delete(lecture);
		model.addAttribute("title", "講義管理画面");
		model.addAttribute("message", "講義一覧から目的の科目を検索し、編集・削除等が可能");
		String sql = "Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id ";
		List<LectureForm> al = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<LectureForm>(LectureForm.class));
		model.addAttribute("datalist", al);
		return "/lectureList";

	}
}