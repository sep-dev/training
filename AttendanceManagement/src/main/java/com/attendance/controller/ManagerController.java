package com.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//管理者ページコントローラ
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

	@RequestMapping(value = "/top")
	public String managerTop() {
		return "managerTop";
	}

	@RequestMapping(value = "/managerMainPage")
	public String managerMenu() {
		return "managerMainPage";
	}
}
