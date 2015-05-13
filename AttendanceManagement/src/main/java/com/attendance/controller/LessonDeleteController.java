package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Lesson;
import com.attendance.repository.LessonRepository;

@Controller
public class LessonDeleteController {
	 @Autowired
     private LessonRepository repository;
	 @RequestMapping(value = "/lessonDelete", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(HttpServletRequest request,Model model) {
		    int id=Integer.parseInt(request.getParameter("id"));

	        model.addAttribute("title","科目削除画面");
	        model.addAttribute("message","本当に削除しますか？");

	        Lesson lesson = repository.findOne(id);
	        model.addAttribute("id",id);
	        model.addAttribute("lesson",lesson);
	        return "/lessonDelete";
	    }

	   @RequestMapping(value = "/lessonDelete", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(Model model,HttpServletRequest request) {
		    System.out.println("id="+request.getParameter("id"));
		    Lesson lesson = repository.findOne(Integer.parseInt(request.getParameter("id")));
		    repository.delete(lesson);
	        model.addAttribute("title","科目管理画面");
		    model.addAttribute("message","科目一覧から目的の科目を検索し、編集・削除等が可能");

		    List<Lesson> list = repository.findAll();
		    model.addAttribute("datalist",list);
	        return "/lessonList";

	    }
}