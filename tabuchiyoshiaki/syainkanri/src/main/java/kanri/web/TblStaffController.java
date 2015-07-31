package kanri.web;

import kanri.domain.TblAffiliation;
import kanri.domain.TblStaff;
import kanri.service.TblAffiliationService;
import kanri.service.TblClientService;
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
@RequestMapping("TblStaff")
public class TblStaffController {

	@Autowired
	TblAffiliationService tblAffiliationService;

	@Autowired
	TblStaffService tblStaffService;

	@Autowired
	TblClientService tblClientService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ModelAttribute
	TblStaffForm setUpForm() {
		return new TblStaffForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {

		List<Map<String, Object>> tblstaff = jdbcTemplate.queryForList("select * from tblstaff left join tblaffiliation  using(affiliation_id)");
		model.addAttribute("TblStaff", tblstaff);

		return "customers/member-infomation";
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(Model model, @RequestParam("str") String str) {

		List<Map<String, Object>> tblstaff = jdbcTemplate.queryForList("select * from tblstaff left join tblaffiliation  using(affiliation_id) where staff_name like  ?",('%' + str + '%'));
		model.addAttribute("TblStaff", tblstaff);

		return "customers/member-infomation";
	}

	@RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
	String createForm(TblStaffForm form, Model model) {

		List<TblAffiliation> tblaffiliation = tblAffiliationService.findAll();
		model.addAttribute("TblAffiliation", tblaffiliation);
		model.addAttribute("TblStaffForm", form);

		return "customers/member-success";

	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated TblStaffForm form, BindingResult result,Model model) {

		TblStaff tblStaff = new TblStaff();
		BeanUtils.copyProperties(form, tblStaff);
		tblStaffService.create(tblStaff);
		return "redirect:/TblStaff";
	}

	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
	String editForm(@RequestParam("staffId") Integer staffId,TblStaffForm form, Model model) {
		TblStaff tblStaff = tblStaffService.findOne(staffId);
		BeanUtils.copyProperties(tblStaff, form);

		List<TblAffiliation> tblaffiliation = tblAffiliationService.findAll();
		model.addAttribute("TblAffiliation", tblaffiliation);

		return "customers/member-edit";

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	String edit(@RequestParam Integer staffId, @Validated TblStaffForm form,BindingResult result, Model model) {

		TblStaff tblStaff = new TblStaff();
		BeanUtils.copyProperties(form, tblStaff);
		tblStaff.setStaffId(staffId);
		tblStaffService.update(tblStaff);

		return "redirect:/TblStaff";
	}

	@RequestMapping(value = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/TblStaff";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete(@RequestParam Integer id) {
		tblStaffService.delete(id);
		return "redirect:/TblStaff";
	}

}