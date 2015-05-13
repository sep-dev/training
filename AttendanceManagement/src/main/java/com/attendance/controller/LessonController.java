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


/**
 * Handles requests for the application home page.
 */
@Controller
public class LessonController {
	 @Autowired
     private LessonRepository repository;
	 @RequestMapping(value = "/lessonList", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
	        Lesson data=new Lesson();
	        model.addAttribute("title","科目管理画面");
	        model.addAttribute("message","科目一覧から目的の科目を検索し、編集・削除等が可能");
	        model.addAttribute("myData",data);
	        
	        List<Lesson> list = repository.findAll();
	        model.addAttribute("datalist",list);

	        return "/lessonList";
	    }

	    @RequestMapping(value = "/lessonList", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String search(HttpServletRequest request,Model model) {

	        String param=request.getParameter("fstr");
	        System.out.println(param);
	        model.addAttribute("title","検索");
	        model.addAttribute("message","「"+param+"」の"+"検索結果");
	        //名前・住所であいまい検索
	        List<Lesson> list = repository.findByLessonNameLike("%"+param+"%");
	        model.addAttribute("datalist",list);
	        return "/lessonList";
	    }
}

