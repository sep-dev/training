package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.domain.AccessUser;
import com.attendance.entity.Teacher;
import com.attendance.repository.TeacherRepository;
/**
 * 講師削除のコントローラ
 */
@Controller
@RequestMapping(value = "/manager")
public class TeacherDeleteController extends AccessController{
    @Autowired
    private TeacherRepository repository;

    @RequestMapping(value = "/teacherDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteConfirm(HttpServletRequest request, Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("message", "本当に削除しますか？");
        Teacher teacher = repository.findOne(id);
        model.addAttribute("id", id);
        model.addAttribute("teacher", teacher);
        return "/teacherDelete";
    }

    @RequestMapping(value = "/teacherDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String deleteData(Model model, HttpServletRequest request,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        System.out.println("id=" + request.getParameter("id"));
        Teacher teacher = repository.findOne(Integer.parseInt(request
                .getParameter("id")));
        repository.delete(teacher);
        List<Teacher> list = repository.findAll();
        model.addAttribute("datalist", list);
        return "/teacherList";
    }
}
