package com.sample.book;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import FormModel.UserModel;

//ログインユーザー管理ページのコントローラー
	@Controller
	public class UserController {
		@Autowired
		private JdbcTemplate jdbcTemplate;

			//ユーザー一覧を表示
			@RequestMapping(value="/book/userInfo",  method = RequestMethod.GET)
			public String User(Locale locale, Model model) {
				//2つのテーブル（tblAffiliation,tblStaff）から情報を取得、結合
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser");
				model.addAttribute("data", list);
				return"userInfo";
			}

			//管理者新規登録
			@RequestMapping(value="/book/userInGamen", method=RequestMethod.GET)
			public String UserIn1(Model model){
				UserModel usermodel = new UserModel();
				model.addAttribute("userModel", usermodel);
				model.addAttribute("List", "null");
				return "userUpIn";
			}
			@RequestMapping(value="/book/userInSyori",method=RequestMethod.POST)
			public String UserIn2(@Valid @ModelAttribute UserModel usermodel,  BindingResult result, Model model) {
				String userName = usermodel.getLoginusername();
				String loginUser = usermodel.getLoginuser();
				String loginPass = usermodel.getLoginuserpass1();
				String kakuninPass = usermodel.getLoginuserpass2();
				System.out.println(loginPass);
				System.out.println(kakuninPass);
					if (result.hasErrors()) {
						model.addAttribute("message", "空欄があります！");
					} else {
						if(!(kakuninPass.equals(loginPass))){
							model.addAttribute("message", "パスワードと確認用パスワードが一致しません！");
							model.addAttribute("List", "null");
							return "UserUpIn";
						}else{
							String sql = "insert into tblLoginUser (loginUserName, loginUser, loginUserPass) values (?, ?, ?)";
							jdbcTemplate.update(sql,new Object[]{userName, loginUser, loginPass});
						}
					}
				//ユーザー情報一覧に戻る
				List<Map<String, Object>> list= jdbcTemplate.queryForList("select * from tblLoginUser");
				model.addAttribute("data", list);
				return"userInfo";
			}

			//管理者情報編集
			@RequestMapping(value="/book/userUpGamen", method=RequestMethod.GET)
			public String UserUp(@ModelAttribute UserModel usermodel, Model model){
				String userId = usermodel.getLoginuserid();
				System.out.println(userId);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser where loginUserId = " + userId +";");
				model.addAttribute("loginUserId", list.get(0).get("loginUserId"));
				model.addAttribute("loginUserName", list.get(0).get("loginUserName"));
				model.addAttribute("loginUser", list.get(0).get("loginUser"));
				model.addAttribute("loginUserPass", list.get(0).get("loginUserPass"));
				model.addAttribute("List", "edit");
				return "userUpIn";
			}
			@RequestMapping(value="/book/userUpSyori",method=RequestMethod.POST)
				public String UserUp2(@Valid @ModelAttribute UserModel usermodel,  BindingResult result, Model model){
				String userId = usermodel.getLoginuserid();
				String userName = usermodel.getLoginusername();
				String loginUser = usermodel.getLoginuser();
				String loginPass = usermodel.getLoginuserpass1();
				String kakuninPass = usermodel.getLoginuserpass2();
				System.out.println(userId);
				System.out.println(loginPass);
				System.out.println(kakuninPass);
					if (result.hasErrors()) {
						model.addAttribute("message", "空欄があります！");
					} else {
						if(!(kakuninPass.equals(loginPass))){
							model.addAttribute("message", "パスワードと確認用パスワードが一致しません！");
						}else{
							//DBを更新
							String sql = "update tblLoginUser set loginUserName=?, loginUser=?, loginUserPass=? where loginUserId=? ";
							jdbcTemplate.update(sql,new Object[]{userName, loginUser, loginPass, userId});
						}
					}
				//一覧に戻る
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser");
				model.addAttribute("data", list);
				return"userInfo";
			}

			//ユーザー削除
			@RequestMapping(value="/book/userDelete", method=RequestMethod.POST)
			public String UserDe(@ModelAttribute UserModel usermodel, Model model){
				String userId = usermodel.getLoginuserid();
				jdbcTemplate.update("delete from tblLoginUser where loginUserId= " + userId +";");
				//一覧に戻る
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser");
				model.addAttribute("data", list);
				return"userInfo";
			}

			//ユーザー検索
			@RequestMapping(value="/book/userSearch", method=RequestMethod.POST)
			public String UserSe(@ModelAttribute UserModel usermodel, Model model){
				String userSearch = usermodel.getUsearch();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser where loginUserName like '%"+userSearch+"%';");
				model.addAttribute("data", list);
				return "userInfo";
			}
	}
