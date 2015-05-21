package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class ListController<T> {
    public String init(Model model,String title,String location,List <T> list) {
        
        return location+"List";
    }
    public String find(HttpServletRequest request,Model model,String title,String location,List <T> list) {
    	String param = request.getParameter("fstr");
        model.addAttribute("title", title+"管理画面");
        model.addAttribute("message", title+"一覧から検索し、編集・削除等が可能");
        model.addAttribute("datalist", list);
        return location+"List";
    }
}