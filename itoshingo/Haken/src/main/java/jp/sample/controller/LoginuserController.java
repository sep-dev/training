package jp.sample.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import jp.sample.form.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginuserController {

	@Autowired
	private JdbcTemplate jt;

	@RequestMapping(value="/haken/loginuserInfo",method = RequestMethod.GET)
	public String loginuserInfo(Model model){


		List<Map<String,Object>> list = jt.queryForList("select * from tblLoginUser");

		model.addAttribute("data",list);


		return "loginuserInfo";
	}

	@RequestMapping(value="/haken/loginuserInsert",method = RequestMethod.GET)
	public String loginuserInsertform(Model model){


		model.addAttribute("message","<br>");

		model.addAttribute("form",new LoginForm());


		model.addAttribute("title","管理者登録");
		model.addAttribute("submit","登録");

		model.addAttribute("loginUserName","");
		model.addAttribute("loginUser","");

		return "loginuserInput";
	}

	@RequestMapping(value="/haken/loginuserInsert",method = RequestMethod.POST)
	public String loginuserInsert(@Valid @ModelAttribute LoginForm form,BindingResult result,Model model){

		if(result.hasErrors()){

			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
			model.addAttribute("loginUserName","");
			model.addAttribute("loginUser","");

		}else{

			String username = form.getUsername();
			String user = form.getUser();
			String pass1 = form.getPass1();
			String pass2 = form.getPass2();

			if(pass1.equals(pass2)){

				try{
						String sql2 = "insert into tblLoginUser(loginUserName,loginUser,LoginUserPass) values(?,?,?)";
						jt.update(sql2,new Object[]{username,user,pass1});
						model.addAttribute("message","登録に成功しました。");
						model.addAttribute("loginUserName","");
						model.addAttribute("loginUser","");
				}catch(Exception e){
					model.addAttribute("message","<font color=\"red\">入力されたユーザーIDは既に登録されています。</font>");
					model.addAttribute("loginUserName",username);
					model.addAttribute("loginUser","");
				}
			}else{
				model.addAttribute("message","<font color=\"red\">入力されたパスワードが違います</font>");
				model.addAttribute("loginUserName",username);
				model.addAttribute("loginUser",user);
			}

		}

		model.addAttribute("form",new LoginForm());

		model.addAttribute("title","管理者登録");
		model.addAttribute("submit","登録");

		return "loginuserInput";
	}

	@RequestMapping(value="/haken/loginuserUpdate",method = RequestMethod.GET)
	public String loginuserUpateform(Model model,HttpServletRequest request){

		model.addAttribute("form",new LoginForm());

		model.addAttribute("message","<br>");

		model.addAttribute("title","管理者編集");
		model.addAttribute("submit","確定");

		int id = Integer.parseInt(request.getParameter("id"));

		String sql1 = "select loginusername from tblLoginUser where loginUserId = ? ;";
		String sql2 = "select loginuser from tblLoginUser where loginUserId = ? ;";

		String username = jt.queryForObject(sql1, new Object[]{id},String.class);
		String user = jt.queryForObject(sql2, new Object[]{id},String.class);


		model.addAttribute("loginUserName",username);
		model.addAttribute("loginUser",user);

		return "loginuserInput";
	}

	@RequestMapping(value="/haken/loginuserUpdate",method = RequestMethod.POST)
	public String loginuserUpdate(@Valid @ModelAttribute LoginForm form,BindingResult result,Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		if(result.hasErrors()){

			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
		}
		else{

			String username = form.getUsername();
			String user = form.getUser();
			String pass1 = form.getPass1();
			String pass2 = form.getPass2();

			if(pass1.equals(pass2)){
				try{
					String sql = "update tblLoginUser set loginUserName = ?,loginUser = ? ,LoginUserPass = ? where loginuserid = ? ;";
					jt.update(sql,new Object[]{username,user,pass1,id});
					model.addAttribute("message","更新に成功しました。");
				}catch(Exception e){
					model.addAttribute("message","<font color=\"red\">入力されたユーザーIDは既に登録されています。</font>");
				}


			}else{
				model.addAttribute("message","<font color=\"red\">入力されたパスワードが違います</font>");
			}
		}
		String sql1 = "select loginusername from tblLoginUser where loginUserId = ? ;";
		String sql2 = "select loginuser from tblLoginUser where loginUserId = ? ;";

		String username = jt.queryForObject(sql1, new Object[]{id},String.class);
		String user = jt.queryForObject(sql2, new Object[]{id},String.class);

		model.addAttribute("loginUserName",username);
		model.addAttribute("loginUser",user);


		model.addAttribute("form",new LoginForm());

		model.addAttribute("title","管理者編集");
		model.addAttribute("submit","確定");




		return "loginuserInput";
	}

	@RequestMapping(value="/haken/loginuserDelete",method = RequestMethod.POST)
	public String loginuserDelete(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		String sql = "delete from tblLoginUser where loginUserId = ? ;";

		jt.update(sql, new Object[]{id});

		return "redirect:/haken/loginuserInfo";

	}

	@RequestMapping(value="/haken/loginuserSelect",method = RequestMethod.POST)
	public String loginuserSelect(Model model,HttpServletRequest request){

		String input = request.getParameter("input");

		List<Map<String,Object>> list = jt.queryForList("select * from tblLoginUser where loginUserName like \'%"+ input +"%\';");

		model.addAttribute("data",list);

		return "loginuserInfo";
	}

}
