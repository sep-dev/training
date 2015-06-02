package jp.sample.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import jp.sample.form.ShozokumotoForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ShozokumotoController {

	@Autowired
	JdbcTemplate jt;

	@RequestMapping(value="/haken/shozokumotoInfo",method = RequestMethod.GET)
	public String shozokumotoInfo(Model model){

		List<Map<String,Object>> list = jt.queryForList("select * from tblAffiliation");

		model.addAttribute("data",list);

		return "shozokumotoInfo";
	}

	@RequestMapping(value="/haken/shozokumotoInsert",method = RequestMethod.GET)
	public String shozokumotoInsertform(Model model){

		model.addAttribute("message","<br>");

		model.addAttribute("form",new ShozokumotoForm());

		model.addAttribute("title","所属元登録");
		model.addAttribute("submit","登録");

		model.addAttribute("affiliationName","");
		model.addAttribute("affiliationTel","");
		model.addAttribute("affiliationPostcode","");
		model.addAttribute("affiliationAdd","");
		model.addAttribute("affiliationNeareststation","");
		model.addAttribute("affiliationRemarks","");

		return "shozokumotoInput";
	}

	@RequestMapping(value="/haken/shozokumotoInsert",method = RequestMethod.POST)
	public String shozokumotoInsert(@Valid @ModelAttribute ShozokumotoForm form,BindingResult result,Model model){

		if(result.hasErrors()){
			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
		}else{
			String name = form.getName();
			String tel = form.getTel();
			String postcode = form.getPostcode();
			String add = form.getAdd();
			String neareststation = form.getNeareststation();
			String remarks = form.getRemarks();

			String sql = "insert into tblAffiliation(affiliationName,affiliationTel,affiliationPostCode,affiliationAdd,affiliationNearestStation,affiliationRemarks)"
					+ "values(?,?,?,?,?,?)";

			jt.update(sql,new Object[]{name,tel,postcode,add,neareststation,remarks});

			model.addAttribute("message","登録しました。");
		}

		model.addAttribute("form",new ShozokumotoForm());

		model.addAttribute("title","所属元登録");
		model.addAttribute("submit","登録");

		model.addAttribute("affiliationName","");
		model.addAttribute("affiliationTel","");
		model.addAttribute("affiliationPostcode","");
		model.addAttribute("affiliationAdd","");
		model.addAttribute("affiliationNeareststation","");
		model.addAttribute("affiliationRemarks","");

		return "shozokumotoInput";
	}


	@RequestMapping(value="/haken/shozokumotoUpdate",method = RequestMethod.GET)
	public String shozokumotoUpdateform(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		List<Map<String,Object>> list = jt.queryForList("select * from tblAffiliation where affiliationId = "+ id +";");

		model.addAttribute("message","<br>");

		model.addAttribute("form",new ShozokumotoForm());

		model.addAttribute("title","所属元編集");
		model.addAttribute("submit","確定");



		model.addAttribute("affiliationName",list.get(0).get("affiliationName"));
		model.addAttribute("affiliationTel",list.get(0).get("affiliationTel"));
		model.addAttribute("affiliationPostcode",list.get(0).get("affiliationPostcode"));
		model.addAttribute("affiliationAdd",list.get(0).get("affiliationAdd"));
		model.addAttribute("affiliationNeareststation",list.get(0).get("affiliationNeareststation"));
		model.addAttribute("affiliationRemarks",list.get(0).get("affiliationRemarks"));

		return "shozokumotoInput";
	}

	@RequestMapping(value="/haken/shozokumotoUpdate",method = RequestMethod.POST)
	public String shozokumotoUpdate(@Valid @ModelAttribute ShozokumotoForm form,BindingResult result,Model model,HttpServletRequest request){
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
				String sql = "update tblAffiliation set affiliationName = ?,affiliationTel = ?,affiliationPostCode = ?,"
						+ "affiliationAdd = ?,affiliationNearestStation = ?,affiliationRemarks = ?"
						+ " where affiliationId = ? ;";

				jt.update(sql,new Object[]{name,tel,postcode,add,neareststation,remarks,id});

				model.addAttribute("message","更新に成功しました。");
			}catch(Exception e){
				model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
			}
		}
		String sql1 = "select * from tblAffiliation where affiliationId = ";

		List<Map<String,Object>> list = jt.queryForList(sql1 + id + ";");

		model.addAttribute("form",new ShozokumotoForm());


		model.addAttribute("title","所属元編集");
		model.addAttribute("submit","確定");

		model.addAttribute("affiliationName",list.get(0).get("affiliationName"));
		model.addAttribute("affiliationTel",list.get(0).get("affiliationTel"));
		model.addAttribute("affiliationPostcode",list.get(0).get("affiliationPostcode"));
		model.addAttribute("affiliationAdd",list.get(0).get("affiliationAdd"));
		model.addAttribute("affiliationNeareststation",list.get(0).get("affiliationNearestStation"));
		model.addAttribute("affiliationRemarks",list.get(0).get("affiliationRemarks"));

		return "shozokumotoInput";
	}

	@RequestMapping(value="/haken/shozokumotoDelete",method = RequestMethod.POST)
	public String shozokumotoDelete(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		String sql = "delete from tblaffiliation where affiliationId = ? ;";

		jt.update(sql, new Object[]{id});

		return "redirect:/haken/shozokumotoInfo";
	}


	@RequestMapping(value="/haken/shozokumotoSelect",method = RequestMethod.POST)
	public String shozokumotoSelect(Model model,HttpServletRequest request){

		String input = request.getParameter("input");

		List<Map<String,Object>> list = jt.queryForList("select * from tblaffiliation where affiliationName like \'%"+ input +"%\';");

		model.addAttribute("data",list);


		return "shozokumotoInfo";
	}

}
