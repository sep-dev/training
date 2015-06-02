package jp.sample.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import jp.sample.form.ShukkosakiForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShukkosakiController {

	@Autowired
	JdbcTemplate jt;

	@RequestMapping(value="/haken/shukkosakiInfo",method = RequestMethod.GET)
	public String shukkosakiInfo(Model model){

		List<Map<String,Object>> list = jt.queryForList("select * from tblClient");

		model.addAttribute("data",list);

		return "shukkosakiInfo";
	}

	@RequestMapping(value="/haken/shukkosakiInsert",method = RequestMethod.GET)
	public String shukkosakiInsertform(Model model){

		model.addAttribute("message","<br>");

		model.addAttribute("form",new ShukkosakiForm());

		model.addAttribute("title","出向先登録");
		model.addAttribute("submit","登録");

		model.addAttribute("clientName","");
		model.addAttribute("clientTel","");
		model.addAttribute("clientPostcode","");
		model.addAttribute("clientAdd","");
		model.addAttribute("clientNeareststation","");
		model.addAttribute("clientRemarks","");


		return "shukkosakiInput";
	}

	@RequestMapping(value="/haken/shukkosakiInsert",method = RequestMethod.POST)
	public String shukkosakiInsert(@Valid @ModelAttribute ShukkosakiForm form,BindingResult result,Model model){

		if(result.hasErrors()){
			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");

		}else{

			String name = form.getName();
			String tel = form.getTel();
			String postcode = form.getPostcode();
			String add = form.getAdd();
			String neareststation = form.getNeareststation();
			String remarks = form.getRemarks();

			String sql = "insert into tblClient(clientName,clientTel,clientPostCode,clientAdd,clientNearestStation,clientRemarks)"
					+ "values(?,?,?,?,?,?)";

			jt.update(sql,new Object[]{name,tel,postcode,add,neareststation,remarks});

			model.addAttribute("message","登録しました。");
		}

		model.addAttribute("form",new ShukkosakiForm());

		model.addAttribute("title","出向先登録");
		model.addAttribute("submit","登録");

		model.addAttribute("clientName","");
		model.addAttribute("clientTel","");
		model.addAttribute("clientPostcode","");
		model.addAttribute("clientAdd","");
		model.addAttribute("clientNeareststation","");
		model.addAttribute("clientRemarks","");


		return "shukkosakiInput";
	}

	@RequestMapping(value="/haken/shukkosakiUpdate",method = RequestMethod.GET)
	public String shukkosakiUpdateform(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		String sql = "select * from tblClient where clientId = ";

		List<Map<String,Object>> list = jt.queryForList(sql + id + ";");

		model.addAttribute("message","<br>");

		model.addAttribute("form",new ShukkosakiForm());


		model.addAttribute("title","出向先編集");
		model.addAttribute("submit","確定");

		model.addAttribute("clientName",list.get(0).get("clientName"));
		model.addAttribute("clientTel",list.get(0).get("clientTel"));
		model.addAttribute("clientPostcode",list.get(0).get("clientPostCode"));
		model.addAttribute("clientAdd",list.get(0).get("clientAdd"));
		model.addAttribute("clientNeareststation",list.get(0).get("clientNearestStation"));
		model.addAttribute("clientRemarks",list.get(0).get("clientRemarks"));


		return "shukkosakiInput";
	}

	@RequestMapping(value="/haken/shukkosakiUpdate",method = RequestMethod.POST)
	public String shukkosakiUpdate(@Valid @ModelAttribute ShukkosakiForm form,BindingResult result,Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));
		if(result.hasErrors()){

			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
		}else{

			String name = form.getName();
			String tel = form.getTel();
			String postcode = form.getPostcode();
			String add = form.getAdd();
			String neareststation = form.getNeareststation();
			String remarks = form.getRemarks();

			try{
				String sql = "update tblClient set clientName = ?,clientTel = ?,clientPostcode = ?,clientAdd = ?,clientNearestStation = ?,"
						+ "clientRemarks = ? where clientId = ? ;";

				jt.update(sql,new Object[]{name,tel,postcode,add,neareststation,remarks,id});

				model.addAttribute("message","更新に成功しました。");
			}catch(Exception e){
				model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
			}
		}

		String sql1 = "select * from tblClient where clientId = ";

		List<Map<String,Object>> list = jt.queryForList(sql1 + id + ";");

		model.addAttribute("form",new ShukkosakiForm());


		model.addAttribute("title","出向先編集");
		model.addAttribute("submit","確定");

		model.addAttribute("clientName",list.get(0).get("clientName"));
		model.addAttribute("clientTel",list.get(0).get("clientTel"));
		model.addAttribute("clientPostcode",list.get(0).get("clientPostCode"));
		model.addAttribute("clientAdd",list.get(0).get("clientAdd"));
		model.addAttribute("clientNeareststation",list.get(0).get("clientNearestStation"));
		model.addAttribute("clientRemarks",list.get(0).get("clientRemarks"));

		return "shukkosakiInput";
	}


	@RequestMapping(value="/haken/shukkosakiDelete",method = RequestMethod.POST)
	public String shukkosakiDelete(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		String sql = "delete from tblClient where clientId = ? ;";

		try{
			jt.update(sql, new Object[]{id});
		}catch(Exception e){
			model.addAttribute("message","<font color=\"red\">派遣先情報に参照されているので、先にそちらを開放してください。</font>");
			model.addAttribute("flag",1);

			return "shukkosakiInput";
		}

		return "redirect:/haken/shukkosakiInfo";
	}

	@RequestMapping(value="/haken/shukkosakiSelect",method = RequestMethod.POST)
	public String shukkosakiSelect(Model model,HttpServletRequest request){

		String input = request.getParameter("input");

		List<Map<String,Object>> list = jt.queryForList("select * from tblClient where clientName like \'%"+ input +"%\';");

		model.addAttribute("data",list);


		return "shukkosakiInfo";
	}
}
