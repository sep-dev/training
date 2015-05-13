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
import com.attendance.repository.LessonRepository;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LectureController {
	 @Autowired
     private LessonRepository lesson_repository;
	 @Autowired
     private LectureRepository repository;
	 @RequestMapping(value = "/lectureList", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
	        Lecture data=new Lecture();
	        model.addAttribute("title","講義管理画面");
	        model.addAttribute("message","講義一覧から目的の講義を検索し、編集・削除等が可能");
	        model.addAttribute("myData",data);

	        List<Lecture> list = repository.findAll();
	        model.addAttribute("datalist",list);

	        return "/lectureList";
	    }

	    @RequestMapping(value = "/lectureList", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String search(HttpServletRequest request,Model model) {

	        String param=request.getParameter("fstr");
	        System.out.println(param);
	        model.addAttribute("title","検索");
	        model.addAttribute("message","「"+param+"」の"+"検索結果");
	        //あいまい検索
	       // List<Lecture> list = repository.findByLectureNameLike("%"+param+"%");
	        model.addAttribute("datalist",null);
	        return "/lectureList";
	    }
}