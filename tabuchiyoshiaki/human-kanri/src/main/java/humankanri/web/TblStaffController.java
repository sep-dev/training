/*package humankanri.web;

import humankanri.domain.TblStaff;
import humankanri.service.TblStaffService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("tblstaff")
public class TblStaffController {
	

	@ModelAttribute
	TblStaffForm setUpForm() {
		return new TblStaffForm();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		List<TblStaff> tblstaff = tblStaffService
				.findAll();
		model.addAttribute("tblstaff", tblstaff);
		return "customers/edit";
	}
}*/
