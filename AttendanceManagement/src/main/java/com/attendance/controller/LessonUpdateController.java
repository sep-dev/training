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

import com.attendance.dao.PasswordManager;
import com.attendance.dao.TeacherPropertyEditor;
import com.attendance.entity.Lesson;
import com.attendance.entity.Teacher;
import com.attendance.repository.LessonRepository;
import com.attendance.repository.TeacherRepository;

@Controller
public class LessonUpdateController {
	 @Autowired
     private TeacherRepository teacher_repository;
	 @Autowired
     private LessonRepository repository;
     private PasswordManager pm;
	 @RequestMapping(value = "/lessonUpdate", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(HttpServletRequest request,Model model) {
		 System.out.println("ID"+request.getParameter("id"));
		    int id=Integer.parseInt(request.getParameter("id"));

	        model.addAttribute("title","科目編集画面");
	        model.addAttribute("message","科目情報の編集が可能");
	        Lesson lesson = repository.findOne(id);
	        model.addAttribute("lesson",lesson);
	        List<Teacher> teacher_list=teacher_repository.findAll();       
	        model.addAttribute("selectTeacher",teacher_list); 
	        return "/lessonUpdate";
	    }

	   @RequestMapping(value = "/lessonUpdate", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(@Valid @ModelAttribute Lesson data ,Errors result,Model model) {
	    	if(result.hasErrors()){
	            model.addAttribute("title","エラー画面");
	            model.addAttribute("message","エラーが発生しました");
	            return "/lessonUpdate";
	    	}else{
	            repository.saveAndFlush(data);
	            model.addAttribute("title","科目管理画面");
		        model.addAttribute("message","科目一覧から目的の生徒を検索し、編集・削除等が可能");
		        model.addAttribute("myData",data);
		        List<Lesson> list = repository.findAll();
		        model.addAttribute("datalist",list);
	            return "/lessonList";
	    	}
	    }
	   @InitBinder
	   protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder)throws Exception{

		   binder.registerCustomEditor(Teacher.class,new TeacherPropertyEditor(teacher_repository));
	   }
}
