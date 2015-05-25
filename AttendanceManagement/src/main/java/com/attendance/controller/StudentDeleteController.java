package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;
import com.attendance.entity.Student;
import com.attendance.repository.StudentRepository;
/**
 * 生徒削除のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class StudentDeleteController extends AccessController{
    @Autowired
    private StudentRepository repository;

    @RequestMapping(value = "/studentDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteConfirm(HttpServletRequest request, Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("message", "本当に削除しますか？");
        Student student = repository.findOne(id);
        model.addAttribute("id", id);
        model.addAttribute("student", student);
        return "/studentDelete";
    }

    @RequestMapping(value = "/studentDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String deleteData(Model model, HttpServletRequest request,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        System.out.println("id=" + request.getParameter("id"));
        Student student = repository.findOne(Integer.parseInt(request
                .getParameter("id")));
        repository.delete(student);
        List<Student> list = repository.findAll();
        model.addAttribute("datalist", list);
        return "/studentList";

    }
}
