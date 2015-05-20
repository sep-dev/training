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
import com.attendance.entity.Teacher;
import com.attendance.repository.ClassRepository;
import com.attendance.repository.TeacherRepository;
import com.attendance.service.PasswordManager;

@Controller
public class TeacherUpdateController {
	@Autowired
	private ClassRepository class_repository;
	@Autowired
	private TeacherRepository repository;
	@Autowired
	private PasswordManager pm;

	@RequestMapping(value = "/teacherUpdate", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));

		model.addAttribute("title", "講師編集画面");
		model.addAttribute("message", "講師情報の編集が可能");
		// 既存のパスワードのデータを検索し、保存
		pm = new PasswordManager();
		Teacher teacher = repository.findOne(id);
		pm.setForwardHash(teacher.getTeacherPassword());
		model.addAttribute("teacher", teacher);
		List<Clas> class_list = class_repository.findAll();
		model.addAttribute("selectClass", class_list);
		return "/teacherUpdate";
	}

	@RequestMapping(value = "/teacherUpdate", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String repo(@Valid @ModelAttribute Teacher data,HttpServletRequest request, Errors result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "エラー画面");
			model.addAttribute("message", "エラーが発生しました");
			return "/teacherUpdate";
		}else if(!(request.getParameter("passwordConfirm").equals(data.getTeacherPassword()))){
			model.addAttribute("title", "エラー画面");
			model.addAttribute("message", "入力パスワードが異なっています");
			List<Clas> class_list = class_repository.findAll();
			model.addAttribute("selectClass", class_list);
			return "/teacherUpdate";
		} else {
			data.setTeacherPassword(pm.hashCreate(data.getTeacherPassword()));
			repository.saveAndFlush(data);
			model.addAttribute("title", "講師管理画面");
			model.addAttribute("message", "講師一覧から目的の講師を検索し、編集・削除等が可能");
			model.addAttribute("myData", data);
			List<Teacher> list = repository.findAll();
			model.addAttribute("datalist", list);
			return "/teacherList";
		}
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Clas.class, new ClassPropertyEditor(
				class_repository));
	}
}
