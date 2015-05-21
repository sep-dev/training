package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.domain.AccessUser;
import com.attendance.entity.Lesson;
import com.attendance.repository.LessonRepository;

/**
 * 科目関連の初期画面のコントローラ
 */
@Controller
@RequestMapping(value = "/manager")
public class LessonController extends AccessController{
    @Autowired
    private LessonRepository repository;

    @RequestMapping(value = "/lessonList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String showList(Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        List<Lesson> list = repository.findAll();
        model.addAttribute("datalist", list);
        return "/lessonList";
    }

    @RequestMapping(value = "/lessonList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String searchList(HttpServletRequest request, Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        String param = request.getParameter("fstr");
        model.addAttribute("find1",param);
        // 名前・住所であいまい検索
        List<Lesson> list = repository.findByLessonNameLike("%" + param + "%");
        model.addAttribute("datalist", list);
        return "/lessonList";
    }
}
