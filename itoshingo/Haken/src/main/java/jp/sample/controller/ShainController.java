package jp.sample.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import jp.sample.form.ShainForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShainController {

	@Autowired
	JdbcTemplate jt;

	@RequestMapping(value="/haken/shainInfo",method = RequestMethod.GET)
	public String shainInfo(Model model){

		List<Map<String,Object>> list = jt.queryForList("select * from tblstaff as t1 "
				+ "left join (select affiliationid,affiliationname from tblaffiliation) as t2 "
				+ "on t1.affiliationid = t2.affiliationid;");

		model.addAttribute("data",list);

		return "shainInfo";
	}

	@RequestMapping(value="/haken/shainInsert",method = RequestMethod.GET)
	public String shainInsertform(Model model){

		model.addAttribute("message","<br>");

		model.addAttribute("title","派遣社員登録");
		model.addAttribute("submit","登録");

		model.addAttribute("form",new ShainForm());

		model.addAttribute("staffName","");
		model.addAttribute("staffEmail","");
		model.addAttribute("staffTel","");
		model.addAttribute("staffMoblietel","");
		model.addAttribute("staffPostcode","");
		model.addAttribute("staffAdd","");
		model.addAttribute("staffNeareststation","");
		model.addAttribute("staffRemarks","");

		List<Map<String,Object>> list = jt.queryForList("select affiliationId,affiliationName from tblAffiliation;");

		model.addAttribute("List",list);

		return "shainInput";

	}

	@RequestMapping(value="/haken/shainInsert",method = RequestMethod.POST)
	public String shainInsert(@Valid @ModelAttribute ShainForm form,BindingResult result,Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("affiliationid"));

		if(result.hasErrors()){
			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
		}else{

			String name = form.getName();
			String email = form.getEmail();
			String tel = form.getTel();
			String mobiletel = form.getMobiletel();
			String postcode = form.getPostcode();
			String add = form.getAdd();
			String neareststation = form.getNeareststation();
			String remarks = form.getRemarks();


			try{
				String sql = "insert into tblStaff(staffName,staffEmail,stafftel,staffMobiletel,staffPostcode,staffAdd,"
						+ "staffNeareststation,affiliationId,staffRemarks)"
						+ " values(?,?,?,?,?,?,?,?,?);";

				jt.update(sql,new Object[]{name,email,tel,mobiletel,postcode,add,neareststation,id,remarks});

				model.addAttribute("message","登録に成功しました。");
			}catch(Exception e){
				model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
			}
		}

		model.addAttribute("title","派遣社員登録");
		model.addAttribute("submit","登録");

		model.addAttribute("form",new ShainForm());

		model.addAttribute("staffName","");
		model.addAttribute("staffEmail","");
		model.addAttribute("staffTel","");
		model.addAttribute("staffMoblietel","");
		model.addAttribute("staffPostcode","");
		model.addAttribute("staffAdd","");
		model.addAttribute("staffNeareststation","");
		model.addAttribute("staffRemarks","");

		List<Map<String,Object>> list = jt.queryForList("select affiliationId,affiliationName from tblAffiliation;");

		model.addAttribute("List",list);

		return "shainInput";

	}


	@RequestMapping(value="/haken/shainUpdate",method = RequestMethod.GET)
	public String shainUpdateform(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));


		List<Map<String,Object>> data = jt.queryForList("select * from tblStaff where staffId = "+ id +";");

		model.addAttribute("message","<br>");

		model.addAttribute("title","派遣社員編集");
		model.addAttribute("submit","確定");

		model.addAttribute("form",new ShainForm());

		model.addAttribute("staffName",data.get(0).get("staffName"));
		model.addAttribute("staffEmail",data.get(0).get("staffEmail"));
		model.addAttribute("staffTel",data.get(0).get("staffTel"));
		model.addAttribute("staffMobiletel",data.get(0).get("staffMobiletel"));
		model.addAttribute("staffPostcode",data.get(0).get("staffPostcode"));
		model.addAttribute("staffAdd",data.get(0).get("staffAdd"));
		model.addAttribute("staffNeareststation",data.get(0).get("staffNeareststation"));
		model.addAttribute("staffRemarks",data.get(0).get("staffRemarks"));

		List<Map<String,Object>> list = jt.queryForList("select affiliationId,affiliationName from tblAffiliation;");
		model.addAttribute("List",list);
		model.addAttribute("affid",data.get(0).get("affiliationId"));

		return "shainInput";
	}

	@RequestMapping(value="/haken/shainUpdate",method = RequestMethod.POST)
	public String shainUpdate(@Valid @ModelAttribute ShainForm form,BindingResult result,Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));
		int affiliationid = Integer.parseInt(request.getParameter("affiliationid"));

		if(result.hasErrors()){
			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");

		}else{

			String name = form.getName();
			String email = form.getEmail();
			String tel = form.getTel();
			String mobiletel = form.getMobiletel();
			String postcode = form.getPostcode();
			String add = form.getAdd();
			String neareststation = form.getNeareststation();
			String remarks = form.getRemarks();



			try{
				String sql = "update tblStaff set staffName = ?,staffEmail = ?,staffTel = ?,staffMobiletel = ?,staffPostcode = ?,staffAdd = ?,"
						+ "staffNeareststation = ?,affiliationId = ?,staffRemarks = ? where staffId = ?";

				jt.update(sql,new Object[]{name,email,tel,mobiletel,postcode,add,neareststation,affiliationid,remarks,id});

				model.addAttribute("message","編集に成功しました。");
			}catch(Exception e){
				model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
			}
		}
		List<Map<String,Object>> data = jt.queryForList("select * from tblStaff where staffId = "+ id +";");

		model.addAttribute("title","派遣社員編集");
		model.addAttribute("submit","確定");

		model.addAttribute("form",new ShainForm());

		model.addAttribute("staffName",data.get(0).get("staffName"));
		model.addAttribute("staffEmail",data.get(0).get("staffEmail"));
		model.addAttribute("staffTel",data.get(0).get("staffTel"));
		model.addAttribute("staffMobiletel",data.get(0).get("staffMobiletel"));
		model.addAttribute("staffPostcode",data.get(0).get("staffPostcode"));
		model.addAttribute("staffAdd",data.get(0).get("staffAdd"));
		model.addAttribute("staffNeareststation",data.get(0).get("staffNeareststation"));
		model.addAttribute("staffRemarks",data.get(0).get("staffRemarks"));

		List<Map<String,Object>> list = jt.queryForList("select affiliationId,affiliationName from tblAffiliation;");
		model.addAttribute("List",list);
		model.addAttribute("affid",data.get(0).get("affiliationId"));

		return "shainInput";
	}

	@RequestMapping(value="/haken/shainDelete",method = RequestMethod.POST)
	public String shainDelete(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		String sql = "delete from tblStaff where staffId = ?";

		jt.update(sql,new Object[]{id});

		return "redirect:/haken/shainInfo";
	}


	@RequestMapping(value="/haken/shainSelect",method = RequestMethod.POST)
	public String shainSelect(Model model,HttpServletRequest request){

		String input = request.getParameter("input");

		List<Map<String,Object>> list = jt.queryForList("select * from (select * from tblstaff where staffname like \'%"
						+ input +"%\') as t1 left join (select affiliationid,affiliationName from tblaffiliation) as t2 "
								+ "on t1.affiliationid = t2.affiliationid;");

		model.addAttribute("data",list);

		return "shainInfo";
	}

}
