package com.addressbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.addressbook.entity.UserMst;
import com.addressbook.form.ListForm;
import com.addressbook.service.repositories.UserMstRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @Autowired
    private UserMstRepository repository;

    private ModelAndView modelAndView;

    //登録情報入力画面へ
    @RequestMapping(value = "/")
    public String index(Model model){
        model.addAttribute("user",new UserMst());
        return "home";
    }

    //登録住所一覧表示
    @RequestMapping(value="/addressList",method=RequestMethod.GET)
    public ModelAndView dispAddressList(){
        modelAndView = new ModelAndView("addressList");
        modelAndView.addObject("listForm", new ListForm());
        modelAndView.addObject("userList",repository.findAll());
        return modelAndView;
    }
}
