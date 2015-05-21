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
 * クラス（中学特進クラスなど）関連の初期画面のコントローラ
 */
@Controller
@RequestMapping(value = "/manager")
public class ClassController extends AccessController{
    @Autowired
    private ClassRepository repository;

    @RequestMapping(value = "/classList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String showList(Model model ,AccessUser user) {
        //管理者かどうかの判定
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        List<Clas> list = repository.findAll();
        model.addAttribute("datalist", list);
        return "/classList";
    }

    @RequestMapping(value = "/classList", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String searchList(HttpServletRequest request, Model model,AccessUser user) {
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        String param = request.getParameter("fstr");
        System.out.println(param);
        model.addAttribute("find1", param);
        // 名前・住所であいまい検索
        List<Clas> list = repository.findByClassNameLike("%" + param + "%");
        model.addAttribute("datalist", list);
        return "/classList";
    }
}
