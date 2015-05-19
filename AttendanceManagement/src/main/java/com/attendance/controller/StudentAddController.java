package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.dao.ClassPropertyEditor;
import com.attendance.entity.Clas;
import com.attendance.entity.Student;
import com.attendance.repository.ClassRepository;
import com.attendance.repository.StudentRepository;

@Controller
public class StudentAddController {
	@Autowired
	private ClassRepository class_repository;
	@Autowired
	private StudentRepository repository;

	@RequestMapping(value = "/studentAdd", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(Model model) {
		Student studnet = new Student();
		model.addAttribute("title", "生徒新規作成画面");
		model.addAttribute("message", "生徒情報の新規作成が可能");
		model.addAttribute("student", studnet);
		List<Clas> class_list = class_repository.findAll();
		model.addAttribute("selectClass", class_list);
		return "/studentAdd";
	}

	@RequestMapping(value = "/studentAdd", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String repo(@Valid @ModelAttribute Student data, Errors result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "エラー画面");
			model.addAttribute("message", "エラーが発生しました");
			List<Clas> class_list = class_repository.findAll();
			model.addAttribute("selectClass", class_list);
			return "/studentAdd";
		} else if (repository.findByStudentId(data.getStudentId()) != null) {
			model.addAttribute("title", "エラー画面");
			model.addAttribute("message", "IDが重複しています");
			List<Clas> class_list = class_repository.findAll();
			model.addAttribute("selectClass", class_list);
			return "/studentAdd";
		} else {
			repository.saveAndFlush(data);
			model.addAttribute("title", "生徒管理画面");
			model.addAttribute("message", "生徒一覧から目的の生徒を検索し、編集・削除等が可能");
			model.addAttribute("myData", data);
			List<Student> list = repository.findAll();
			model.addAttribute("datalist", list);
			return "/studentList";
		}
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Clas.class, new ClassPropertyEditor(
				class_repository));
	}
}
