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
 * 科目削除のコントローラ
 */
@Controller
@RequestMapping(value = "/manager")
public class LessonDeleteController extends AccessController{
    @Autowired
    private LessonRepository repository;

    @RequestMapping(value = "/lessonDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteConfirm(HttpServletRequest request, Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("message", "本当に削除しますか？");
        Lesson lesson = repository.findOne(id);
        model.addAttribute("id", id);
        model.addAttribute("lesson", lesson);
        return "/lessonDelete";
    }

    @RequestMapping(value = "/lessonDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String deleteData(Model model, HttpServletRequest request,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        System.out.println("id=" + request.getParameter("id"));
        Lesson lesson = repository.findOne(Integer.parseInt(request
                .getParameter("id")));
        repository.delete(lesson);
        List<Lesson> list = repository.findAll();
        model.addAttribute("datalist", list);
        return "/lessonList";

    }
}
