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


/**
 * Handles requests for the application home page.
 */
@Controller
public class TeacherController {
	 @Autowired
     private TeacherRepository repository;
	 @RequestMapping(value = "/teacherList", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
	        Teacher data=new Teacher();
	        model.addAttribute("title","講師管理画面");
	        model.addAttribute("message","講師一覧から目的の講師を検索し、編集・削除等が可能");
	        model.addAttribute("myData",data);
	        List<Teacher> list = repository.findAll();
	        model.addAttribute("datalist",list);

	        return "/teacherList";
	    }

	    @RequestMapping(value = "/teacherList", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String search(HttpServletRequest request,Model model) {

	        String param=request.getParameter("fstr");
	        System.out.println(param);
	        model.addAttribute("title","検索");
	        model.addAttribute("message","「"+param+"」の"+"検索結果");
	        //名前・住所であいまい検索
	        List<Teacher> list = repository.findByTeacherNameLikeOrTeacherAddressLike("%"+param+"%","%"+param+"%");
	        model.addAttribute("datalist",list);
	        return "/teacherList";
	    }
}