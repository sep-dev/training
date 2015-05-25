package com.attendance.controller;

import java.util.List;

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

@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class ClassAddController extends AccessController{

    @Autowired
    private ClassRepository repository;

    @RequestMapping(value = "/classAdd", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String newEntry(Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*エンティティの生成*/
        Clas clas = new Clas();
        model.addAttribute("clas", clas);
        return "/classAdd";
    }

    @RequestMapping(value = "/classAdd", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String addData(@Valid @ModelAttribute Clas data, Errors result,
            Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*文字チェック後問題なければ登録*/
        if(isError(result, data, model)){
        	return "/classAdd";
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
         } else if (repository.findByClassId(data.getClassId()) != null) {
             model.addAttribute("message", "IDが重複しています");
             return true;
         } else {
             return false;
         }
    }
}
