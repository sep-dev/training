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

import com.attendance.dao.TeacherPropertyEditor;
import com.attendance.entity.Lecture;
import com.attendance.entity.Teacher;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.TeacherRepository;


@Controller
public class LectureAddController {
	 @Autowired
     private TeacherRepository teacher_repository;
	 @Autowired
     private LectureRepository repository;
	 @RequestMapping(value = "/lectureAdd", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
	        Lecture lecture=new Lecture();
	        model.addAttribute("title","講義新規作成画面");
	        model.addAttribute("message","講義情報の新規作成が可能");
	        model.addAttribute("lecture",lecture);

	        return "/lectureAdd";
	    }

	   @RequestMapping(value = "/lectureAdd", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(@Valid @ModelAttribute Lecture data ,Errors result,Model model) {
	    	if(result.hasErrors()){
	            model.addAttribute("title","エラー画面");
	            model.addAttribute("message","エラーが発生しました");
	            return "/lectureAdd";
	    	}else{
	            repository.saveAndFlush(data);
	            model.addAttribute("title","講義管理画面");
		        model.addAttribute("message","講義一覧から目的の科目を検索し、編集・削除等が可能");
		        model.addAttribute("myData",data);
		        List<Lecture> list = repository.findAll();
		        model.addAttribute("datalist",list);
	            return "/lectureList";
	    	}
	    }
	   @InitBinder
	   protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder)throws Exception{

		   binder.registerCustomEditor(Teacher.class,new TeacherPropertyEditor(teacher_repository));
	   }
}