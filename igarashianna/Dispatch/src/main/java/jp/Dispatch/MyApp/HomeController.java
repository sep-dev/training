package jp.Dispatch.MyApp;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

	private static Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	//0ログイン画面
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Home start!!!");
		System.out.println("ログイン突破");
		Staff_Search_Model staff_Search_Model = new Staff_Search_Model();
		model.addAttribute("staff_Search_Model", staff_Search_Model);
		return "LoginList";
	}

	// ⓪メインメニュー
	@RequestMapping(value = "/MainMenu", method = RequestMethod.GET)
	public String home2(Model model) {
		model.addAttribute("title", "メインメニュー");
		return "MainMenu";
	}

	// ①ログイン成功後表示画面・派遣先情報一覧画面
	@RequestMapping(value = "/LoginList", method = RequestMethod.POST)
	public String list(@Valid @ModelAttribute LoginUserModel loginUserModel,
			Staff_Search_Model staff_search_Model, BindingResult result,
			Model model) {
		model.addAttribute("title", "派遣先情報一覧");
		StaffModel staffmodel = new StaffModel();
		model.addAttribute("staffModel", staffmodel);
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select * from tbStaffManagement inner join tbStaff on tbStaffManagement.staffId = tbStaff.staffId inner join  tbClient on tbStaffManagement.clientId = tbClient.clientId;");
		model.addAttribute("data", list);
		System.out.println("ログイン成功！");
		System.out.println(list);
		// 検索用 FormModel
		Staff_Search_Model staff_Search_Model = new Staff_Search_Model();
		model.addAttribute("staff_Search_Model", staff_Search_Model);
		return "LoginList";
	}

	// ①派遣先情報一覧画面リンク用（GET）
	@RequestMapping(value = "/LoginList", method = RequestMethod.GET)
	public String list6(@Valid @ModelAttribute StaffModel staffModel,
			BindingResult result, Model model) {
		model.addAttribute("title", "派遣先情報一覧");
		StaffModel staffmodel = new StaffModel();
		model.addAttribute("staffModel", staffmodel);
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select * from tbStaffManagement inner join tbStaff on tbStaffManagement.staffId = tbStaff.staffId inner join tbClient on tbStaffManagement.clientId = tbClient.clientId;");
		model.addAttribute("data", list);
		System.out.println(list);
		// 検索用 FormModel
		Staff_Search_Model staff_Search_Model = new Staff_Search_Model();
		model.addAttribute("staff_Search_Model", staff_Search_Model);
		return "LoginList";
	}

	// ①検索
	@RequestMapping(value = "/LoginList", method = RequestMethod.POST, params = "search")
	public String list6(@ModelAttribute StaffModel staffmodel,
			Staff_Search_Model staff_Search_model, Model model) {
		//フォームモデルnew宣言
		StaffModel staffModel = new StaffModel();
		model.addAttribute("staffModel", staffModel);
		//検索用フォームモデルnew宣言
		Staff_Search_Model staff_Search_Model = new Staff_Search_Model();
		model.addAttribute("staff_Search_Model", staff_Search_Model);
		System.out.println("検索されたワード =" + staff_Search_model.getSearchword());
		System.out.println("選択された年 =" + staff_Search_model.getDear());
		System.out.println("選択された月 =" + staff_Search_model.getDay());
		System.out.println("選択された年月 =" + staff_Search_model.getDear()
				+ staff_Search_model.getDay());
		//あいまい検索SQL文
		List<Map<String, Object>> sql = jdbcTemplate
				.queryForList(
						"select * from tbStaffManagement inner join tbStaff on tbStaffManagement.staffId = tbStaff.staffId inner join  tbClient on tbStaffManagement.clientId = tbClient.clientId where startDate <= ? and endDate >= ? and (staffName like ? or clientName like ? or amountMonth like ? or conditions like ? or deductionUnitPrice like ? or overtimeRate like ? or site like ? or staffManRemarks like ?)",
						staff_Search_model.getDear()
								+ staff_Search_model.getDay(),
						staff_Search_model.getDear()
								+ staff_Search_model.getDay(), "%"
								+ staff_Search_model.getSearchword() + "%", "%"
								+ staff_Search_model.getSearchword() + "%", "%"
								+ staff_Search_model.getSearchword() + "%", "%"
								+ staff_Search_model.getSearchword() + "%", "%"
								+ staff_Search_model.getSearchword() + "%", "%"
								+ staff_Search_model.getSearchword() + "%", "%"
								+ staff_Search_model.getSearchword() + "%", "%"
								+ staff_Search_model.getSearchword() + "%");
		model.addAttribute("data", sql);
		if (staff_Search_model.getSearchword().equals("")) {
			model.addAttribute("title", "検索失敗");
			model.addAttribute("message", "入力フォームが空白です");
		} else {
			//SQL文の結果がヒットしなかったら
			if(sql.isEmpty()) {
				model.addAttribute("title","検索失敗");
				model.addAttribute("message","入力されたデータに該当するデータは見つかりませんでした。");
				System.out.println("検索結果 =" + sql);
			//SQL文の結果がヒットしたら表示する
			}else{
				System.out.println("検索結果 =" + sql);
				model.addAttribute("data", sql);

		}
	}
		return "/LoginList";
	}



	// (1)編集画面
	@RequestMapping(value = "/LoginList Update", method = RequestMethod.POST)
	public String list2(@ModelAttribute StaffUpdateModel staffUpdatemodel,
			BindingResult result, Model model) {
		model.addAttribute("message", "派遣先編集");
		StaffUpdateModel staffUpdateModel = new StaffUpdateModel();
		model.addAttribute("staffUpdateModel", staffUpdateModel);
		model.addAttribute(staffUpdatemodel.getStaffManId());
		Map<String, Object> SQL = jdbcTemplate.queryForMap(
				"select * from tbStaffManagement where  staffManId=?",
				staffUpdatemodel.getStaffManId());
		model.addAttribute("date", SQL);
		List<StaffUpdateModel> list1 = jdbcTemplate.query(
				"select * from tbStaff",
				new BeanPropertyRowMapper<StaffUpdateModel>(
						StaffUpdateModel.class));
		model.addAttribute("data1", list1);
		List<StaffUpdateModel> list2 = jdbcTemplate.query(
				"select * from tbClient",
				new BeanPropertyRowMapper<StaffUpdateModel>(
						StaffUpdateModel.class));
		model.addAttribute("data2", list2);
		System.out.println("テスト1=" + list1);
		System.out.println("テスト2=" + list2);
		model.addAttribute("amountMonth", SQL.get("AmountMonth"));
		model.addAttribute("conditions", SQL.get("conditions"));
		model.addAttribute("deductionUnitPrice", SQL.get("deductionUnitPrice"));
		model.addAttribute("overtimeRate", SQL.get("overtimeRate"));
		model.addAttribute("site", SQL.get("site"));
		model.addAttribute("startDate", SQL.get("startDate"));
		model.addAttribute("endDate", SQL.get("endDate"));
		model.addAttribute("staffManRemarks", SQL.get("staffManRemarks"));
		model.addAttribute("staffId", SQL.get("staffId"));
		model.addAttribute("clientId", SQL.get("clientId"));
		model.addAttribute("staffManId", SQL.get("staffManId"));

		System.out.println("取得したStaffManId ="
				+ staffUpdatemodel.getStaffManId());
		System.out.println("取得したStaffId =" + staffUpdatemodel.getStaffId());
		System.out.println("取得したclientId =" + staffUpdatemodel.getClientId());
		System.out.println("取得したレコード =" + SQL);
		return "/LoginList Update";
	}

	// (1)編集処理用コントローラー
	@RequestMapping(value = "/LoginList Update", method = RequestMethod.POST, params = "update")
	public String list5(
			@Valid @ModelAttribute StaffUpdateModel staffUpdatemodel,
			BindingResult result, Model model) {
		StaffUpdateModel staffUpdateModel = new StaffUpdateModel();
		model.addAttribute("staffUpdateModel", staffUpdateModel);
		System.out.println("更新するStaffManId ="
				+ staffUpdatemodel.getStaffManId());
		System.out.println("入力編集した出向先Id = " + staffUpdatemodel.getClientId());
		System.out.println("入力編集した派遣社員Id = " + staffUpdatemodel.getStaffId());
		System.out.println("入力編集した単価 = " + staffUpdatemodel.getAmountMonth());
		final String sql = "update tbStaffManagement set staffId=?,clientId=?,amountMonth=?,conditions=?,deductionUnitPrice=?,overtimeRate=?,site=?,startDate=?,endDate=?,staffManRemarks=? where staffManId=?";
		jdbcTemplate.update(
				sql,
				new Object[] { staffUpdatemodel.getStaffId(),
						staffUpdatemodel.getClientId(),
						staffUpdatemodel.getAmountMonth(),
						staffUpdatemodel.getConditions(),
						staffUpdatemodel.getDeductionUnitPrice(),
						staffUpdatemodel.getOvertimeRate(),
						staffUpdatemodel.getSite(),
						staffUpdatemodel.getStartDate(),
						staffUpdatemodel.getEndDate(),
						staffUpdatemodel.getStaffManRemarks(),
						staffUpdatemodel.getStaffManId() });
		model.addAttribute("data", sql);
		System.out.println("更新するStaffId =" + staffUpdatemodel.getStaffManId());
		return "redirect:/LoginList";
	}

	// Ⅰ削除処理用コントローラー
	@RequestMapping(value = "/LoginList Update", method = RequestMethod.POST, params = "delete")
	public String list6(@ModelAttribute StaffDeleteModel staffDeletemodel,
			Model model) {
		StaffDeleteModel staffdeletemodel = new StaffDeleteModel();
		model.addAttribute("staffDeleteModel", staffdeletemodel);
		System.out.println("削除するId =" + staffDeletemodel.getStaffManId());
		jdbcTemplate.update("delete from tbStaffManagement where staffManId=?",
				staffDeletemodel.getStaffManId());
		return "redirect:/LoginList";
	}

	// ➊登録画面
	@RequestMapping(value = "/LoginList Insert", method = RequestMethod.GET)
	public String list3(StaffModel staffModel, Model model) {
		StaffModel staffmodel = new StaffModel();
		model.addAttribute("message", "派遣先登録");
		model.addAttribute("staffModel", staffmodel);
		// 外部結合
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select * from (tbClient join tbStaff on tbClient.clientId = tbStaff.staffId) left join tbStaffManagement as tbStaffManagement on tbStaff.staffId = tbStaffManagement.clientId;");
		model.addAttribute("data", list);
		// tbStaffテーブル呼び出しのSQL文
		List<StaffList> list1 = jdbcTemplate.query("select * from tbStaff",
				new BeanPropertyRowMapper<StaffList>(StaffList.class));
		model.addAttribute("data1", list1);
		System.out.println("テスト=" + list1);
		// tbClientテーブル呼び出しのSQL文
		List<StaffList> list2 = jdbcTemplate.query("select * from tbClient",
				new BeanPropertyRowMapper<StaffList>(StaffList.class));
		model.addAttribute("data2", list2);
		System.out.println("テスト=" + list2);
		return "LoginList Insert";
	}

	// ➊登録成功・失敗画面
	@RequestMapping(value = "/LoginList Insert", method = RequestMethod.POST)
	public String view4(@Valid @ModelAttribute StaffModel staffmodel,
			StaffList staffList, BindingResult result, Model model) {
		StaffModel staffModel = new StaffModel();
		model.addAttribute("staffmodel", staffModel);
		StaffList stafflist = new StaffList();
		model.addAttribute("staffList", stafflist);
		System.out.println(staffList.getStaffId());
		System.out.println(staffList.getClientId());
		if (result.hasErrors()) {
			model.addAttribute("title", "登録失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			final String sql = "insert into tbStaffManagement(staffId,clientId,amountMonth,conditions,deductionUnitPrice,overtimeRate,site,startDate,endDate,staffManRemarks) values(?,?,?,?,?,?,?,?,?,?)";
			jdbcTemplate.update(
					sql,
					new Object[] { staffList.getStaffId(),
							staffList.getClientId(),
							staffmodel.getAmountMonth(),
							staffmodel.getConditions(),
							staffmodel.getDeductionUnitPrice(),
							staffmodel.getOvertimeRate(), staffmodel.getSite(),
							staffmodel.getStartDate(), staffmodel.getEndDate(),
							staffmodel.getStaffManRemarks() });
			model.addAttribute("title", "登録成功");
		}
		return "LoginList Insert";
	}

	// ②派遣社員情報一覧画面
	@RequestMapping(value = "/Staff", method = RequestMethod.GET)
	public String from(@Valid @ModelAttribute StaffManModel staffmanModel,
			BindingResult result, Model model) {

		model.addAttribute("title", "派遣社員情報");
		StaffManModel staffmanmodel = new StaffManModel();
		model.addAttribute("staffManModel", staffmanmodel);
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select * from tbAffliation left join tbStaff on tbAffliation.affliationId = tbStaff.AffliationId;");
		System.out.println(list);
		model.addAttribute("data", list);
		StaffMan_Search_Model staffman_Search_Model = new StaffMan_Search_Model();
		model.addAttribute("staffMan_Search_Model", staffman_Search_Model);
		System.out.println(list.get(0).get("AffliationId"));
		System.out.println(list);
		return "Staff";
	}

	// ②検索
	@RequestMapping(value = "/Staff", method = RequestMethod.POST, params = "search")
	public String from6(@ModelAttribute StaffManModel staffmanmodel,
			StaffMan_Search_Model staffman_Search_model, Model model) {
		//フォームモデルnew宣言
		StaffManModel staffmanModel = new StaffManModel();
		model.addAttribute("staffManModel", staffmanModel);
		//検索用フォームモデルnew宣言
		StaffMan_Search_Model staffman_Search_Model = new StaffMan_Search_Model();
		model.addAttribute("staffMan_Search_Model", staffman_Search_Model);
		System.out.println("テスト =" + staffman_Search_model.getSearchword());
		//あいまい検索SQL文
		List<Map<String, Object>> sql = jdbcTemplate
				.queryForList(
						"select * from tbAffliation left join tbStaff on tbAffliation.affliationId = tbStaff.AffliationId where staffName like ? or staffMail like ? or staffPostCode like ? or staffAdd like ? or staffTel like ? or staffMobileTel like ? or staffNearestStation like ? or affliationName like ? or staffRemarks like ?",
						"%" + staffman_Search_model.getSearchword() + "%", "%"
								+ staffman_Search_model.getSearchword() + "%",
						"%" + staffman_Search_model.getSearchword() + "%", "%"
								+ staffman_Search_model.getSearchword() + "%",
						"%" + staffman_Search_model.getSearchword() + "%", "%"
								+ staffman_Search_model.getSearchword() + "%",
						"%" + staffman_Search_model.getSearchword() + "%", "%"
								+ staffman_Search_model.getSearchword() + "%",
						"%" + staffman_Search_model.getSearchword() + "%");
		System.out.println(sql);
		model.addAttribute("data", sql);
		if (staffman_Search_model.getSearchword().equals("")) {
			model.addAttribute("title", "検索失敗");
			model.addAttribute("message", "入力フォームが空白です");
		} else {
			//SQL文の結果がヒットしなかったら
			if(sql.isEmpty()) {
				model.addAttribute("title","検索失敗");
				model.addAttribute("message","入力されたデータに該当するデータは見つかりませんでした。");
				System.out.println("検索結果 =" + sql);
			//SQL文の結果がヒットしたら表示する
			}else{
				System.out.println("検索結果 =" + sql);
				model.addAttribute("data", sql);

		}
	}
		return "/Staff";
	}

	// (2)編集画面
	@RequestMapping(value = "/Staff Update", method = RequestMethod.POST)
	public String from2(
			@ModelAttribute StaffManUpdateModel staffManUpdatemodel,
			BindingResult result, Model model) {
		model.addAttribute("title", "所属元編集");
		StaffManUpdateModel staffmanUpdateModel = new StaffManUpdateModel();
		model.addAttribute("staffManUpdateModel", staffmanUpdateModel);
		model.addAttribute(staffManUpdatemodel.getStaffId());
		Map<String, Object> SQL = jdbcTemplate.queryForMap(
				"select * from tbStaff where  staffId=?",
				staffManUpdatemodel.getStaffId());
		model.addAttribute("date", SQL);
		List<StaffManUpdateModel> list1 = jdbcTemplate.query(
				"select * from tbAffliation",
				new BeanPropertyRowMapper<StaffManUpdateModel>(
						StaffManUpdateModel.class));
		model.addAttribute("data1", list1);
		System.out.println("テスト=" + list1);
		model.addAttribute("Name", SQL.get("staffName"));
		model.addAttribute("Mail", SQL.get("staffMail"));
		model.addAttribute("Tel", SQL.get("staffTel"));
		model.addAttribute("MobileTel", SQL.get("staffMobileTel"));
		model.addAttribute("PostCode", SQL.get("staffPostCode"));
		model.addAttribute("Add", SQL.get("staffAdd"));
		model.addAttribute("Station", SQL.get("staffNearestStation"));
		model.addAttribute("affliationName", SQL.get("affliationName"));
		model.addAttribute("Remarks", SQL.get("affliationRemarks"));
		model.addAttribute("affliationId", SQL.get("affliationId"));
		model.addAttribute("Id", SQL.get("staffId"));

		System.out.println("取得したId =" + staffManUpdatemodel.getStaffId());
		System.out.println("取得したAffliationId ="
				+ staffManUpdatemodel.getAffliationId());
		System.out.println("取得したレコード =" + SQL);
		return "/Staff Update";
	}

	// (2)編集処理用コントローラー
	@RequestMapping(value = "/Staff Update", method = RequestMethod.POST, params = "update")
	public String from5(
			@Valid @ModelAttribute StaffManUpdateModel staffManUpdatemodel,
			BindingResult result, Model model) {
		StaffManUpdateModel staffmanUpdateModel = new StaffManUpdateModel();
		model.addAttribute("staffManUpdateModel", staffmanUpdateModel);
		System.out.println("更新するId =" + staffManUpdatemodel.getStaffId());
		System.out.println("入力編集したユーザー名称 = "
				+ staffManUpdatemodel.getStaffName());
		System.out.println("入力編集した派遣社員所属元Id = "
				+ staffManUpdatemodel.getAffliationId());
		if (result.hasErrors()) {
			model.addAttribute("title", "更新失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			final String sql = "update tbStaff set staffName=?,staffMail=?,staffTel=?,staffMobileTel=?,staffPostCode=?,staffAdd=?,staffNearestStation=?,affliationId=?,staffRemarks=? where staffId=?";
			jdbcTemplate.update(sql,
					new Object[] { staffManUpdatemodel.getStaffName(),
							staffManUpdatemodel.getStaffMail(),
							staffManUpdatemodel.getStaffTel(),
							staffManUpdatemodel.getStaffMobileTel(),
							staffManUpdatemodel.getStaffPostCode(),
							staffManUpdatemodel.getStaffAdd(),
							staffManUpdatemodel.getStaffNearestStation(),
							staffManUpdatemodel.getAffliationId(),
							staffManUpdatemodel.getStaffRemarks(),
							staffManUpdatemodel.getStaffId() });
			model.addAttribute("data", sql);

		}
		return "redirect:/Staff";
	}

	// Ⅱ削除処理用コントローラー
	@RequestMapping(value = "/Staff Update", method = RequestMethod.POST, params = "delete")
	public String from6(
			@ModelAttribute StaffManDeleteModel staffManDeletemodel, Model model) {
		StaffManDeleteModel staffmandeletemodel = new StaffManDeleteModel();
		model.addAttribute("staffManDeleteModel", staffmandeletemodel);
		System.out.println("削除するId =" + staffManDeletemodel.getStaffId());
		jdbcTemplate.update("delete from tbStaff where staffId=?",
				staffManDeletemodel.getStaffId());
		return "redirect:/Staff";
	}

	// ❷登録画面
	@RequestMapping(value = "/Staff Insert", method = RequestMethod.GET)
	public String from3(Model model) {
		StaffManModel staffmanmodel = new StaffManModel();
		model.addAttribute("message", "派遣社員登録");
		model.addAttribute("staffmanModel", staffmanmodel);
		List<StaffList> list1 = jdbcTemplate.query(
				"select * from tbAffliation",
				new BeanPropertyRowMapper<StaffList>(StaffList.class));
		model.addAttribute("data1", list1);
		System.out.println("テスト=" + list1);
		return "Staff Insert";
	}

	// ➋登録成功・失敗画面
	@RequestMapping(value = "/Staff Insert", method = RequestMethod.POST)
	public String from3(@Valid @ModelAttribute StaffManModel staffmanmodel,
			StaffList staffList, BindingResult result, Model model) {
		System.out.println(staffmanmodel.getAffliationId());
		StaffManModel staffManmodel = new StaffManModel();
		model.addAttribute("staffmanModel", staffManmodel);
		if (result.hasErrors()) {
			model.addAttribute("title", "登録失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			final String sql = "insert into tbStaff(staffName,staffMail,staffTel,staffMobileTel,staffPostCode,staffAdd,staffNearestStation,affliationId,staffRemarks) value(?,?,?,?,?,?,?,?,?)";
			jdbcTemplate.update(
					sql,
					new Object[] { staffmanmodel.getStaffName(),
							staffmanmodel.getStaffMail(),
							staffmanmodel.getStaffTel(),
							staffmanmodel.getStaffMobileTel(),
							staffmanmodel.getStaffPostCode(),
							staffmanmodel.getStaffAdd(),
							staffmanmodel.getStaffNearestStation(),
							staffList.getAffliationId(),
							staffmanmodel.getStaffRemarks() });
			model.addAttribute("title", "登録成功");
		}
		return "Staff Insert";
	}

	// ③所属元情報一覧画面
	@RequestMapping(value = "/Affliation", method = RequestMethod.GET)
	public String view(@Valid @ModelAttribute AffliationModel affliationModel,
			BindingResult result, Model model) {
		model.addAttribute("title", "所属元情報");
		AffliationModel affliationmodel = new AffliationModel();
		model.addAttribute("AffliationModel", affliationmodel);
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select * from tbAffliation");
		model.addAttribute("data", list);
		Affliation_Search_Model affliation_Search_Model = new Affliation_Search_Model();
		model.addAttribute("affliation_Search_Model", affliation_Search_Model);
		System.out.println(list);
		return "Affliation";
	}

	// ③検索
	@RequestMapping(value = "/Affliation", method = RequestMethod.POST, params = "search")
	public String view6(@ModelAttribute AffliationModel affliationmodel,
			Affliation_Search_Model affliation_Search_model, Model model) {
		//フォームモデルnew宣言
		AffliationModel affliationModel = new AffliationModel();
		model.addAttribute("AffliationModel", affliationModel);
		//検索用フォームモデルnew宣言
		Affliation_Search_Model affliation_Search_Model = new Affliation_Search_Model();
		model.addAttribute("affliation_Search_Model", affliation_Search_Model);
		System.out.println("テスト =" + affliation_Search_model.getSearchword());
		//あいまい検索SQL文
		List<Map<String, Object>> sql = jdbcTemplate
				.queryForList(
						"select * from tbAffliation where affliationName like ? or affliationPostCode like ? or affliationAdd like ? or affliationTel like ? or affliationNearestStation like ? or affliationRemarks like ?",
						"%" + affliation_Search_model.getSearchword() + "%",
						"%" + affliation_Search_model.getSearchword() + "%",
						"%" + affliation_Search_model.getSearchword() + "%",
						"%" + affliation_Search_model.getSearchword() + "%",
						"%" + affliation_Search_model.getSearchword() + "%",
						"%" + affliation_Search_model.getSearchword() + "%");
		System.out.println(sql);
		model.addAttribute("data", sql);
		//入力フォームが空白
		if (affliation_Search_model.getSearchword().equals("")) {
			model.addAttribute("title", "検索失敗");
			model.addAttribute("message", "入力フォームが空白です");
		} else {
			//SQL文の結果がヒットしなかったら
			if(sql.isEmpty()) {
				model.addAttribute("title","検索失敗");
				model.addAttribute("message","入力されたデータに該当するデータは見つかりませんでした。");
				System.out.println("検索結果 =" + sql);
			//SQL文の結果がヒットしたら表示する
			}else{
				System.out.println("検索結果 =" + sql);
				model.addAttribute("data", sql);

		}
	}
		return "/Affliation";
	}

	// (3)編集画面
	@RequestMapping(value = "/Affliation Update", method = RequestMethod.POST)
	public String view2(
			@ModelAttribute AffliationUpdateModel affliationUpdatemodel,
			BindingResult result, Model model) {
		model.addAttribute("title", "所属元編集");
		AffliationUpdateModel affliationUpdateModel = new AffliationUpdateModel();
		model.addAttribute("affliationUpdateModel", affliationUpdateModel);
		model.addAttribute(affliationUpdatemodel.getAffliationId());
		Map<String, Object> SQL = jdbcTemplate.queryForMap(
				"select * from tbAffliation where  affliationId=?",
				affliationUpdatemodel.getAffliationId());
		model.addAttribute("date", SQL);
		model.addAttribute("Name", SQL.get("affliationName"));
		model.addAttribute("Tel", SQL.get("affliationTel"));
		model.addAttribute("PostCode", SQL.get("affliationPostCode"));
		model.addAttribute("Station", SQL.get("affliationNearestStation"));
		model.addAttribute("Remarks", SQL.get("affliationRemarks"));
		model.addAttribute("Id", SQL.get("affliationId"));
		System.out
				.println("取得したId =" + affliationUpdatemodel.getAffliationId());
		System.out.println("取得したレコード =" + SQL);
		return "/Affliation Update";
	}

	// (3)編集処理用コントローラー
	@RequestMapping(value = "/Affliation Update", method = RequestMethod.POST, params = "update")
	public String view5(
			@Valid @ModelAttribute AffliationUpdateModel affliationUpdatemodel,
			BindingResult result, Model model) {
		AffliationUpdateModel affliationUpdateModel = new AffliationUpdateModel();
		model.addAttribute("affliationUpdateModel", affliationUpdateModel);
		System.out
				.println("更新するId =" + affliationUpdatemodel.getAffliationId());
		System.out.println("入力編集したユーザー名称 = "
				+ affliationUpdatemodel.getAffliationName());
		if (result.hasErrors()) {
			model.addAttribute("title", "更新失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			final String sql = "update tbAffliation set affliationName=?,affliationTel=?,affliationPostCode=?,affliationAdd=?,affliationNearestStation=?,affliationRemarks=? where affliationId=?";
			jdbcTemplate
					.update(sql,
							new Object[] {
									affliationUpdatemodel.getAffliationName(),
									affliationUpdatemodel.getAffliationTel(),
									affliationUpdatemodel
											.getAffliationPostCode(),
									affliationUpdatemodel.getAffliationAdd(),
									affliationUpdatemodel
											.getAffliationNearestStation(),
									affliationUpdatemodel
											.getAffliationRemarks(),
									affliationUpdatemodel.getAffliationId() });
		}
		return "redirect:/Affliation";
	}

	// Ⅲ削除処理用コントローラー
	@RequestMapping(value = "/Affliation Update", method = RequestMethod.POST, params = "delete")
	public String view6(
			@ModelAttribute AffliationDeleteModel affliationDeletemodel,
			Model model) {
		AffliationDeleteModel affliationdeletemodel = new AffliationDeleteModel();
		model.addAttribute("affliationDeleteModel", affliationdeletemodel);
		System.out
				.println("削除するId =" + affliationDeletemodel.getAffliationId());
		jdbcTemplate.update("delete from tbAffliation where affliationId=?",
				affliationDeletemodel.getAffliationId());
		return "redirect:/Affliation";
	}

	// ❸登録画面
	@RequestMapping(value = "/Affliation Insert", method = RequestMethod.GET)
	public String view3(@ModelAttribute AffliationModel affliationmodel,
			Model model) {
		model.addAttribute("message", "所属元登録");
		AffliationModel affliationModel = new AffliationModel();
		model.addAttribute("affliationModel", affliationModel);
		return "Affliation Insert";
	}

	// ➌登録成功・失敗画面
	@RequestMapping(value = "/Affliation Insert", method = RequestMethod.POST)
	public String view4(@Valid @ModelAttribute AffliationModel affliationmodel,
			BindingResult result, Model model) {
		AffliationModel affliationModel = new AffliationModel();
		model.addAttribute("affliationModel", affliationModel);
		if (result.hasErrors()) {
			model.addAttribute("title", "登録失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			final String sql = "insert into tbAffliation(affliationName,affliationTel,affliationPostCode,affliationAdd,affliationNearestStation,affliationRemarks) value(?,?,?,?,?,?)";
			jdbcTemplate.update(
					sql,
					new Object[] { affliationmodel.getAffliationName(),
							affliationmodel.getAffliationTel(),
							affliationmodel.getAffliationPostCode(),
							affliationmodel.getAffliationAdd(),
							affliationmodel.getAffliationNearestStation(),
							affliationmodel.getAffliationRemarks() });
			model.addAttribute("title", "登録成功");
		}
		return "Affliation Insert";
	}

	// ④出向先情報一覧画面
	@RequestMapping(value = "/Client", method = RequestMethod.GET)
	public String client(Model model) {
		model.addAttribute("title", "出向先情報");
		ClientModel clientmodel = new ClientModel();
		model.addAttribute("ClientModel", clientmodel);
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select * from tbClient");
		model.addAttribute("data", list);
		Client_Search_Model client_Search_Model = new Client_Search_Model();
		model.addAttribute("client_Search_Model", client_Search_Model);
		System.out.println(list);
		return "Client";
	}

	// ④検索
	@RequestMapping(value = "/Client", method = RequestMethod.POST, params = "search")
	public String client6(@ModelAttribute ClientModel clientmodel,
			Client_Search_Model client_Search_model, Model model) {
		//フォームモデルnew宣言
		ClientModel clientModel = new ClientModel();
		model.addAttribute("ClientModel", clientModel);
		//検索用フォームモデルnew宣言
		Client_Search_Model client_Search_Model = new Client_Search_Model();
		model.addAttribute("client_Search_Model", client_Search_Model);
		System.out.println("テスト =" + client_Search_model.getSearchword());
		//あいまい検索のSQL文
		List<Map<String, Object>> sql = jdbcTemplate
				.queryForList(
						"select * from tbClient where clientName like ? or clientPostCode like ? or clientAdd like ? or clientTel like ? or clientNearestStation like ? or clientRemarks like ?",
						"%" + client_Search_model.getSearchword() + "%", "%"
								+ client_Search_model.getSearchword() + "%",
						"%" + client_Search_model.getSearchword() + "%", "%"
								+ client_Search_model.getSearchword() + "%",
						"%" + client_Search_model.getSearchword() + "%", "%"
								+ client_Search_model.getSearchword() + "%");
		System.out.println(sql);
		model.addAttribute("data", sql);
		//入力フォームが空白だったら
		if (client_Search_model.getSearchword().equals("")) {
			model.addAttribute("title", "検索失敗");
			model.addAttribute("message", "入力フォームが空白です");
		} else {
			//SQL文の結果がヒットしなかったら
			if(sql.isEmpty()) {
				model.addAttribute("title","検索失敗");
				model.addAttribute("message","入力されたデータに該当するデータは見つかりませんでした。");
				System.out.println("検索結果 =" + sql);
			//SQL文の結果がヒットしたら表示する
			}else{
				System.out.println("検索結果 =" + sql);
				model.addAttribute("data", sql);

		}
	}
		return "/Client";
	}

	// (4)編集画面
	@RequestMapping(value = "/Client Update", method = RequestMethod.POST)
	public String client2(@ModelAttribute ClientUpdateModel clientUpdatemodel,
			BindingResult result, Model model) {
		model.addAttribute("title", "出向先編集");
		ClientUpdateModel clientUpdateModel = new ClientUpdateModel();
		model.addAttribute("clientUpdateModel", clientUpdateModel);
		model.addAttribute(clientUpdatemodel.getClientId());
		Map<String, Object> SQL = jdbcTemplate.queryForMap(
				"select * from tbClient where  clientId=?",
				clientUpdatemodel.getClientId());
		model.addAttribute("date", SQL);
		model.addAttribute("Name", SQL.get("clientName"));
		model.addAttribute("Tel", SQL.get("clientTel"));
		model.addAttribute("PostCode", SQL.get("clientPostCode"));
		model.addAttribute("Station", SQL.get("clientNearestStation"));
		model.addAttribute("Remarks", SQL.get("clientRemarks"));
		model.addAttribute("Id", SQL.get("clientId"));
		System.out.println("取得したId =" + clientUpdatemodel.getClientId());
		System.out.println("取得したレコード =" + SQL);
		return "/Client Update";
	}

	// (4)編集処理用コントローラー
	@RequestMapping(value = "/Client Update", method = RequestMethod.POST, params = "update")
	public String client5(
			@Valid @ModelAttribute ClientUpdateModel clientUpdatemodel,
			BindingResult result, Model model) {
		ClientUpdateModel clientUpdateModel = new ClientUpdateModel();
		model.addAttribute("clientUpdateModel", clientUpdateModel);
		System.out.println("更新するId =" + clientUpdatemodel.getClientId());
		System.out.println("入力編集したユーザー名称 = "
				+ clientUpdatemodel.getClientName());
		if (result.hasErrors()) {
			model.addAttribute("title", "更新失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			final String sql = "update tbClient set clientName=?,clientTel=?,clientPostCode=?,clientAdd=?,clientNearestStation=?,clientRemarks=? where clientId=?";
			jdbcTemplate.update(
					sql,
					new Object[] { clientUpdatemodel.getClientName(),
							clientUpdatemodel.getClientTel(),
							clientUpdatemodel.getClientPostCode(),
							clientUpdatemodel.getClientAdd(),
							clientUpdatemodel.getClientNearestStation(),
							clientUpdatemodel.getClientRemarks(),
							clientUpdatemodel.getClientId() });
		}
		return "redirect:/Client";
	}

	// Ⅳ削除処理用コントローラー
	@RequestMapping(value = "/Client Update", method = RequestMethod.POST, params = "delete")
	public String client6(@ModelAttribute ClientDeleteModel clientDeletemodel,
			Model model) {
		ClientDeleteModel clientdeletemodel = new ClientDeleteModel();
		model.addAttribute("clientDeleteModel", clientdeletemodel);
		System.out.println("削除するId =" + clientDeletemodel.getClientId());
		jdbcTemplate.update("delete from tbClient where clientId=?",
				clientDeletemodel.getClientId());
		return "redirect:/Client";
	}

	// ➍登録画面
	@RequestMapping(value = "/Client Insert", method = RequestMethod.GET)
	public String client3(Model model) {
		model.addAttribute("message", "出向先登録");
		ClientModel clientModel = new ClientModel();
		model.addAttribute("clientModel", clientModel);
		return "Client Insert";
	}

	// ➍登録処理コントローラー
	@RequestMapping(value = "/Client Insert", method = RequestMethod.POST)
	public String client4(@Valid @ModelAttribute ClientModel clientmodel,
			BindingResult result, Model model) {
		ClientModel clientModel = new ClientModel();
		model.addAttribute("clientModel", clientModel);
		if (result.hasErrors()) {
			model.addAttribute("title", "登録失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			final String sql = "insert into tbClient(clientName,clientTel,clientPostCode,clientAdd,clientNearestStation,clientRemarks) value(?,?,?,?,?,?)";
			jdbcTemplate.update(
					sql,
					new Object[] { clientmodel.getClientName(),
							clientmodel.getClientTel(),
							clientmodel.getClientPostCode(),
							clientmodel.getClientAdd(),
							clientmodel.getClientNearestStation(),
							clientmodel.getClientRemarks() });
		}
		return "Client Insert";
	}

	// ⑤ログインユーザー情報一覧画面
	@RequestMapping(value = "/LoginUserList", method = RequestMethod.GET)
	public String user(@ModelAttribute LoginUserModel loginUsermodel,
			LoginUser_Search_Model loginUser_Search_model, Model model) {
		model.addAttribute("title", "ログインユーザー情報");
		LoginUserModel loginUserModel = new LoginUserModel();
		model.addAttribute("loginUserModel", loginUserModel);
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select * from tbLoginUser");
		model.addAttribute("data", list);
		LoginUser_Search_Model loginUser_Search_Model = new LoginUser_Search_Model();
		model.addAttribute("loginUser_Search_Model", loginUser_Search_Model);
		System.out.println(list);
		return "LoginUserList";
	}

	// ⑤検索
	@RequestMapping(value = "/LoginUserList", method = RequestMethod.POST, params = "search")
	public String user6(@ModelAttribute LoginUserModel loginUsermodel,BindingResult result,
			LoginUser_Search_Model loginUser_Search_model, Model model) {
		//フォームモデルをnew宣言
		LoginUserModel loginUserModel = new LoginUserModel();
		model.addAttribute("loginUserModel", loginUserModel);
		//検索用フォームモデルをnew宣言
		LoginUser_Search_Model loginUser_Search_Model = new LoginUser_Search_Model();
		model.addAttribute("loginUser_Search_Model", loginUser_Search_Model);

		System.out.println("入力したデータ =" + loginUser_Search_model.getSearchword());
		//あいまい検索SQL文
		List<Map<String,Object>> sql = jdbcTemplate.queryForList("select * from tbLoginUser where loginUserName like ? or loginUser like ? or loginUserPass like ?",
						"%" + loginUser_Search_model.getSearchword() + "%", "%"
								+ loginUser_Search_model.getSearchword() + "%",
						"%" + loginUser_Search_model.getSearchword() + "%");
		model.addAttribute("date",sql);
		//入力フォームが空白と等しかったら
		if (loginUser_Search_model.getSearchword().equals("")) {
			model.addAttribute("title", "検索失敗");
			model.addAttribute("message", "入力フォームが空白です");
		} else {
			//SQL文の結果がヒットしなかったら
			if(sql.isEmpty()) {
				model.addAttribute("title","検索失敗");
				model.addAttribute("message","入力されたデータに該当するデータは見つかりませんでした。");
				System.out.println("検索結果 =" + sql);
			//SQL文の結果がヒットしたら表示する
			}else{
				System.out.println("検索結果 =" + sql);
				model.addAttribute("data", sql);

		}
	}
		return "/LoginUserList";
	}


	// (5)編集画面
	@RequestMapping(value = "/LoginUserList Update", method = RequestMethod.POST)
	public String user2(
			@ModelAttribute LoginUserUpdateModel loginUserUpdatemodel,
			BindingResult result, Model model) {
		model.addAttribute("title", "管理者編集");
		LoginUserUpdateModel loginUserUpdateModel = new LoginUserUpdateModel();
		model.addAttribute("loginUserUpdateModel", loginUserUpdateModel);
		model.addAttribute(loginUserUpdatemodel.getLoginUserID());
		Map<String, Object> SQL = jdbcTemplate.queryForMap(
				"select * from tbLoginUser where  loginUserID=?",
				loginUserUpdatemodel.getLoginUserID());
		model.addAttribute("date", SQL);
		model.addAttribute("Name", SQL.get("LoginUserName"));
		model.addAttribute("User", SQL.get("loginUser"));
		model.addAttribute("Id", SQL.get("loginUserID"));
		System.out.println("取得したId =" + loginUserUpdatemodel.getLoginUserID());
		System.out.println("取得したレコード =" + SQL);
		return "/LoginUserList Update";
	}

	// (5)編集処理用コントローラー
	@RequestMapping(value = "/LoginUserList Update", method = RequestMethod.POST, params = "update")
	public String user5(
			@Valid @ModelAttribute LoginUserUpdateModel loginUserUpdatemodel,
			BindingResult result, Model model) {
		LoginUserUpdateModel loginuserupdatemodel = new LoginUserUpdateModel();
		model.addAttribute("loginUserUpdateModel", loginuserupdatemodel);
		System.out.println("更新するId =" + loginUserUpdatemodel.getLoginUserID());
		System.out.println("入力編集したユーザー名称 = "
				+ loginUserUpdatemodel.getLoginUserName());
		if (result.hasErrors()) {
			model.addAttribute("title", "更新失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			if (loginUserUpdatemodel.getLoginUserPass().equals(
					loginUserUpdatemodel.getLoginUserPassCheck())) {
				final String sql = "update tbLoginUser set loginUserName=?,loginUser=?,loginUserPass=? where loginUserID=?";
				jdbcTemplate.update(sql,
						new Object[] { loginUserUpdatemodel.getLoginUserName(),
								loginUserUpdatemodel.getLoginUser(),
								loginUserUpdatemodel.getLoginUserPass(),
								loginUserUpdatemodel.getLoginUserID() });
			} else {
				model.addAttribute("title", "更新失敗");
				model.addAttribute("message", "パスワードが一致しません！パスワードを確認してください");
			}
		}
		return "redirect:/LoginUserList";
	}

	// Ⅴ削除処理用コントローラー
	@RequestMapping(value = "/LoginUserList Update", method = RequestMethod.POST, params = "delete")
	public String use6(
			@ModelAttribute LoginUserDeleteModel loginUserDeletemodel,
			Model model) {
		LoginUserDeleteModel loginuserdeletemodel = new LoginUserDeleteModel();
		model.addAttribute("loginUserDeleteModel", loginuserdeletemodel);
		System.out.println("削除するId =" + loginUserDeletemodel.getLoginUserID());
		jdbcTemplate.update("delete from tbLoginUser where loginUserID=?",
				loginUserDeletemodel.getLoginUserID());
		return "redirect:/LoginUserList";
	}

	// ➎登録画面
	@RequestMapping(value = "/LoginUserList Insert", method = RequestMethod.GET)
	public String user3(Model model) {
		LoginUserModel loginUsermodel = new LoginUserModel();
		model.addAttribute("title","管理者登録");
		model.addAttribute("loginUserModel", loginUsermodel);
		return "LoginUserList Insert";
	}

	// ➎登録成功・失敗画面
	@RequestMapping(value = "/LoginUserList Insert", method = RequestMethod.POST)
	public String user4(@Valid @ModelAttribute LoginUserModel loginUsermodel,
			BindingResult result, Model model) {
		LoginUserModel loginUserModel = new LoginUserModel();
		model.addAttribute("loginUserModel", loginUserModel);
		System.out.println(loginUsermodel.getLoginUserPass());
		System.out.println(loginUsermodel.getLoginUserPassCheck());
		if (result.hasErrors()) {
			model.addAttribute("title", "登録失敗");
			model.addAttribute("message", "値を再チェックしてください");
		} else {
			if (loginUsermodel.getLoginUserPass().equals(
					loginUsermodel.getLoginUserPassCheck())) {
				final String sql = "insert into tbLoginUser(loginUserName,loginUser,loginUserPass) value(?,?,?)";
				jdbcTemplate.update(
						sql,
						new Object[] { loginUsermodel.getLoginUserName(),
								loginUsermodel.getLoginUser(),
								loginUsermodel.getLoginUserPass() });
				model.addAttribute("title", "登録成功");
			} else {
				model.addAttribute("title", "登録失敗");
				model.addAttribute("message", "パスワードが一致していません");
			}
		}
		return "LoginUserList Insert";
	}
}
