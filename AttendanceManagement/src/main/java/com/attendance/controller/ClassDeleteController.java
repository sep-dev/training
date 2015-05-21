package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.attendance.domain.AccessUser;
import com.attendance.entity.Clas;
import com.attendance.repository.ClassRepository;

/**
 * クラス（中学特進クラスなど）削除のコントローラ
 */
@Controller
@RequestMapping(value = "/manager")
public class ClassDeleteController extends AccessController{
    @Autowired
    private ClassRepository repository;

    @RequestMapping(value = "/classDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteConfirm(HttpServletRequest request, Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        int id = Integer.parseInt(request.getParameter("id"));
        Clas clas = repository.findOne(id);
        model.addAttribute("message", "本当に削除しますか？");
        model.addAttribute("id", id);
        model.addAttribute("class", clas);
        return "/classDelete";
    }

    @RequestMapping(value = "/classDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String delete(Model model, HttpServletRequest request,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        System.out.println("id=" + request.getParameter("id"));
        Clas clas = repository.findOne(Integer.parseInt(request
                .getParameter("id")));
        repository.delete(clas);
        List<Clas> list = repository.findAll();
        model.addAttribute("datalist", list);
        return "/classList";
    }
}
