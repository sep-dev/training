package kanri.web;

import java.util.List;
import java.util.Map;

import kanri.domain.TblLoginUser;
import kanri.service.TblLoginUserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("TblLoginUser")
public class TblLoginUserController {
	@Autowired
	TblLoginUserService tblLoginUserService;

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@ModelAttribute
	TblLoginUserForm setUpForm() {
		return new TblLoginUserForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		List<Map<String, Object>> tblloginuser = JdbcTemplate.queryForList("select * from tblloginuser");
		model.addAttribute("tblloginuser", tblloginuser);
		return "customers/administration-infomation";
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(Model model, @RequestParam("str") String str) {

		List<Map<String, Object>> list = JdbcTemplate.queryForList("select * from tblloginuser where login_user_name like  ?",('%' + str + '%'));
		model.addAttribute("tblloginuser", list);

		return "customers/administration-infomation";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	String loginuser(Model model, TblLoginUserForm tblLoginUserForm) {
		List<TblLoginUser> tblloginuser = tblLoginUserService.findAll();
		model.addAttribute("tblloginuser", tblloginuser);
		model.addAttribute("tblLoginUserForm", tblLoginUserForm);
		return "customers/administration-success";

	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated TblLoginUserForm form, BindingResult result,@RequestParam("loginUserPass") String loginUserPass,@RequestParam("loginUserPass1") String loginUserPass1, Model model) {

		if (loginUserPass.equals(loginUserPass1)) {
			TblLoginUser tblLoginUser = new TblLoginUser();
			BeanUtils.copyProperties(form, tblLoginUser);
			tblLoginUserService.create(tblLoginUser);
			model.addAttribute("tblLoginUserForm", form);
			return "redirect:/TblLoginUser";
		}
		return "redirect:/TblLoginUser";
	}

	@RequestMapping(value = "create", params = "goToTop")
	String goTotop() {
		return "redirect:/TblLoginUser";
	}

	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
	String editForm(@RequestParam("loginUserId") Integer loginUserId,TblLoginUserForm form, Model model) {
		TblLoginUser tblLoginUser = tblLoginUserService.findOne(loginUserId);
		BeanUtils.copyProperties(tblLoginUser, form);

		return "customers/administration-edit";

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	String edit(@RequestParam Integer loginUserId,@RequestParam("loginUserPass") String loginUserPass,@RequestParam("loginUserPass1") String loginUserPass1,@Validated TblLoginUserForm form, BindingResult result, Model model) {
		if (loginUserPass.equals(loginUserPass1)) {
			TblLoginUser tblLoginUser = new TblLoginUser();
			BeanUtils.copyProperties(form, tblLoginUser);
			tblLoginUser.setLoginUserId(loginUserId);
			tblLoginUserService.update(tblLoginUser);

			return "redirect:/TblLoginUser";

		}
		return "redirect:/TblLoginUser";
	}

	@RequestMapping(value = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/TblLoginUser";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete(@RequestParam Integer id) {
		tblLoginUserService.delete(id);
		return "redirect:/TblLoginUser";
	}
}
