package jp.baba.spring;

import java.util.List;
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

@Controller
public class User_Controller {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	//ユーザー
	@RequestMapping(value = "/menu/User", method = RequestMethod.GET)
	public String User(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblloginUser;");

		model.addAttribute("list",list);
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);
		return "User";
	}

	//編集画面へ
	@RequestMapping(value = "/menu/User", method = RequestMethod.POST, params = "update")
	public String UserEdit(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblloginUser where loginUserID= ?;"
																	,new Object[]{pageModel.getUserid()});

		model.addAttribute("list",list);
		model.addAttribute("h2","管理者更新");
		model.addAttribute("button","更新");
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);
		return "newUserInput";
	}

	//新規画面へ
	@RequestMapping(value = "/menu/User", method = RequestMethod.POST, params = "new")
	public String UserEditnew(@ModelAttribute pageModel pageModel,Model model) {

		model.addAttribute("list",null);
		model.addAttribute("h2","管理者新規登録");
		model.addAttribute("button","登録");
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);
		return "newUserInput";
	}

	//delete確認
	@RequestMapping(value = "/menu/User", method = RequestMethod.POST, params = "delete")
	public String UserEditdel(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblloginUser where loginUserID= ?;"
																				,new Object[]{pageModel.getUserid()});
		int from = 1;

		model.addAttribute("list",list);
		model.addAttribute("h2","削除しますか?");
		model.addAttribute("from",from);
		model.addAttribute("home", "/spring/menu/User");
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "delete";
	}

	//delete実行
		@RequestMapping(value = "/menu/User", method = RequestMethod.POST, params = "delyes")
		public String UserEditdelyes(@ModelAttribute SqlModel2 sqlModel,BindingResult result,Model model) {

			int id = sqlModel.getId();
			String sqldel = "DELETE FROM tblloginUser where loginUserID = ?";

			try{
				jdbcTemplate.update(sqldel,new Object[]{id});

				model.addAttribute("h2","削除しました");
				model.addAttribute("home", "/spring/menu/User");
				return "kekka";
			}catch(Exception e){
				model.addAttribute("h2","削除できませんでした");
				model.addAttribute("home", "/spring/menu/User");
				return "kekka";
			}

		}

	//追加と更新
	@RequestMapping(value = "/menu/User", method = RequestMethod.POST, params = "change")
	public String UserEditchange(@Valid @ModelAttribute SqlModel2 sqlModel,BindingResult result,Model model) {

		int up = sqlModel.getUp();
		String name = sqlModel.getName();
		String user = sqlModel.getLoginUser();
		String kennsa = sqlModel.getLoginUserPass_second();
		String pass = sqlModel.getLoginUserPass();

		//空文字判定
		if(name == "" || user == "" || pass == "" || kennsa == ""){
			if(up ==1){
				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblloginUser where loginUserID= ?;"
						,new Object[]{sqlModel.getId()});
				model.addAttribute("list",list);
				model.addAttribute("h2", "更新に失敗しました");
				model.addAttribute("button","更新");
			}else{
				model.addAttribute("h2", "登録に失敗しました");
				model.addAttribute("button","登録");
			}

			return "newUserInput";
		}

			if(!(kennsa.equals(pass))){
				model.addAttribute("h2", "パスワードが一致しません");
				if(up == 1){
					model.addAttribute("button","更新");
				}else if(up == 2){
					model.addAttribute("button","登録");
				}
				return "newUserInput";
			}

			SqlModel2 sqlModel2 = new SqlModel2();
			model.addAttribute("sqlModel", sqlModel2);

			String sqlup = "UPDATE tblLoginUser SET loginUserName = ?, loginUser = ?, loginUserPass = ? WHERE loginUserID = ?;";
			String sqlin = "INSERT INTO tblLoginUser (loginUserName,loginUser,loginUserPass) VALUES (?,?,?);";

			//更新
			if(up == 1){
				try{
					jdbcTemplate.update(sqlup, new Object[]{name,user,pass,sqlModel.getId()});

					List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblloginUser where loginUserID= ?;"
																	,new Object[]{sqlModel.getId()});

					model.addAttribute("list",list);
					model.addAttribute("h2", "更新しました！");
					model.addAttribute("button","更新");
					return "newUserInput";
				}catch(Exception e){
					List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblloginUser where loginUserID= ?;"
							,new Object[]{sqlModel.getId()});

					model.addAttribute("list",list);
					System.out.println(e);
					model.addAttribute("h2", "更新に失敗しました");
					model.addAttribute("button","更新");
					return "newUserInput";

				}
			//追加
			}else if(up == 2){
				try{
					jdbcTemplate.update(sqlin, new Object[]{name,user,pass});
					model.addAttribute("h2", "登録しました！");
					model.addAttribute("button","登録");
					return "newUserInput";
				}catch(Exception e){
					model.addAttribute("h2", "登録に失敗しました");
					model.addAttribute("button","登録");
					return "newUserInput";
				}
			}
			return "newUserInput";
	}

	//検索
	@RequestMapping(value = "/menu/User", method = RequestMethod.POST, params = "kensaku")
	public String UserEditserch(@ModelAttribute SqlModel2 sqlModel,BindingResult result,Model model) {
		String name = sqlModel.getName();

		try{
			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblloginUser where loginUserName like \'%"+name+"%\';");

			model.addAttribute("list",list);
			return "User";
		}catch(Exception e){
			model.addAttribute("h3","見つかりませんでした");
			System.out.println(e);
			return "User";
		}

	}

}
