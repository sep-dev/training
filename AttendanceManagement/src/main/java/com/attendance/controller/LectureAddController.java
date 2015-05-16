package com.attendance.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
import com.attendance.form.LectureForm;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.LessonRepository;
import com.attendance.repository.TeacherRepository;


@Controller
public class LectureAddController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	 @Autowired
    private LessonRepository lesson_repository;
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

	        List<Lesson> lesson_list=lesson_repository.findAll();
	        model.addAttribute("selectLesson",lesson_list);
	        List<Teacher> teacher_list=teacher_repository.findAll();
	        model.addAttribute("selectTeacher",teacher_list);
	        return "/lectureAdd";
	    }

	   @RequestMapping(value = "/lectureAdd", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String repo(@Valid @ModelAttribute Lecture data ,Errors result,Model model) {
	    	if(result.hasErrors()){
	            model.addAttribute("title","エラー画面");
	            model.addAttribute("message","エラーが発生しました");
	            List<Lesson> lesson_list=lesson_repository.findAll();
		        model.addAttribute("selectLesson",lesson_list);
		        List<Teacher> teacher_list=teacher_repository.findAll();
		        model.addAttribute("selectTeacher",teacher_list);
	            return "/lectureAdd";
	    	}else if(repository.findByLectureId(data.getLectureId())!=null){
	    		model.addAttribute("title","エラー画面");
	            model.addAttribute("message","IDが重複しています");
	            List<Lesson> lesson_list=lesson_repository.findAll();
		        model.addAttribute("selectLesson",lesson_list);
		        List<Teacher> teacher_list=teacher_repository.findAll();
		        model.addAttribute("selectTeacher",teacher_list);
		        return "/lectureAdd";
	    	}else{
	            repository.saveAndFlush(data);
	            model.addAttribute("title","講義管理画面");
		        model.addAttribute("message","講義一覧から目的の科目を検索し、編集・削除等が可能");
		        String sql="Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id ";
		        List<LectureForm> al=jdbcTemplate.query(sql, new BeanPropertyRowMapper<LectureForm>(LectureForm.class));
		        model.addAttribute("datalist",al);
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