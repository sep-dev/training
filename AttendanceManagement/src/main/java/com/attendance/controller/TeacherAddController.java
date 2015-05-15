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

import com.attendance.dao.ClassPropertyEditor;
import com.attendance.entity.Clas;
import com.attendance.entity.Teacher;
import com.attendance.repository.ClassRepository;
import com.attendance.repository.TeacherRepository;

@Controller
public class TeacherAddController {
	 @Autowired
     private ClassRepository class_repository;
	 @Autowired
     private TeacherRepository repository;
	 @RequestMapping(value = "/teacherAdd", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
	        Teacher teacher=new Teacher();
	        model.addAttribute("title","講師新規作成画面");
	        model.addAttribute("message","講師情報の新規作成が可能");
	        model.addAttribute("teacher",teacher);
	        List<Clas> class_list=class_repository.findAll();
	        model.addAttribute("selectClass",class_list);
	        return "/teacherAdd";
	    }

	   @RequestMapping(value = "/teacherAdd", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(@Valid @ModelAttribute Teacher data ,Errors result,Model model) {
	    	if(result.hasErrors()){
	            model.addAttribute("title","エラー画面");
	            model.addAttribute("message","エラーが発生しました");
	            return "/teacherAdd";
	    	}else{
	            repository.saveAndFlush(data);
	            model.addAttribute("title","講師管理画面");
		        model.addAttribute("message","講師一覧から目的の講師を検索し、編集・削除等が可能");
		        model.addAttribute("myData",data);
		        List<Teacher> list = repository.findAll();
		        model.addAttribute("datalist",list);
	            return "/teacherList";
	    	}
	    }
	   @InitBinder
	   protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder)throws Exception{
		   binder.registerCustomEditor(Clas.class,new ClassPropertyEditor(class_repository));
	   }
}
