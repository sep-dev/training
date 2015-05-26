package com.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerMainController {

    @RequestMapping(value = "/managerMain", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String fromInit(Model model) {
        model.addAttribute("title", "管理者メイン画面");
        model.addAttribute("message", "管理者のメイン画面です");
        return "/managerMain";
    }
}
