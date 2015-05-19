package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Clas;
import com.attendance.repository.ClassRepository;

@Controller
public class ClassDeleteController {
	@Autowired
	private ClassRepository repository;

	@RequestMapping(value = "/classDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));

		model.addAttribute("title", "クラス削除画面");
		model.addAttribute("message", "本当に削除しますか？");

		Clas clas = repository.findOne(id);
		model.addAttribute("id", id);
		model.addAttribute("class", clas);
		return "/classDelete";
	}

	@RequestMapping(value = "/classDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String repo(Model model, HttpServletRequest request) {
		System.out.println("id=" + request.getParameter("id"));
		Clas clas = repository.findOne(Integer.parseInt(request
				.getParameter("id")));
		repository.delete(clas);
		model.addAttribute("title", "クラス管理画面");
		model.addAttribute("message", "クラス一覧から目的のクラスを検索し、編集・削除等が可能");

		List<Clas> list = repository.findAll();
		model.addAttribute("datalist", list);
		return "/classList";

	}
}