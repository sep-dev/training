package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.Lecture;
import com.attendance.form.LectureForm;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.LessonRepository;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LectureController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
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

	        String sql="Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id ";
	        List<LectureForm> al=jdbcTemplate.query(sql, new BeanPropertyRowMapper<LectureForm>(LectureForm.class));
	        model.addAttribute("datalist",al);

	        return "/lectureList";
	    }

	    @RequestMapping(value = "/lectureList", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String search(HttpServletRequest request,Model model) {
	    	String param=request.getParameter("date");
	    	if(param==null){
	        	param="1800-1-1 00:00:00";
	        }
	        String param2=request.getParameter("date2");
	        if(param2.length()==0){
	        	param2="2100-1-1 00:00:00";
	        }
			String param3=request.getParameter("lessonName");
			String param4=request.getParameter("teacherName");
			String param5=request.getParameter("lectureHour");
	        System.out.println(param);
	        model.addAttribute("title","検索");
	        model.addAttribute("message","「"+param+"」の"+"検索結果");
	        //あいまい検索
	        List<Lecture> list=repository.findAll();
	        if(param!=null&&param2!=null){
	        	String sql="Select * from lectures as a inner join lessons as b on a.lesson_id=b.lesson_id inner join teachers as c on b.lesson_teacher_id=c.teacher_id "
	        			+ "where  b.lesson_name like ? and c.teacher_name like ? and a.lecture_date between ? and ? and a.lecture_hour like ?";
	 	        List<LectureForm> al=jdbcTemplate.query(sql, new BeanPropertyRowMapper<LectureForm>(LectureForm.class),"%"+param3+"%","%"+param4+"%",param,param2,"%"+param5+"%");
	 	        model.addAttribute("datalist",al);
	        }


	       
	        return "/lectureList";
	    }

}