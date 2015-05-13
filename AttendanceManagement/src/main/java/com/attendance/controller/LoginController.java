package com.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login")
public class LoginController {

    @RequestMapping(value="/")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/manager")
    public String loginManager(){
        return "";
    }

    @RequestMapping(value="student")
    public String loginStudent(){
        return "";
    }
}
