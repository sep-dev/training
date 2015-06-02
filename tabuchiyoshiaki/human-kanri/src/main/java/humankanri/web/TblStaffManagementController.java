package humankanri.web;

import humankanri.domain.TblClient;
import humankanri.domain.TblStaff;
import humankanri.domain.TblStaffManagement;
import humankanri.service.TblClientService;
import humankanri.service.TblStaffManagementService;
import humankanri.service.TblStaffService;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

	@ModelAttribute
	TblStaffManagementForm setUpForm() {
		return new TblStaffManagementForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		List<TblStaffManagement> customers = tblStaffManagementService
				.findAll();
		model.addAttribute("tblstaffmanagement", customers);
		return "customers/list";
	}
	
	
	
	@RequestMapping(value="/find", method=RequestMethod.POST) 
	public String find(Model model,@RequestParam("str") String str) {
		model.addAttribute("tblstaffmanagement");
		  return "customers/list";
		}
	

	@RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
	String createForm(
			TblStaffForm form,TblClientForm form2, Model model) {
		List<TblStaff> tblstaff = tblStaffService
				.findAll();
		model.addAttribute("tblstaff", tblstaff);

		List<TblClient> tblclient = tblClientService
				.findAll();
		model.addAttribute("tblclient", tblclient);
		
		return "customers/create";

	}

	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated TblStaffManagementForm form,BindingResult result, Model model) {
		if(result.hasErrors()) {
			return list(model);
		}
		TblStaffManagement tblstaffmanagement = new TblStaffManagement();
		BeanUtils.copyProperties(form, tblstaffmanagement);
		tblStaffManagementService.create(tblstaffmanagement);
		return "redirect:/customers";
	}
	

	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
	String editForm(@RequestParam("tblstaffmanagement") Integer staffManId,
			TblStaffManagementForm form, Model model) {
		TblStaffManagement tblstaffmanagement = tblStaffManagementService
				.findOne(staffManId);
		BeanUtils.copyProperties(tblstaffmanagement, form);
		
		List<TblStaff> tblstaff = tblStaffService
				.findAll();
		model.addAttribute("tblstaff", tblstaff);
		
		List<TblClient> tblclient = tblClientService
				.findAll();
		model.addAttribute("tblclient", tblclient);
		return "customers/edit";
	
	}
	
	/*@RequestMapping(value = "edit", params = "form",method = RequestMethod.GET)
		String editForm(@RequestParam Integer staffManId,TblStaffManagementForm form) {
		TblStaffManagement tblstaffmanagement = tblStaffManagementService.findOne(staffManId);
		BeanUtils.copyProperties(tblstaffmanagement, form);
		return "customers/edit";
	}
*/
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
		String edit(@RequestParam Integer staffManId, @Validated TblStaffManagementForm form, BindingResult result,Model model) {
		if (result.hasErrors()) {
			return editForm(staffManId, form,model);
		}
		TblStaffManagement tblStaffManagement = new TblStaffManagement();
		BeanUtils.copyProperties(form, tblStaffManagement);
		/*tblStaffManagement.setStaffManId(staffManId);*/
		tblStaffManagementService.update(staffManId);
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