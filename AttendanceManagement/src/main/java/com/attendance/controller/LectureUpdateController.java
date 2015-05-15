package com.attendance.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.dao.LessonPropertyEditor;
import com.attendance.dao.TeacherPropertyEditor;
import com.attendance.entity.Lecture;
import com.attendance.entity.Lesson;
import com.attendance.entity.Teacher;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.LessonRepository;
import com.attendance.repository.TeacherRepository;

@Controller
public class LectureUpdateController {
	 @Autowired
     private TeacherRepository teacher_repository;
	 @Autowired
     private LessonRepository lesson_repository;
	 @Autowired
     private LectureRepository repository;
 
	 @RequestMapping(value = "/lectureUpdate", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(HttpServletRequest request,Model model) {
		    int id=Integer.parseInt(request.getParameter("id"));
            
	        model.addAttribute("title","講義編集画面");
	        model.addAttribute("message","講義情報の編集が可能");
	        Lecture lecture = repository.findOne(id);
	        model.addAttribute("lecture",lecture);
	        List<Lesson> lesson_list=lesson_repository.findAll();       
	        model.addAttribute("selectLesson",lesson_list);
	        List<Teacher> teacher_list=teacher_repository.findAll();       
	        model.addAttribute("selectTeacher",teacher_list);    
	        model.addAttribute("id",lecture.getLesson().getLessonId());  
	        return "/lectureUpdate";
	    }

	   @RequestMapping(value = "/lectureUpdate", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(@Valid @ModelAttribute Lecture data ,Errors result,Model model) {
	    	if(result.hasErrors()){
	            model.addAttribute("title","エラー画面");
	            model.addAttribute("message","エラーが発生しました");
	            return "/lectureUpdate";
	    	}else{
	            repository.saveAndFlush(data);
	            model.addAttribute("title","講義管理画面");
		        model.addAttribute("message","講義一覧から目的の講義を検索し、編集・削除等が可能");
		        model.addAttribute("myData",data);
		        List<Lecture> list = repository.findAll();
		        model.addAttribute("datalist",list);
	            return "/lectureList";
	    	}
	    }
	   @InitBinder
	   protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder)throws Exception{
		   binder.registerCustomEditor(Lesson.class,new LessonPropertyEditor(lesson_repository));
		   binder.registerCustomEditor(Teacher.class,new TeacherPropertyEditor(teacher_repository));
		   DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		   CustomDateEditor editor = new CustomDateEditor(df, true);
		   binder.registerCustomEditor(Date.class, editor);
	   }
}