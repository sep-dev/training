package com.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.attendance.entity.Student;
import com.attendance.repository.StudentRepository;

@Controller
public class LoginController {

    @Autowired
    StudentRepository sr;

    @RequestMapping(value="/")
    public String index(){
        List<Student> sl = sr.findAll();
        if(sl.isEmpty()) System.out.println("接続");
        return "index";
    }
}
