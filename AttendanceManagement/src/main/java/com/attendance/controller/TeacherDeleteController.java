package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Teacher;
import com.attendance.repository.TeacherRepository;

@Controller
public class TeacherDeleteController {
	@Autowired
	private TeacherRepository repository;

	@RequestMapping(value = "/teacherDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		model.addAttribute("title", "講師削除画面");
		model.addAttribute("message", "本当に削除しますか？");
		Teacher teacher = repository.findOne(id);
		model.addAttribute("id", id);
		model.addAttribute("teacher", teacher);
		return "/teacherDelete";
	}

	@RequestMapping(value = "/teacherDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String repo(Model model, HttpServletRequest request) {
		System.out.println("id=" + request.getParameter("id"));
		Teacher teacher = repository.findOne(Integer.parseInt(request
				.getParameter("id")));
		repository.delete(teacher);
		model.addAttribute("title", "講師管理画面");
		model.addAttribute("message", "講師一覧から目的の生徒を検索し、編集・削除等が可能");
		List<Teacher> list = repository.findAll();
		model.addAttribute("datalist", list);
		return "/teacherList";
	}
}
