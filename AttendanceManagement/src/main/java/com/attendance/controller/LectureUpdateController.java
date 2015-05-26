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
import com.attendance.service.SerchLecture;
/**
 * 講義更新のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class LectureUpdateController extends AccessController{
    @Autowired
    private TeacherRepository teacher_repository;
    @Autowired
    private LessonRepository lesson_repository;
    @Autowired
    private HourMstRepository hour_repository;
    @Autowired
    private LectureRepository repository;
    int id;
    @Autowired
    private SerchLecture search;

    @RequestMapping(value = "/lectureUpdate", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String entry(HttpServletRequest request, Model model,AccessUser user) {
        /*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*対象データの取り出し*/
        id = Integer.parseInt(request.getParameter("id"));
        Lecture lecture = repository.findByLectureId(id);
        model.addAttribute("lecture", lecture);
        createList(model);
        model.addAttribute("id", lecture.getLesson().getLessonId());

        return "/lectureUpdate";
    }

    @RequestMapping(value = "/lectureUpdate", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String updateData(@Valid @ModelAttribute Lecture data, Errors result,Model model,AccessUser user) {
        /*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*エラーチェック後問題なければデータ登録*/
        if (isError(result, data, model)) {
            model.addAttribute("message", "エラーが発生しました");
            Lecture lecture = repository.findByLectureId(id);
            model.addAttribute("lecture", lecture);
            createList(model);
            model.addAttribute("id", lecture.getLesson().getLessonId());
            return "/lectureUpdate";
        } else {
            repository.saveAndFlush(data);
            model.addAttribute("datalist",search.getAll());
            return "/lectureList";
        }
    }

    /*型変換用*/
    @InitBinder
    protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Lesson.class, new LessonPropertyEditor(lesson_repository));
        binder.registerCustomEditor(Teacher.class, new TeacherPropertyEditor(teacher_repository));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    /*入力文字チェック*/
    private boolean isError(Errors result,Lecture data,Model model){
         if (result.hasErrors()) {
             model.addAttribute("message", "エラーが発生しました");
             return true;
         } else {
             return false;
         }
    }

    /*検索用リストの生成*/
    private void createList(Model model){
        List<Lesson> lesson_list = lesson_repository.findAll();
        model.addAttribute("selectLesson", lesson_list);
        List<Teacher> teacher_list = teacher_repository.findAll();
        model.addAttribute("selectTeacher", teacher_list);
        List<HourMst> hour_list = hour_repository.findAll();
        model.addAttribute("selectHour", hour_list);
    }
}