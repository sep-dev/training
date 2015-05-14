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


/**
 * Handles requests for the application home page.
 */
@Controller
public class StudentController {
	 @Autowired
     private StudentRepository repository;
	 @RequestMapping(value = "/studentList", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
	        Student data=new Student();
	        model.addAttribute("title","生徒管理画面");
	        model.addAttribute("message","生徒一覧から目的の生徒を検索し、編集・削除等が可能");
	        model.addAttribute("myData",data);
	        List<Student> list = repository.findAll();
	        model.addAttribute("datalist",list);
            int i=0;
	        return "/studentList";
	    }
	    
	    @RequestMapping(value = "/studentList", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String search(HttpServletRequest request,Model model) {

	        String param=request.getParameter("fstr");
	        System.out.println(param);
	        model.addAttribute("title","検索");
	        model.addAttribute("message","「"+param+"」の"+"検索結果");
	        //名前・住所であいまい検索
	        List<Student> list = repository.findByStudentNameLikeOrStudentAddressLike("%"+param+"%","%"+param+"%");
	        model.addAttribute("datalist",list);
	        return "/studentList";
	    }
}
