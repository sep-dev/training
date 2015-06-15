package com.sample.book;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//ホームからログインページに飛ばすコントローラー
@Controller
public class HomeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("message1", "派遣人員管理システムにようこそ！");
		model.addAttribute("message2", "ログインして下さい！");
		return "home";
	}

		@RequestMapping(value="/login", method = RequestMethod.GET)
		public String Login(Locale locale, Model model) {
			return "login";
		}

		@RequestMapping(value="/book/OK",  method=RequestMethod.GET)
		public String loginok(Locale locale, Model model){
			return "loginOK";
		}

		@RequestMapping(value="/book/menu", method = RequestMethod.GET)
		public String Menu(){
			return "menu";
		}

		@RequestMapping(value="/403", method=RequestMethod.GET)
		public String sessionerror(Model model){
			model.addAttribute("message", "不正なアクセスが発生したか、セッションがタイムアウトしました");
			model.addAttribute("message2", "再度ログインしてください");
			return "403";
		}

		@RequestMapping(value="/loginNG", method=RequestMethod.GET)
		public String loginerror(Model model){
			model.addAttribute("message", "入力した情報が間違っています！");
			model.addAttribute("message2", "内容を確認し再度ログインしてください！");
			return "403";
		}

		@RequestMapping(value="/logoutok", method=RequestMethod.GET)
		public String logout(Locale locale, Model model){
			model.addAttribute("message1", "ログアウトしました！！");
			model.addAttribute("message2", "またね！");
			return "home";

		}
}
