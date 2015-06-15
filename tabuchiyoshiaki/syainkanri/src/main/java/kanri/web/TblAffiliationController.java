package kanri.web;

import kanri.domain.TblAffiliation;
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
@RequestMapping("tblaffiliation")
public class TblAffiliationController {
	@Autowired
	TblStaffManagementService tblStaffManagementService;

	@Autowired
	TblStaffService tblStaffService;

	@Autowired
	TblClientService tblClientService;

	@Autowired
	TblAffiliationService tblAffiliationService;

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@ModelAttribute
	TblAffiliationForm setUpForm() {
		return new TblAffiliationForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		List<Map<String, Object>> tblaffiliation = JdbcTemplate.queryForList("select * from tblaffiliation");
		model.addAttribute("tblaffiliation", tblaffiliation);

		return "customers/affiliation-infomation";
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(Model model, @RequestParam("str") String str) {

		List<Map<String, Object>> list = JdbcTemplate.queryForList("select * from tblaffiliation where affiliation_name like  ?",('%' + str + '%'));
		model.addAttribute("tblaffiliation", list);

		return "customers/affiliation-infomation";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	String affiliation(Model model, TblAffiliationForm form) {
		List<TblAffiliation> tblaffiliation = tblAffiliationService.findAll();
		model.addAttribute("tblaffiliation", tblaffiliation);
		model.addAttribute("tblAffiliationForm", form);
		return "customers/affiliation-success";

	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated TblAffiliationForm form, BindingResult result,Model model) {

		TblAffiliation tblAffiliation = new TblAffiliation();
		BeanUtils.copyProperties(form, tblAffiliation);
		tblAffiliationService.create(tblAffiliation);
		model.addAttribute("tblAffiliationForm", form);
		return "redirect:/tblaffiliation";
	}

	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
	String editForm(@RequestParam("affiliationId") Integer affiliationId,TblAffiliationForm form, Model model) {
		TblAffiliation tblaffiliation = tblAffiliationService.findOne(affiliationId);
		BeanUtils.copyProperties(tblaffiliation, form);

		return "customers/affiliation-edit";

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	String edit(@RequestParam Integer affiliationId,@Validated TblAffiliationForm form, BindingResult result,Model model) {

		TblAffiliation tblAffiliation = new TblAffiliation();
		BeanUtils.copyProperties(form, tblAffiliation);
		tblAffiliation.setAffiliationId(affiliationId);
		tblAffiliationService.update(tblAffiliation);

		return "redirect:/tblaffiliation";
	}

	@RequestMapping(value = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/tblaffiliation";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete(@RequestParam Integer id) {
		tblAffiliationService.delete(id);
		return "redirect:/tblaffiliation";
	}
}