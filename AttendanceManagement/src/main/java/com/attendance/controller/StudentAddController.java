package com.attendance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Student;
import com.attendance.repository.StudentRepository;

@Controller
public class StudentAddController {
	 @Autowired
     private StudentRepository repository;
	 @RequestMapping(value = "/studentAdd", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
	        Student studnet=new Student();
	        model.addAttribute("title","生徒新規作成画面");
	        model.addAttribute("message","生徒情報の新規作成が可能");
	        model.addAttribute("student",studnet);
	       
	        return "/studentAdd";
	    }

	   @RequestMapping(value = "/studentAdd", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(@Valid @ModelAttribute Student data ,Errors result,Model model) {
	    	if(result.hasErrors()){
	            model.addAttribute("title","エラー画面");
	            model.addAttribute("message","エラーが発生しました");
	            return "/studentAdd";
	    	}else{
	            repository.saveAndFlush(data);
	            model.addAttribute("title","生徒管理画面");
		        model.addAttribute("message","生徒一覧から目的の生徒を検索し、編集・削除等が可能");
		        model.addAttribute("myData",data);
		        List<Student> list = repository.findAll();
		        model.addAttribute("datalist",list);
	            return "/studentList";
	    	}
	    }
}

