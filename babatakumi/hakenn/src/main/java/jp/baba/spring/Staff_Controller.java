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
public class Staff_Controller {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	//派遣社員情報
	@RequestMapping(value = "/menu/hakennsyain_info", method = RequestMethod.GET)
	public String hakennsyain(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaff left join tblaffiliation "
																		+ "on tblStaff.affiliationId = tblAffiliation.affiliationId;");

		model.addAttribute("list",list);
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "hakennsyain_info";
	}

	//編集画面へ
		@RequestMapping(value = "/menu/hakennsyain_info", method = RequestMethod.POST, params = "update")
		public String staffEdit(@ModelAttribute pageModel pageModel,Model model) {

			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaff "
																		+ "left join tblaffiliation on tblStaff.affiliationId = tblAffiliation.affiliationId where staffId = ?;"
																		,new Object[]{pageModel.getUserid()});

			List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");

			model.addAttribute("list",list);
			model.addAttribute("list2",list2);
			model.addAttribute("h2","派遣社員編集");
			model.addAttribute("button","更新");

			pageModel pgModel = new pageModel();
			model.addAttribute("pgModel", pgModel);

			return "hakennsyain_input";
		}

		//新規画面へ
		@RequestMapping(value = "/menu/hakennsyain_info", method = RequestMethod.POST, params = "new")
		public String staffEditnew(@ModelAttribute pageModel pageModel,Model model) {

			String list = null;
			List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");


			model.addAttribute("list",list);
			model.addAttribute("list2",list2);
			model.addAttribute("h2","派遣社員新規登録");
			model.addAttribute("button","登録");

			pageModel pgModel = new pageModel();
			model.addAttribute("pgModel", pgModel);

			return "hakennsyain_input";
		}

		//追加と更新
		@RequestMapping(value = "/menu/hakennsyain_info", method = RequestMethod.POST, params = "change")
		public String staffEditchange(@ModelAttribute SqlModel2 sqlModel2,BindingResult result,Model model) {

			int up = sqlModel2.getUp();

			String name = sqlModel2.getName();
			String email = sqlModel2.getEmail();
			String tel = sqlModel2.getTel();
			String mob = sqlModel2.getMobtel();
			String post = sqlModel2.getPost();
			String add = sqlModel2.getAdd();
			String station = sqlModel2.getStation();

			//空文字判定
			if(name == "" || email == "" || tel == "" || mob == "" || post == "" || add == "" || station == ""){

				if(up == 1){
					List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");

					List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaff "
							+ "left join tblaffiliation on tblStaff.affiliationId = tblAffiliation.affiliationId where staffId = ?;"
							,new Object[]{sqlModel2.getId()});

					model.addAttribute("list",list);
					model.addAttribute("list2",list2);
					model.addAttribute("h2", "更新に失敗しました");
					model.addAttribute("button","更新");
				}else{
					List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");
					model.addAttribute("list2",list2);
					model.addAttribute("h2", "登録に失敗しました");
					model.addAttribute("button","登録");
				}

				return "hakennsyain_input";
			}

			SqlModel2 sqlModel = new SqlModel2();
			model.addAttribute("sqlModel",sqlModel);

			String sqlup = "UPDATE tblStaff "
							+ "SET staffName = ?, staffEMail = ?,staffTel = ?, staffMobileTel = ?, "
							+ "staffPostCode = ?, staffAdd = ?, staffNearestStation = ?, affiliationId = ?, staffRemarks = ? "
							+ "WHERE staffId = ?;";

			String sqlin = "INSERT INTO tblStaff (staffName,staffEMail,staffTel,staffMobileTel,staffPostCode,"
														+ "staffAdd,staffNearestStation,affiliationId,staffRemarks) "
													+ "VALUES (?,?,?,?,?,?,?,?,?);";

		//更新
		if(up == 1){
			try{
				jdbcTemplate.update(sqlup, new Object[]{name,email,tel,mob,post,add,station
														,sqlModel2.getSub(),sqlModel2.getRemarks(),sqlModel2.getId()});

				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaff "
						+ "left join tblaffiliation on tblStaff.affiliationId = tblAffiliation.affiliationId "
						+ "where staffId = ?;"
						,new Object[]{sqlModel2.getId()});

				List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");

				model.addAttribute("list",list);
				model.addAttribute("list2",list2);
				model.addAttribute("h2", "更新しました！");
				model.addAttribute("button","更新");
				return "hakennsyain_input";
			}catch(Exception e){

				List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");

				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaff "
						+ "left join tblaffiliation on tblStaff.affiliationId = tblAffiliation.affiliationId where staffId = ?;"
						,new Object[]{sqlModel2.getId()});

				model.addAttribute("list",list);
				model.addAttribute("h2", "更新に失敗しました");
				model.addAttribute("button","更新");
				model.addAttribute("list2",list2);
				System.out.println(e);
				return "hakennsyain_input";

			}
			//追加
			}else if(up == 2){
				try{
					jdbcTemplate.update(sqlin, new Object[]{name,email,tel,mob,post,add,station
															,sqlModel2.getSub(),sqlModel2.getRemarks()});

					List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");

					model.addAttribute("h2", "登録しました！");
					model.addAttribute("button","登録");
					model.addAttribute("list2",list2);

					return "hakennsyain_input";
				}catch(Exception e){
					List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");

					model.addAttribute("h2", "登録に失敗しました");
					model.addAttribute("h3", "未入力の値があります");
					model.addAttribute("button","登録");
					model.addAttribute("list2",list2);

					System.out.println(e);
					return "hakennsyain_input";
				}
			}
			return "hakennsyain_input";
		}

		//delete移動
		@RequestMapping(value = "/menu/hakennsyain_info", method = RequestMethod.POST, params = "delete")
		public String staffEditdel(@ModelAttribute pageModel pageModel,BindingResult result,Model model) {

			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaff "
					+ "left join tblaffiliation on tblStaff.affiliationId = tblAffiliation.affiliationId where staffId = ?;"
					,new Object[]{pageModel.getUserid()});
			int from = 4;

			model.addAttribute("list",list);
			model.addAttribute("h2","削除しますか?");
			model.addAttribute("from",from);
			model.addAttribute("home", "/spring/menu/hakennsyain_info");
			pageModel pgModel = new pageModel();
			model.addAttribute("pgModel", pgModel);

			return "delete";
		}

		//delete実行
		@RequestMapping(value = "/menu/hakennsyain_info", method = RequestMethod.POST, params = "delyes")
		public String staffEditdelyes(@ModelAttribute SqlModel2 sqlModel,BindingResult result,Model model) {

			int id = sqlModel.getId();
			String sqldel = "DELETE FROM tblStaff where staffId = ?";

			try{
				jdbcTemplate.update(sqldel,new Object[]{id});

				model.addAttribute("h2","削除しました");
				model.addAttribute("home", "/spring/menu/hakennsyain_info");
				return "kekka";
			}catch(Exception e){
				model.addAttribute("h2","削除できませんでした");
				model.addAttribute("home", "/spring/menu/hakennsyain_info");
				return "kekka";
			}
		}

		//検索
		@RequestMapping(value = "/menu/hakennsyain_info", method = RequestMethod.POST, params = "kensaku")
		public String staffEditserch(@ModelAttribute SqlModel2 sqlModel,BindingResult result,Model model) {
			String name = sqlModel.getName();

			try{
				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaff "
																		+ "left join tblaffiliation on tblStaff.affiliationId = tblAffiliation.affiliationId "
																		+ "where staffName like \'%"+name+"%\';");

				model.addAttribute("list",list);
				return "hakennsyain_info";
			}catch(Exception e){
				model.addAttribute("h3","見つかりませんでした");
				System.out.println(e);
				return "hakennsyain_info";
			}

		}

}
