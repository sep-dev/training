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
			@RequestMapping(value = "/book/userInfo",  method = RequestMethod.GET)
			public String User(Locale locale, Model model) {
				//2つのテーブル（tblAffiliation,tblStaff）から情報を取得、結合
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser");
				model.addAttribute("data", list);
				return"userInfo";
			}


			//管理者新規登録
			@RequestMapping(value="/book/User", params="userInsert", method=RequestMethod.GET)
			public String UserIn(Model model){
				UserModel um = new UserModel();
				model.addAttribute("userModel", um);
				model.addAttribute("List", "null");
				return "UserUpIn";
			}
			@RequestMapping(value="/book/User", params="userInsert2",method=RequestMethod.POST)
			public String UserIn2(@Valid @ModelAttribute UserModel um,  BindingResult result, Model model) {
				String lun = um.getLoginusername();
				String lu = um.getLoginuser();
				String lup = um.getLoginuserpass1();
				String lup2 = um.getLoginuserpass2();
					if (result.hasErrors()) {
						model.addAttribute("message", "空欄があります！");
					} else {
						if(!(lup2.equals(lup))){
							model.addAttribute("message", "確認用パスワードが間違っています！");
							model.addAttribute("List", "null");
							return "UserUpIn";
						}else{
							String sql = "insert into tblLoginUser (loginUserName, loginUser, loginUserPass) values (?, ?, ?)";
							jdbcTemplate.update(sql,new Object[]{lun, lu, lup});
						}
					}
				//ユーザー情報一覧に戻る
				List<Map<String, Object>> list= jdbcTemplate.queryForList("select * from tblLoginUser");
				model.addAttribute("data", list);
				return"userInfo";
			}


			//管理者情報編集
			@RequestMapping(value="/book/User", params="userUpdate", method=RequestMethod.GET)
			public String UserUp(@ModelAttribute UserModel um, Model model){
				String lui = um.getLoginuserid();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser where loginUserId = " + lui +";");
				model.addAttribute("loginUserName", list.get(0).get("loginUserName"));
				model.addAttribute("loginUser", list.get(0).get("loginUser"));
				model.addAttribute("loginUserPass", list.get(0).get("loginUserPass"));
				model.addAttribute("List", "edit");
				return "UserUpIn";
			}
			@RequestMapping(value="/book/User", params="userUpdate2",method=RequestMethod.POST)
				public String UserUp2(@Valid @ModelAttribute UserModel um,  BindingResult result, Model model){
				String lui = um.getLoginuserid();
				String lun = um.getLoginusername();
				String lu = um.getLoginuser();
				String lup = um.getLoginuserpass1();
				String lup2 = um.getLoginuserpass2();
					if (result.hasErrors()) {
						model.addAttribute("message", "空欄があります！");
					} else {
						if(!(lup2.equals(lup))){
							model.addAttribute("message", "確認用パスワードが間違っています！");
						}else{
							//DBを更新
							String sql = "update tblLoginUser set loginUserName=?, loginUser=?, loginUserPass=? where loginUserId=? ";
							jdbcTemplate.update(sql,new Object[]{lun, lu, lup, lui});
						}
					}
				//一覧に戻る
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser");
				model.addAttribute("data", list);
				return"userInfo";
			}


			//ユーザー削除
			@RequestMapping(value="/book/User", params="userDelete", method=RequestMethod.POST)
			public String UserDe(@ModelAttribute UserModel um, Model model){
				String lui = um.getLoginuserid();
				jdbcTemplate.update("delete from tblLoginUser where loginUserId= " + lui +";");
				//一覧に戻る
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser");
				model.addAttribute("data", list);
				return"userInfo";
			}


			//ユーザー検索
			@RequestMapping(value="/book/User", params="userSearch", method=RequestMethod.POST)
			public String UserSe(@ModelAttribute UserModel um, Model model){
				String us = um.getUsearch();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblLoginUser where loginUserName like '%"+us+"%';");
				model.addAttribute("data", list);
				return "userInfo";
			}
	}
