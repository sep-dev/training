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
public class HomeController {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/", method = RequestMethod.GET )
	public String log(Model model) {

		loginModel logModel = new loginModel();
		model.addAttribute("logModel",logModel);

		return "login";
	}

	//ログイン画面
	@RequestMapping(value = "/", method = RequestMethod.POST,params="in")
	public String log(@ModelAttribute loginModel loginModel,Model model) {

		String id = loginModel.getId();
		String pass = loginModel.getPass();

		loginModel logModel = new loginModel();
		model.addAttribute("logModel",logModel);

		String sql = "select loginUserPass from tblloginUser where loginUser=?;";

		try{
			String password = jdbcTemplate.queryForObject(sql,new Object[]{id},String.class);
			if(pass.equals(password)){
				return "menu";
			}else{
				model.addAttribute("h2","パスワードが違います");
				return "login";
			}
		}catch(Exception e){
			model.addAttribute("h2","ユーザーが存在しません");
			return "login";

		}

	}

	//メニュー画面　兼　派遣先情報
	@RequestMapping(value = "/menu", method = RequestMethod.GET )
	public String hakennsaki(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaffManagement as tba "
																		+ "left join tblStaff as tbs on tba.staffId = tbs.staffId "
																		+ "left join tblClient as tbc on tba.clientId = tbc.clientId;");

		List<Map<String,Object>> list2 = jdbcTemplate.queryForList("select distinct year(startDate) as year from tblStaffManagement");


		model.addAttribute("list",list);
		model.addAttribute("list2",list2);
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "hakennsaki_info";
	}

	//編集画面へ
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "update")
	public String staffmanEdit(@ModelAttribute pageModel pageModel,Model model) {

		int id = pageModel.getUserid();

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select *, "
					+ "year(startDate) as yearS, month(startDate) as monthS, day(startDate) as dayS, "
					+ "year(endDate) as yearE, month(endDate) as monthE, day(endDate) as dayE "
					+ "from tblStaffManagement as tba "
					+ "left join tblStaff as tbs on tba.staffId = tbs.staffId "
					+ "left join tblClient as tbc on tba.clientId = tbc.clientId "
					+ "where staffManId = ?;"
					,new Object[]{id});

		List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT staffId, staffName FROM tblStaff;");
		List<Map<String,Object>> list3 = jdbcTemplate.queryForList("SELECT clientId, clientName FROM tblClient;");

		model.addAttribute("list",list);
		model.addAttribute("list2",list2);
		model.addAttribute("list3",list3);
		model.addAttribute("h2","派遣先編集");
		model.addAttribute("button","更新");

		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "hakennsaki_input";
	}

	//新規画面へ
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "new")
	public String staffmanEditnew(@ModelAttribute pageModel pageModel,Model model) {

		String list = null;
		List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT staffId, staffName FROM tblStaff;");
		List<Map<String,Object>> list3 = jdbcTemplate.queryForList("SELECT clientId, clientName FROM tblClient;");

		model.addAttribute("list",list);
		model.addAttribute("list2",list2);
		model.addAttribute("list3",list3);

		model.addAttribute("h2","派遣先新規登録");
		model.addAttribute("button","登録");

		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "hakennsaki_input";
	}

	//追加と更新
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "change")
	public String staffmanEditchange(@ModelAttribute ManagementSql mansql, BindingResult result,Model model) {

		int up = mansql.getUp();
		int id = mansql.getId();

		String con = mansql.getCon();

		String year1 = mansql.getYearS();
		String month1 = mansql.getMonthS();
		String day1 = mansql.getDayS();

		String year2 = mansql.getYearE();
		String month2 = mansql.getMonthE();
		String day2 = mansql.getDayE();

		String start = year1 + "-" + month1 + "-" +day1;
		String end = year2 + "-" + month2 + "-" +day2;

		if(con == ""){
			if(up == 1){
				List<Map<String,Object>> list = jdbcTemplate.queryForList("select *, "
						+ "year(startDate) as yearS, month(startDate) as monthS, day(startDate) as dayS, "
						+ "year(endDate) as yearE, month(endDate) as monthE, day(endDate) as dayE "
						+ "from tblStaffManagement as tba "
						+ "left join tblStaff as tbs on tba.staffId = tbs.staffId "
						+ "left join tblClient as tbc on tba.clientId = tbc.clientId "
						+ "where staffManId = ?;"
						,new Object[]{id});

				List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT staffId, staffName FROM tblStaff;");
				List<Map<String,Object>> list3 = jdbcTemplate.queryForList("SELECT clientId, clientName FROM tblClient;");

				model.addAttribute("list",list);
				model.addAttribute("list2",list2);
				model.addAttribute("list3",list3);

				model.addAttribute("h2", "更新に失敗しました");
				model.addAttribute("button","更新");
			}else{
				List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT staffId, staffName FROM tblStaff;");
				List<Map<String,Object>> list3 = jdbcTemplate.queryForList("SELECT clientId, clientName FROM tblClient;");

				model.addAttribute("list2",list2);
				model.addAttribute("list3",list3);
				model.addAttribute("h2", "登録に失敗しました");
				model.addAttribute("button","登録");
			}

			return "hakennsaki_input";
		}

		SqlModel2 sqlModel = new SqlModel2();
		model.addAttribute("sqlModel",sqlModel);

		String sqlup = "UPDATE tblStaffManagement "
						+ "SET staffId = ?, clientId = ?, amountMonth = ?, conditions = ?, "
						+ "deductionUnitPrice = ?, overtimeRate = ?, site = ?, startDate = ?, endDate = ?, staffManRemarks = ? "
						+ "WHERE staffManId = ?;";

		String sqlin = "INSERT INTO tblStaffManagement (staffId,clientId,amountMonth,conditions,deductionUnitPrice,"
													+ "overtimeRate,site,startDate,endDate,staffManRemarks) "
													+ "VALUES (?,?,?,?,?,?,?,?,?,?);";

	//更新
	if(up == 1){
		try{
			jdbcTemplate.update(sqlup, new Object[]{mansql.getSub(),mansql.getKigyou(),mansql.getAmo(),con
													,mansql.getDed(),mansql.getOver(),mansql.getSite()
													,start,end,mansql.getRemarks(),mansql.getId()});

			List<Map<String,Object>> list = jdbcTemplate.queryForList("select *, "
					+ "year(startDate) as yearS, month(startDate) as monthS, day(startDate) as dayS, "
					+ "year(endDate) as yearE, month(endDate) as monthE, day(endDate) as dayE "
					+ "from tblStaffManagement as tba "
					+ "left join tblStaff as tbs on tba.staffId = tbs.staffId "
					+ "left join tblClient as tbc on tba.clientId = tbc.clientId "
					+ "where staffManId = ?;"
					,new Object[]{id});

			List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT staffId, staffName FROM tblStaff;");
			List<Map<String,Object>> list3 = jdbcTemplate.queryForList("SELECT clientId, clientName FROM tblClient;");

			model.addAttribute("list",list);
			model.addAttribute("list2",list2);
			model.addAttribute("list3",list3);

			model.addAttribute("h2", "更新しました！");
			model.addAttribute("button","更新");
			return "hakennsaki_input";
		}catch(Exception e){

			List<Map<String,Object>> list = jdbcTemplate.queryForList("select *, "
					+ "year(startDate) as yearS, month(startDate) as monthS, day(startDate) as dayS, "
					+ "year(endDate) as yearE, month(endDate) as monthE, day(endDate) as dayE "
					+ "from tblStaffManagement as tba "
					+ "left join tblStaff as tbs on tba.staffId = tbs.staffId "
					+ "left join tblClient as tbc on tba.clientId = tbc.clientId "
					+ "where staffManId = ?;"
					,new Object[]{id});

			List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT staffId, staffName FROM tblStaff;");
			List<Map<String,Object>> list3 = jdbcTemplate.queryForList("SELECT clientId, clientName FROM tblClient;");

			model.addAttribute("list",list);
			model.addAttribute("list2",list2);
			model.addAttribute("list3",list3);
			model.addAttribute("h2", "更新に失敗しました");
			model.addAttribute("button","更新");
			System.out.println(e);
			return "hakennsaki_input";

		}
		//追加
		}else if(up == 2){
			try{
				jdbcTemplate.update(sqlin, new Object[]{mansql.getSub(),mansql.getKigyou(),mansql.getAmo(),mansql.getCon()
														,mansql.getDed(),mansql.getOver(),mansql.getSite()
														,start,end,mansql.getRemarks()});

				List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT staffId, staffName FROM tblStaff;");
				List<Map<String,Object>> list3 = jdbcTemplate.queryForList("SELECT clientId, clientName FROM tblClient;");

				model.addAttribute("h2", "登録しました！");
				model.addAttribute("button","登録");
				model.addAttribute("list2",list2);
				model.addAttribute("list3",list3);
				return "hakennsaki_input";
			}catch(Exception e){
				List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT staffId, staffName FROM tblStaff;");
				List<Map<String,Object>> list3 = jdbcTemplate.queryForList("SELECT clientId, clientName FROM tblClient;");

				model.addAttribute("h2", "登録に失敗しました");
				model.addAttribute("button","登録");
				model.addAttribute("list2",list2);
				model.addAttribute("list3",list3);

				System.out.println(e);
				return "hakennsaki_input";
			}
		}
		return "hakennsaki_input";
	}

	//delete移動
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "delete")
	public String staffmanEditdel(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblStaffManagement "
																	+ "where staffManId = ?;"
																	,new Object[]{pageModel.getUserid()});
		int from = 5;

		model.addAttribute("list",list);
		model.addAttribute("h2","削除しますか?");
		model.addAttribute("from",from);
		model.addAttribute("home", "/spring/menu");
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "delete";
	}

	//delete実行
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "delyes")
	public String staffmanEditdelyes(@ModelAttribute SqlModel2 sqlModel,Model model) {

		int id = sqlModel.getId();
		String sqldel = "DELETE FROM tblStaffManagement where staffManId = ?";

		try{
			jdbcTemplate.update(sqldel,new Object[]{id});

			model.addAttribute("h2","削除しました");
			model.addAttribute("home", "/spring/menu");
			return "kekka";
		}catch(Exception e){
			model.addAttribute("h2","削除できませんでした");
			model.addAttribute("home", "/spring/menu");
			return "kekka";
		}
	}

	//検索
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "kensaku")
	public String staffmanEditserch(@ModelAttribute ManagementSql mansql,BindingResult result,Model model) {

		String year = mansql.getYear(); //セレクトボックスの年
		String month = mansql.getMonth();//セレクトボックスの月
		String input = mansql.getInput();//入力した文字列

		String start = year + month + "01";
		String end   = year + month + "31";

		try{
			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from ("
			+ "SELECT * FROM tblStaffManagement WHERE startDate >= "+ start +" AND startDate <= "+ end +") AS t1 "
			+ "LEFT JOIN (SELECT staffid,staffname FROM tblstaff WHERE staffname LIKE \'%"+ input +"%\') AS t2 "
			+ "ON t1.staffid = t2.staffid "
			+ "LEFT JOIN (SELECT clientid,clientname FROM tblclient) AS t3 "
			+ "ON t1.clientid = t3.clientid;");

			List<Map<String,Object>> list2 = jdbcTemplate.queryForList("select distinct year(startDate) as year from tblStaffManagement");

			model.addAttribute("list",list);
			model.addAttribute("list2",list2);
			return "hakennsaki_info";
		}catch(Exception e){
			model.addAttribute("h3","見つかりませんでした");
			System.out.println(e);
			return "hakennsaki_info";
		}
	}



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
				}else{
					List<Map<String,Object>> list2 = jdbcTemplate.queryForList("SELECT affiliationId, affiliationName FROM tblAffiliation ;");
					model.addAttribute("list2",list2);
				}
				model.addAttribute("h2", "更新に失敗しました");
				model.addAttribute("button","更新");
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
			}
			model.addAttribute("h2", "更新に失敗しました");
			model.addAttribute("button","更新");
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



	//出向先情報
	@RequestMapping(value = "/menu/syukkousaki_info", method = RequestMethod.GET)
	public String syukkousaki(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblClient;");

		model.addAttribute("list",list);
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "syukkousaki_info";
	}

	//編集画面へ
	@RequestMapping(value = "/menu/syukkousaki_info", method = RequestMethod.POST, params = "update")
	public String clientEdit(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId = ?;"
																	,new Object[]{pageModel.getUserid()});

		model.addAttribute("list",list);
		model.addAttribute("h2","出向先編集");
		model.addAttribute("button","更新");

		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "syukkousaki_input";
	}

	//新規画面へ
	@RequestMapping(value = "/menu/syukkousaki_info", method = RequestMethod.POST, params = "new")
	public String clientEditnew(@ModelAttribute pageModel pageModel,Model model) {

		String list = null;

		model.addAttribute("list",list);
		model.addAttribute("h2","出向先新規登録");
		model.addAttribute("button","登録");

		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "syukkousaki_input";
	}

	//追加と更新
	@RequestMapping(value = "/menu/syukkousaki_info", method = RequestMethod.POST, params = "change")
	public String clientEditchange(@ModelAttribute SqlModel2 sqlModel2,BindingResult result,Model model) {

		int up = sqlModel2.getUp();
		String name = sqlModel2.getName();
		String post = sqlModel2.getPost();
		String add = sqlModel2.getAdd();
		String tel = sqlModel2.getTel();
		String station = sqlModel2.getStation();

		//空文字判定
		if(name == "" || tel == "" || post == "" || add == "" || station == ""){
			if(up == 1){
				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId = ?;"
						,new Object[]{sqlModel2.getId()});

				model.addAttribute("list",list);
			}
			model.addAttribute("h2", "更新に失敗しました");
			model.addAttribute("button","更新");
			return "syukkousaki_input";
		}

		SqlModel2 sqlModel = new SqlModel2();
		model.addAttribute("sqlModel",sqlModel);

		String sqlup = "UPDATE tblClient SET clientName = ?, clientPostCode = ?, clientAdd = ?,clientTel = ?, clientNearestStation = ?, clientRemarks = ? "
											+ "WHERE clientId = ?;";
		String sqlin = "INSERT INTO tblClient (clientName,clientPostCode,clientAdd,clientTel,clientNearestStation,clientRemarks) "
											+ "VALUES (?,?,?,?,?,?);";

		//更新
		if(up == 1){
			try{
				jdbcTemplate.update(sqlup, new Object[]{name,post,add,tel,station,sqlModel2.getRemarks(),sqlModel2.getId()});

				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId = ?;"
																			,new Object[]{sqlModel2.getId()});

				model.addAttribute("list",list);
				model.addAttribute("h2", "更新しました！");
				model.addAttribute("button","更新");
				return "syukkousaki_input";
			}catch(Exception e){
				List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId = ?;"
						,new Object[]{sqlModel2.getId()});

				model.addAttribute("list",list);
				model.addAttribute("h2", "更新に失敗しました");
				model.addAttribute("button","更新");
				System.out.println(e);
				return "syukkousaki_input";

			}
		//追加
		}else if(up == 2){
			try{
				jdbcTemplate.update(sqlin, new Object[]{name,post,add,tel,station,sqlModel2.getRemarks()});
				model.addAttribute("h2", "登録しました！");
				model.addAttribute("button","登録");
				return "syukkousaki_input";
			}catch(Exception e){
				model.addAttribute("h2", "登録に失敗しました");
				model.addAttribute("button","登録");
				return "syukkousaki_input";
			}
		}
		return "syukkousaki_input";
	}

	//delete移動
	@RequestMapping(value = "/menu/syukkousaki_info", method = RequestMethod.POST, params = "delete")
	public String clientEditdel(@ModelAttribute pageModel pageModel,Model model) {

		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId= ?;"
																				,new Object[]{pageModel.getUserid()});
		int from = 2;

		model.addAttribute("list",list);
		model.addAttribute("h2","削除しますか?");
		model.addAttribute("from",from);
		model.addAttribute("home", "/spring/menu/syukkousaki_info");
		pageModel pgModel = new pageModel();
		model.addAttribute("pgModel", pgModel);

		return "delete";
	}

	//delete実行
	@RequestMapping(value = "/menu/syukkousaki_info", method = RequestMethod.POST, params = "delyes")
	public String clientEditdelyes(@ModelAttribute SqlModel2 sqlModel,BindingResult result,Model model) {

		int id = sqlModel.getId();
		String sqldel = "DELETE FROM tblClient where clientId = ?";

		try{
			jdbcTemplate.update(sqldel,new Object[]{id});

			model.addAttribute("h2","削除しました");
			model.addAttribute("home", "/spring/menu/syukkousaki_info");
			return "kekka";
		}catch(Exception e){
			model.addAttribute("h2","削除できませんでした");
			model.addAttribute("home", "/spring/menu/syukkousaki_info");
			return "kekka";
		}
	}

	//検索
	@RequestMapping(value = "/menu/syukkousaki_info", method = RequestMethod.POST, params = "kensaku")
	public String clientEditserch(@ModelAttribute SqlModel2 sqlModel,Model model) {
		String name = sqlModel.getName();

		try{
			List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientName like \'%"+name+"%\';");

			model.addAttribute("list",list);
			return "syukkousaki_info";
		}catch(Exception e){
			model.addAttribute("h3","見つかりませんでした");
			System.out.println(e);
			return "syukkousaki_info";
		}

	}


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
			}
			model.addAttribute("h2", "更新に失敗しました");
			model.addAttribute("button","更新");
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
					jdbcTemplate.update(sqlup, new Object[]{name,user,pass,kennsa});

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
					model.addAttribute("h2", "更新に失敗しました");
					model.addAttribute("button","更新");
					return "newUserInput";

				}
			//追加
			}else if(up == 2){
				try{
					jdbcTemplate.update(sqlin, new Object[]{name,user,pass,kennsa});
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
