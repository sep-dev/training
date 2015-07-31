package kanri.web;

import kanri.domain.TblClient;
import kanri.service.TblAffiliationService;
import kanri.service.TblClientService;
import kanri.service.TblStaffManagementService;
import kanri.service.TblStaffService;

import java.util.List;
import java.util.Map;

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
@RequestMapping("TblClient")
public class TblClientController {
	@Autowired
	TblStaffManagementService tblStaffManagementService;

	@Autowired
	TblStaffService tblStaffService;

	@Autowired
	TblClientService tblClientService;

	@Autowired
	TblAffiliationService tblAffiliationService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ModelAttribute
	TblAffiliationForm setUpForm() {
		return new TblAffiliationForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		List<Map<String, Object>> tblclient = jdbcTemplate.queryForList("select * from  tblclient");

		model.addAttribute("TblClient", tblclient);

		return "customers/loan-infomation";
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(Model model, @RequestParam("str") String str) {

		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from  tblclient where client_name like  ?",('%' + str + '%'));
		model.addAttribute("TblClient", list);

		return "customers/loan-infomation";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	String create(Model model, TblClientForm tblClientForm) {
		List<TblClient> tblclient = tblClientService.findAll();
		model.addAttribute("TblClient", tblclient);
		model.addAttribute("TblClientForm", tblClientForm);
		return "customers/loan-success";

	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated TblClientForm form, BindingResult result,
			Model model) {

		TblClient tblClient = new TblClient();
		BeanUtils.copyProperties(form, tblClient);
		tblClientService.create(tblClient);
		model.addAttribute("TblClientForm", form);
		return "redirect:/TblClient";
	}

	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
	String editForm(@RequestParam("clientId") Integer clientId,TblClientForm form, Model model) {
		TblClient tblclient = tblClientService.findOne(clientId);
		BeanUtils.copyProperties(tblclient, form);

		return "customers/loan-edit";

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	String edit(@RequestParam Integer clientId, @Validated TblClientForm form,BindingResult result, Model model) {

		TblClient tblClient = new TblClient();
		BeanUtils.copyProperties(form, tblClient);
		tblClient.setClientId(clientId);
		tblClientService.update(tblClient);

		return "redirect:/TblClient";
	}

	@RequestMapping(value = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/TblClient";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete(@RequestParam Integer id) {
		tblClientService.delete(id);
		return "redirect:/TblClient";
	}
}