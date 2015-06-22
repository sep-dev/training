package kanri.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping("loginForm")
	String loginForm() {
		return "loginForm";
	}

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute TblLoginUserForm tblLoginUserForm,Model model) {

		int check = JdbcTemplate.queryForInt("SELECT Count(login_user_pass) FROM tblLoginUser where login_user =?",tblLoginUserForm.getLoginUser());
		if (check == 0) {
			boolean loginfailureflag = true;
			model.addAttribute("loginfailureflag", loginfailureflag);

			return "loginForm";
		}

		List<Map<String, Object>> loginlist = JdbcTemplate.queryForList("SELECT login_user_pass FROM tblLoginUser where login_user =?",tblLoginUserForm.getLoginUser());

		if ((loginlist.get(0).get("login_user_pass")) != (null)
				&& (loginlist.get(0).get("login_user_pass")
						.equals(tblLoginUserForm.getLoginUserPass()))) {
			return "customers/list";
		} else {
			boolean loginfailureflag = true;
			model.addAttribute("loginfailureflag", loginfailureflag);
			System.out.println(tblLoginUserForm.getLoginUser());
			return "loginForm";
		}

	}
}