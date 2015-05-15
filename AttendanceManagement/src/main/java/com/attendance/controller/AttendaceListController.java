package com.attendance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.entity.LectureAttendance;
import com.attendance.form.AttendForm;
import com.attendance.repository.AttendanceListRepository;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.StudentRepository;


/**
 * Handles requests for the application home page.
 */
@Controller
public class AttendaceListController {
	@PersistenceContext
	private EntityManager manager;
	 @Autowired
     private LectureRepository lecture_repository;
	 @Autowired
     private StudentRepository student_repository;
	 @Autowired
     private AttendanceListRepository repository;
	 @RequestMapping(value = "/attendList", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
	    public String helo(Model model) {
		    AttendForm af=new AttendForm();
		    List<AttendForm> al=new ArrayList(10);
	        LectureAttendance data=new LectureAttendance();
	        model.addAttribute("title","出席生徒画面");
	        model.addAttribute("message","出席生徒一覧から目的の生徒を検索可能");
	        model.addAttribute("myData",data);
	        List<LectureAttendance> list = repository.findAll();
	        final CriteriaBuilder cb = manager.getCriteriaBuilder();
	        final CriteriaQuery<LectureAttendance> query = cb.createQuery(LectureAttendance.class);
	        final Root<LectureAttendance> root = query.from(LectureAttendance.class);
	        query.select(root).orderBy(cb.asc(root.get(LectureAttendancePK).get(LectureAttendance-.key1)));
	        /*for(int i=0;i<list.size();i++){
	            af.setStudentId(list.get(i).getId().getStudentId());
	            af.setStudentName(student_repository.findByStudentId(list.get(i).getId().getStudentId()).getStudentName());
	            af.setLectureName(lecture_repository.findByLectureId(list.get(i).getId().getLectureId()).getLesson().getLessonName());
	            af.setLectureDate(lecture_repository.findByLectureId(list.get(i).getId().getLectureId()).getLectureDate());
	            af.setLectureHour(lecture_repository.findByLectureId(list.get(i).getId().getLectureId()).getLectureHour());
	            al.add(af);
	        }*/

	        model.addAttribute("datalist",al);

	        return "/attendList";
	    }

	    @RequestMapping(value = "/attendList", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	    public String search(HttpServletRequest request,Model model) {

	        String param=request.getParameter("fstr");
	        System.out.println(param);
	        model.addAttribute("title","検索");
	        model.addAttribute("message","「"+param+"」の"+"検索結果");
	        //名前・住所であいまい検索
	        // List<LectureAttendance> list = repository.findByStudentNameLikeOrStudentAddressLike("%"+param+"%","%"+param+"%");
	        //model.addAttribute("datalist",list);
	        return "/attendList";
	    }
}
