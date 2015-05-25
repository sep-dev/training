package com.attendance.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.repository.AttendanceListRepository;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.StudentRepository;
import com.attendance.search.SerchAttend;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class AttendListController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private LectureRepository lecture_repository;
	@Autowired
	private StudentRepository student_repository;
	@Autowired
	private AttendanceListRepository repository;
	@Autowired
	private SerchAttend search;

	@RequestMapping(value = "/attendList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String helo(Model model) {
		model.addAttribute("datalist", search.getAll());
		model.addAttribute("count", search.getAll().size());
		return "/attendList";
	}

	/*検索要求があったとき*/
	@RequestMapping(value = "/attendList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String search(HttpServletRequest request, Model model) {
		String studentName = request.getParameter("student_name");
		String lectureName = request.getParameter("lecture_name");
		String date1 = request.getParameter("lecture_date");
		String date2 = request.getParameter("lecture_date2");
		String hour = request.getParameter("lecture_hour");
		model.addAttribute("find1", studentName);
        model.addAttribute("find2", lectureName);
        model.addAttribute("find3", date1);
        model.addAttribute("find4", date2);
        model.addAttribute("find5", hour);
		/* 名前・住所であいまい検索*/
		model.addAttribute("datalist", search.getList(studentName, lectureName, date1, date2, hour));
		model.addAttribute("count", search.getList(studentName, lectureName, date1, date2, hour).size());
		return "/attendList";
	}
	
}
