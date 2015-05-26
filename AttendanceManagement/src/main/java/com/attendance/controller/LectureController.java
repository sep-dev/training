package com.attendance.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;
import com.attendance.repository.LectureRepository;
import com.attendance.repository.LessonRepository;
import com.attendance.service.SerchLecture;

/**
 * 講義関連初期画面のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class LectureController extends AccessController{
    @Autowired
    private LessonRepository lesson_repository;
    @Autowired
    private LectureRepository repository;
    @Autowired
    private SerchLecture serch;

    @RequestMapping(value = "/lectureList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String showList(Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*一覧リストの生成*/
        model.addAttribute("datalist", serch.getAll());
        return "/lectureList";
    }

    @RequestMapping(value = "/lectureList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String searchList(HttpServletRequest request, Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*あいまい検索リスト生成*/
        String lessonName = request.getParameter("lessonName");
        String teacherName = request.getParameter("teacherName");
        String date1=request.getParameter("date");
        String date2=request.getParameter("date2");
        String hour = request.getParameter("lectureHour");
        model.addAttribute("find1", lessonName);
        model.addAttribute("find2", teacherName);
        model.addAttribute("find3", date1);
        model.addAttribute("find4", date2);
        model.addAttribute("find5", hour);
        // あいまい検索
        model.addAttribute("datalist", serch.getList(lessonName, teacherName, date1, date2, hour));
        return "/lectureList";
    }

    /*型変換用*/
    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }


}