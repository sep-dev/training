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
import com.attendance.entity.Clas;
import com.attendance.repository.ClassRepository;

/**
 * クラス（中学特進クラスなど）削除のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class ClassDeleteController extends AccessController{

    @Autowired
    private ClassRepository repository;
    /*削除確認表示*/
    @RequestMapping(value = "/classDelete", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteConfirm(HttpServletRequest request, Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*対象データの取り出し*/
        int id = Integer.parseInt(request.getParameter("id"));
        Clas clas = repository.findOne(id);
        model.addAttribute("id", id);
        model.addAttribute("class", clas);
        model.addAttribute("message", "本当に削除しますか？");
        return "/classDelete";
    }
    /*削除*/
    @RequestMapping(value = "/classDelete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String delete(Model model, HttpServletRequest request,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*選択情報の削除*/
        Clas clas = repository.findOne(Integer.parseInt(request.getParameter("id")));
        repository.delete(clas);
        /*一覧リストの生成*/
        List<Clas> list = repository.findAll();
        model.addAttribute("datalist", list);
        return "/classList";
    }
}
