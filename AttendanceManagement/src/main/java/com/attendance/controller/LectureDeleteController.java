package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Lecture;
import com.attendance.repository.LectureRepository;

@Controller
public class LectureDeleteController {
	 @Autowired
     private LectureRepository repository;
	 @RequestMapping(value = "/lectureDelete", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(HttpServletRequest request,Model model) {
		    int id=Integer.parseInt(request.getParameter("id"));

	        model.addAttribute("title","科目削除画面");
	        model.addAttribute("message","本当に削除しますか？");

	        Lecture lecture = repository.findOne(id);
	        model.addAttribute("id",id);
	        model.addAttribute("lecture",lecture);
	        return "/lectureDelete";
	    }

	   @RequestMapping(value = "/lectureDelete", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(Model model,HttpServletRequest request) {
		    System.out.println("id="+request.getParameter("id"));
		    Lecture lecture = repository.findOne(Integer.parseInt(request.getParameter("id")));
		    repository.delete(lecture);
	        model.addAttribute("title","講義管理画面");
		    model.addAttribute("message","講義一覧から目的の科目を検索し、編集・削除等が可能");

		    List<Lecture> list = repository.findAll();
		    model.addAttribute("datalist",list);
	        return "/lectureList";

	    }
}