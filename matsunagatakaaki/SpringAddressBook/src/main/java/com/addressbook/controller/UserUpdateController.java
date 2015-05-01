package com.addressbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.addressbook.entity.UserMst;
import com.addressbook.helper.Helper;
import com.addressbook.service.repositories.UserMstRepository;

@Controller
@RequestMapping(value="/update")
public class UserUpdateController {
    @Autowired
    private UserMstRepository repository;
    private ModelAndView modelAndView;

    //更新情報入力画面へ
    @RequestMapping(value="/input",method=RequestMethod.POST)
    public ModelAndView update(@RequestParam(required = false,value="id") Integer id){
        modelAndView = Helper.getMmodelAndView("input");
        if(id == null){
            modelAndView.setViewName("redirect:./../addressList");
        }else{
            modelAndView.addObject("updateUser", repository.findById(id));
        }
        return modelAndView;
    }

    // ---------------------------------

    //バインドする値を制限
    @InitBinder("updateUser")
    public void initBinder(WebDataBinder binder){
        binder.setAllowedFields("name","address","tel");
    }

    //DBから該当更新者の情報を取得
    @ModelAttribute("updateUser")
    public UserMst request(@RequestParam(required = false,value="id") Integer id){
        if(id == null) return null;
        return repository.findById(id); //リクエストのIDから、該当するユーザオブジェクトを検索して返す
    }

    //更新処理後に、結果画面へ
    @RequestMapping(value="/result",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public ModelAndView update(@Valid @ModelAttribute("updateUser") UserMst updateUser,BindingResult result){
        modelAndView = Helper.getMmodelAndView("result");
        if(!result.hasErrors()) repository.saveAndFlush(updateUser);
        modelAndView.addObject("title", result.hasErrors() ? "更新失敗" : "更新成功");
        modelAndView.addObject("message", result.hasErrors() ? "更新に失敗しました" : "更新しました。" );
        return modelAndView;
    }
}
