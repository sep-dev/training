package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;
import com.attendance.entity.Clas;
import com.attendance.repository.ClassRepository;
/**
 * クラス（中学特進クラスなど）更新のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class ClassUpdateController extends AccessController{

    @Autowired
    private ClassRepository repository;
    /*更新画面の表示*/
    @RequestMapping(value = "/classUpdate", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String entry(HttpServletRequest request, Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*対象データの取り出し*/
        int id = Integer.parseInt(request.getParameter("id"));
        Clas clas = repository.findOne(id);
        model.addAttribute("clas", clas);
        return "/classUpdate";
    }
    /*更新処理*/
    @RequestMapping(value = "/classUpdate", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String updateData(@Valid @ModelAttribute Clas data, Errors result,
            Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*文字チェック後問題なければ更新*/
        if(isError(result, data, model)){
        	return "/classUpdate";
        }else{
            repository.saveAndFlush(data);
            List<Clas> list = repository.findAll();
            model.addAttribute("datalist", list);
            return "/classList";
        }
    }

    /*入力文字チェック*/
    private boolean isError(Errors result,Clas data,Model model){
    	 if (result.hasErrors()) {
             model.addAttribute("message", "エラーが発生しました");
             return true;
         } else {
             return false;
         }
    }
}
