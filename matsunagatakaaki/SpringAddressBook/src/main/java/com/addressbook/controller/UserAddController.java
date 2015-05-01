package com.addressbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.addressbook.entity.UserMst;
import com.addressbook.helper.Helper;
import com.addressbook.service.repositories.UserMstRepository;

@Controller
@RequestMapping(value="/insert")
public class UserAddController {
    @Autowired
    private UserMstRepository repository;

    private ModelAndView modelAndView;

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView insert(@Valid @ModelAttribute UserMst addUser,BindingResult result){
        modelAndView = Helper.getMmodelAndView("result");
        if(!result.hasErrors()) repository.saveAndFlush(addUser); //登録処理
        modelAndView.addObject("title", result.hasErrors() ? "登録失敗" : "登録成功");
        modelAndView.addObject("message", result.hasErrors() ? "登録に失敗しました" : "登録しました。" );
        return modelAndView;
    }
}
