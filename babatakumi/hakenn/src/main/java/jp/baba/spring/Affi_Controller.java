package jp.baba.spring;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Affi_Controller {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	//所属元情報
	@RequestMapping(value = "/menu/syozokumoto_info", method = RequestMethod.GET)
	public String syosokumoto(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation;");

		model.addAttribute("list",list);
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "syozokumoto_info";
	}

	//編集画面へ
	@RequestMapping(value = "/menu/syozokumoto_info", method = RequestMethod.POST, params = "update")
	public String affiEdit(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId = ?;"
																	,new Object[]{pageModel.getUserid()});

		model.addAttribute("list",list);
		model.addAttribute("h2","所属元編集");
		model.addAttribute("button","更新");

		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "syozokumoto_input";
	}

	//新規画面へ
	@RequestMapping(value = "/menu/syozokumoto_info", method = RequestMethod.POST, params = "new")
	public String affiEditnew(@ModelAttribute pageModel pageModel,Model model) {

		String list = null;

		model.addAttribute("list",list);
		model.addAttribute("h2","所属元新規登録");
		model.addAttribute("button","登録");

		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "syozokumoto_input";
	}

	//追加と更新
	@RequestMapping(value = "/menu/syozokumoto_info", method = RequestMethod.POST, params = "change")
	public String affiEditchange(@ModelAttribute SqlModel2 sqlModel2,BindingResult result,Model model) {

		int up = sqlModel2.getUp();

		String name = sqlModel2.getName();
		String post = sqlModel2.getPost();
		String add = sqlModel2.getAdd();
		String tel = sqlModel2.getTel();
		String station = sqlModel2.getStation();

		//空文字判定
		if(name == "" || tel == "" || post == "" || add == "" || station == ""){
			if(up == 1){
				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId = ?;"
						,new Object[]{sqlModel2.getId()});

				model.addAttribute("list",list);
				model.addAttribute("h2", "更新に失敗しました");
				model.addAttribute("button","更新");
			}else{
				model.addAttribute("h2", "登録に失敗しました");
				model.addAttribute("button","登録");
			}
			return "syozokumoto_input";
		}

		SqlModel2 sqlModel = new SqlModel2();
		model.addAttribute("sqlModel",sqlModel);

		String sqlup = "UPDATE tblAffiliation SET affiliationName = ?, affiliationPostCode = ?, affiliationAdd = ?, "
												+ "affiliationTel = ?, affiliationNearestStation = ?, affiliationRemarks = ? "
												+ "WHERE affiliationId = ?;";
		String sqlin = "INSERT INTO tblAffiliation (affiliationName,affiliationPostCode,affiliationAdd,"
												+ "affiliationTel,affiliationNearestStation,affiliationRemarks) "
												+ "VALUES (?,?,?,?,?,?);";

	//更新
	if(up == 1){
		try{
			jdbcTemplate.update(sqlup, new Object[]{name,post,add,tel,station,sqlModel2.getRemarks(),sqlModel2.getId()});

			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId = ?;"
					,new Object[]{sqlModel2.getId()});

			model.addAttribute("list",list);
			model.addAttribute("h2", "更新しました！");
			model.addAttribute("button","更新");
			return "syozokumoto_input";
		}catch(Exception e){
			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId = ?;"
					,new Object[]{sqlModel2.getId()});

			model.addAttribute("list",list);
			model.addAttribute("h2", "更新に失敗しました");
			model.addAttribute("button","更新");
			System.out.println(e);
			return "syozokumoto_input";

		}
		//追加
		}else if(up == 2){
			try{
				jdbcTemplate.update(sqlin, new Object[]{name,post,add,tel,station,sqlModel2.getRemarks()});
				model.addAttribute("h2", "登録しました！");
				model.addAttribute("button","登録");
				return "syozokumoto_input";
			}catch(Exception e){
				model.addAttribute("h2", "登録に失敗しました");
				model.addAttribute("button","登録");
				return "syozokumoto_input";
			}
		}
		return "syozokumoto_input";
	}

	//delete移動
	@RequestMapping(value = "/menu/syozokumoto_info", method = RequestMethod.POST, params = "delete")
	public String affiEditdel(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId= ?;"
																				,new Object[]{pageModel.getUserid()});
		int from = 3;

		model.addAttribute("list",list);
		model.addAttribute("h2","削除しますか?");
		model.addAttribute("from",from);
		model.addAttribute("home", "/spring/menu/syozokumoto_info");
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "delete";
	}

	//delete実行
	@RequestMapping(value = "/menu/syozokumoto_info", method = RequestMethod.POST, params = "delyes")
	public String affiEditdelyes(@ModelAttribute SqlModel2 sqlModel,BindingResult result,Model model) {

		int id = sqlModel.getId();
		String sqldel = "DELETE FROM tblAffiliation where affiliationId = ?";

		try{
			jdbcTemplate.update(sqldel,new Object[]{id});

			model.addAttribute("h2","削除しました");
			model.addAttribute("home", "/spring/menu/syozokumoto_info");
			return "kekka";
		}catch(Exception e){
			model.addAttribute("h2","削除できませんでした");
			model.addAttribute("home", "/spring/menu/syozokumoto_info");
			return "kekka";
		}
	}

	//検索
	@RequestMapping(value = "/menu/syozokumoto_info", method = RequestMethod.POST, params = "kensaku")
	public String affiEditserch(@ModelAttribute SqlModel2 sqlModel,BindingResult result,Model model) {
		String name = sqlModel.getName();

		try{
			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationName like \'%"+name+"%\';");
			model.addAttribute("list",list);
			return "syozokumoto_info";
		}catch(Exception e){
			model.addAttribute("h3","見つかりませんでした");
			System.out.println(e);
			return "syozokumoto_info";
		}

	}
}
