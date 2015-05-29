package com.addressbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.addressbook.entity.UserMst;
import com.addressbook.helper.Helper;
import com.addressbook.service.repositories.UserMstRepository;

@Controller
@RequestMapping(value="/delete")
public class UserDeleteController {
    @Autowired
    private UserMstRepository repository;
    private ModelAndView modelAndView;

    @RequestMapping(value="/kakunin")
    public ModelAndView deleteKakunin(@ModelAttribute UserMst deleteUser){
        modelAndView = Helper.getMmodelAndView("deleteKakunin");
        modelAndView.addObject("deleteUser", deleteUser); //確認画面にUserMstオブジェクトを飛ばす
        return modelAndView;
    }


    /*
     * バインド処理
     *  すべての値のバインドを許可しない
     */
    @InitBinder("deleteUser")
    public void initBuinder(WebDataBinder binder){
        binder.setDisallowedFields("id","name","address","tel");
    }

    //DBから削除対象のUserMstオブジェクト取得
    @ModelAttribute("deleteUser")
    public UserMst requestDeleteUser(@RequestParam(required=false,value="id") Integer id){
        if(id == null) return null;
        return repository.findById(id);
    }

    @RequestMapping(value="/excute")
    public ModelAndView delete(@Valid @ModelAttribute("deleteUser") UserMst deleteUser,BindingResult result){
        modelAndView = Helper.getMmodelAndView("result");
        if(!result.hasErrors()) repository.delete(deleteUser);
        modelAndView.addObject("title", result.hasErrors() ? "削除失敗" : "削除成功");
        modelAndView.addObject("message", result.hasErrors() ? "削除できませんでした。" : "削除しました。");
        return modelAndView;
    }
}
