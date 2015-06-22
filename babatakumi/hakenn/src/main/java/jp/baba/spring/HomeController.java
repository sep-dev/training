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
}
