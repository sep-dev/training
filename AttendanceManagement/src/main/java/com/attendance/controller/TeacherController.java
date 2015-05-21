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
 * 講師関連の初期画面のコントローラ
 */
@Controller
@RequestMapping(value = "/manager")
public class TeacherController extends AccessController{
    @Autowired
    private TeacherRepository repository;

    @RequestMapping(value = "/teacherList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String showList(Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        List<Teacher> list = repository.findAll();
        model.addAttribute("datalist", list);
        return "/teacherList";
    }

    @RequestMapping(value = "/teacherList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String searchList(HttpServletRequest request, Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        String param = request.getParameter("fstr");
        model.addAttribute("find1", param);
        // 名前・住所であいまい検索
        List<Teacher> list = repository
                .findByTeacherNameLikeOrTeacherAddressLike("%" + param + "%",
                        "%" + param + "%");
        model.addAttribute("datalist", list);
        return "/teacherList";
    }
}
