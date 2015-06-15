package kanri.web;

import kanri.domain.TblClient;
import kanri.domain.TblStaff;
import kanri.domain.TblStaffManagement;
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
@RequestMapping("customers")
public class TblStaffManagementController {
	@Autowired
	TblStaffManagementService tblStaffManagementService;

	@Autowired
	TblStaffService tblStaffService;

	@Autowired
	TblClientService tblClientService;

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@ModelAttribute
	TblStaffManagementForm setUpForm() {
		return new TblStaffManagementForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {

		List<Map<String, Object>> list = JdbcTemplate.queryForList("select * from tblstaffmanagement left join tblstaff  using(staff_id) left join tblclient using(client_id)");
		model.addAttribute("List", list);

		return "customers/list";
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(Model model, @RequestParam("year") String year,@RequestParam("month") String month, @RequestParam("str") String str) {

		String s1 = year;
		String s2 = month;
		StringBuffer buf = new StringBuffer();
		buf.append(s1);
		buf.append("-");
		buf.append(s2);
		String kikan = buf.toString();
		List<Map<String, Object>> list = JdbcTemplate.queryForList("select * from tblstaffmanagement left join tblstaff  using(staff_id) left join tblclient using(client_id) where substr(start_date,1,7)<= ? and substr(end_date,1,7)>= ? and staff_name like  ?",
						kikan, kikan, ('%' + str + '%'));
		model.addAttribute("List", list);

		return "customers/list";
	}

	@RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
	String createForm(TblStaffForm form, TblClientForm form2, Model model) {
		List<TblStaff> tblstaff = tblStaffService.findAll();
		model.addAttribute("tblstaff", tblstaff);

		List<TblClient> tblclient = tblClientService.findAll();
		model.addAttribute("tblclient", tblclient);

		return "customers/create";

	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated TblStaffManagementForm form, BindingResult result,
			Model model) {

		TblStaffManagement tblStaffManagement = new TblStaffManagement();
		BeanUtils.copyProperties(form, tblStaffManagement);
		tblStaffManagementService.create(tblStaffManagement);
		return "redirect:/customers";
	}

	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
	String editForm(@RequestParam("staffManId") Integer staffManId,
			TblStaffManagementForm form, Model model) {
		TblStaffManagement tblstaffmanagement = tblStaffManagementService.findOne(staffManId);
		BeanUtils.copyProperties(tblstaffmanagement, form);
		List<TblStaff> tblstaff = tblStaffService.findAll();
		model.addAttribute("tblstaff", tblstaff);

		List<TblClient> tblclient = tblClientService.findAll();
		model.addAttribute("tblclient", tblclient);
		return "customers/edit";

	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	String edit(@RequestParam Integer staffManId,@Validated TblStaffManagementForm form, BindingResult result,Model model) {

		if (result.hasErrors()) {
			return editForm(staffManId, form, model);
		}
		TblStaffManagement tblStaffManagement = new TblStaffManagement();
		BeanUtils.copyProperties(form, tblStaffManagement);
		tblStaffManagement.setStaffManId(staffManId);
		tblStaffManagementService.update(tblStaffManagement);

		return "redirect:/customers";
	}

	@RequestMapping(value = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/customers";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String delete(@RequestParam Integer id) {
		tblStaffManagementService.delete(id);
		return "redirect:/customers";
	}
}