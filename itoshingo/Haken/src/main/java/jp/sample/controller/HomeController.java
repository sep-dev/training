package jp.sample.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		model.addAttribute("message","派遣人員管理システム");


		return "login";
	}

	@RequestMapping(value="/haken/top")
	public String login(){
		return "main";

	}

	@RequestMapping(value="/error")
	public String error(Model model){

		model.addAttribute("message","<font color=\"red\">ユーザが存在しません。</font>");

		return "login";

	}

	@RequestMapping(value="/error403")
	public String error403(){
		return "403";
	}

}
