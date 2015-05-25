package com.attendance.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;
import com.attendance.editor.LessonPropertyEditor;
import com.attendance.editor.TeacherPropertyEditor;
import com.attendance.entity.HourMst;
import com.attendance.entity.Lecture;
import com.attendance.entity.Lesson;
import com.attendance.entity.Teacher;
import com.attendance.repository.HourMstRepository;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.LessonRepository;
import com.attendance.repository.TeacherRepository;
import com.attendance.search.SerchLecture;
/**
 * 講義新規登録のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class LectureAddController extends AccessController{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LessonRepository lesson_repository;
    @Autowired
    private TeacherRepository teacher_repository;
    @Autowired
    private LectureRepository repository;
    @Autowired
    private HourMstRepository hour_repository;
    @Autowired
    private SerchLecture search;

    @RequestMapping(value = "/lectureAdd", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String newEntry(Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        Lecture lecture = new Lecture();
        model.addAttribute("lecture", lecture);
        List<Lesson> lesson_list = lesson_repository.findAll();
        model.addAttribute("selectLesson", lesson_list);
        List<Teacher> teacher_list = teacher_repository.findAll();
        model.addAttribute("selectTeacher", teacher_list);
        List<HourMst> hour_list = hour_repository.findAll();
        model.addAttribute("selectHour", hour_list);
        return "/lectureAdd";
    }

    @RequestMapping(value = "/lectureAdd", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String addData(@Valid @ModelAttribute Lecture data, Errors result,
            Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        if (result.hasErrors()) {
            model.addAttribute("message", "エラーが発生しました");
            List<Lesson> lesson_list = lesson_repository.findAll();
            model.addAttribute("selectLesson", lesson_list);
            List<Teacher> teacher_list = teacher_repository.findAll();
            model.addAttribute("selectTeacher", teacher_list);
            List<HourMst> hour_list = hour_repository.findAll();
            model.addAttribute("selectHour", hour_list);
            return "/lectureAdd";
        } else if (repository.findByLectureId(data.getLectureId()) != null) {
            model.addAttribute("message", "IDが重複しています");
            List<Lesson> lesson_list = lesson_repository.findAll();
            model.addAttribute("selectLesson", lesson_list);
            List<Teacher> teacher_list = teacher_repository.findAll();
            model.addAttribute("selectTeacher", teacher_list);
            List<HourMst> hour_list = hour_repository.findAll();
            model.addAttribute("selectHour", hour_list);
            return "/lectureAdd";
        } else {
            repository.saveAndFlush(data);
            model.addAttribute("datalist",search.getAll());
            return "/lectureList";
        }

    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Lesson.class, new LessonPropertyEditor(
                lesson_repository));
        binder.registerCustomEditor(Teacher.class, new TeacherPropertyEditor(
                teacher_repository));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
