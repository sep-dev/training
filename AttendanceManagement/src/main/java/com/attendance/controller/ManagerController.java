package com.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;

//管理者ページコントローラ
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class ManagerController extends AccessController{

	@RequestMapping(value = "/top")
	public String managerTop(AccessUser user) {
		if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
		return "managerTop";
	}

	@RequestMapping(value = "/managerMain")
	public String managerMenu(AccessUser user) {
		if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
		return "managerMain";
	}
}
