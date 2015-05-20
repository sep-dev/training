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

/**
 * Handles requests for the application home page.
 */
@Controller
public class ClassController {
	@Autowired
	private ClassRepository repository;

	@RequestMapping(value = "/classList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(Model model) {
		List<Clas> list = repository.findAll();
		model.addAttribute("datalist", list);
		return "/classList";
	}

	@RequestMapping(value = "/classList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String search(HttpServletRequest request, Model model) {
		String param = request.getParameter("fstr");
		System.out.println(param);
		model.addAttribute("find1", param);
		// 名前・住所であいまい検索
		List<Clas> list = repository.findByClassNameLike("%" + param + "%");
		model.addAttribute("datalist", list);
		return "/classList";
	}
}