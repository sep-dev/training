package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Student;
import com.attendance.repository.StudentRepository;

@Controller
public class StudentDeleteController {
	@Autowired
	private StudentRepository repository;

	@RequestMapping(value = "/studentDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));

		model.addAttribute("title", "生徒削除画面");
		model.addAttribute("message", "本当に削除しますか？");

		Student student = repository.findOne(id);
		model.addAttribute("id", id);
		model.addAttribute("student", student);
		return "/studentDelete";
	}

	@RequestMapping(value = "/studentDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String repo(Model model, HttpServletRequest request) {
		System.out.println("id=" + request.getParameter("id"));
		Student student = repository.findOne(Integer.parseInt(request
				.getParameter("id")));
		repository.delete(student);
		model.addAttribute("title", "生徒管理画面");
		model.addAttribute("message", "生徒一覧から目的の生徒を検索し、編集・削除等が可能");

		List<Student> list = repository.findAll();
		model.addAttribute("datalist", list);
		return "/studentList";

	}
}
