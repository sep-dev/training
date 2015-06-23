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
import org.springframework.web.bind.annotation.RequestParam;

import FormModel.SyainModel;

//派遣社員情報ページのコントローラー
	@Controller
	public class SyainController {
		@Autowired
		private JdbcTemplate jdbcTemplate;
			@RequestMapping(value = "/book/syainInfo",  method = RequestMethod.GET)
			public String Syain(Locale locale, Model model) {
				//2つのテーブル（tblAffiliation,tblStaff）から情報を取得、結合
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaff as tb1 "
						+ "left join tblAffiliation as tb2 on tb1.affiliationId = tb2.affiliationId ");
				model.addAttribute("data", list);
				return"syainInfo";
			}

			//派遣社員新規登録画面へ
			@RequestMapping(value="/book/syainInGamen", method=RequestMethod.GET)
			public String SyainIn(Model model){
				SyainModel syainmodel = new SyainModel();
				model.addAttribute("syainModel", syainmodel);
				List<Map<String, Object>> afflist = jdbcTemplate.queryForList("select affiliationId, affiliationName from tblAffiliation ");
				model.addAttribute("Adata", afflist);
				model.addAttribute("List", "null");
				return "syainUpIn";
			}
			//登録処理
			@RequestMapping(value="/book/syainInSyori", method=RequestMethod.POST)
			public String SyainIn2(@Valid @ModelAttribute SyainModel syainmodel, BindingResult result, Model model){
				if (result.hasErrors()) {
					model.addAttribute("message", "空欄があるか入力された数値が不適切です！");
				} else {
				String staffName = syainmodel.getStaffname();
				String staffEmail = syainmodel.getStaffemail();
				String staffTel = syainmodel.getStafftel();
				String staffMobTel = syainmodel.getStaffmobiletel();
				String staffPostCode = syainmodel.getStaffpostcode();
				String staffAdd= syainmodel.getStaffadd();
				String staffNear = syainmodel.getStaffneareststation();
				String affiliationId = syainmodel.getAffiliationid();
				String staffRe = syainmodel.getStaffremarks();
				String sql = "insert into tblStaff (staffName, staffEMail, staffTel, staffMobileTel, staffPostCode, staffAdd, "
						+ "staffNearestStation, affiliationId, staffRemarks) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql,new Object[]{staffName, staffEmail , staffTel, staffMobTel, staffPostCode, staffAdd, staffNear, affiliationId, staffRe});
				}
				//一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaff as tb1 "
						+ "left join tblAffiliation as tb2 on tb1.affiliationId = tb2.affiliationId ");
				model.addAttribute("data", list);
				return"syainInfo";
			}

			//派遣社員情報編集(社員名を押した場合)
			@RequestMapping(value="/book/syainUpLink", method=RequestMethod.GET)
			public String SyainUp1(@ModelAttribute SyainModel syainmodel, Model model, @RequestParam("value") String staffId){
				System.out.println(staffId);
				List<Map<String, Object>> afflist = jdbcTemplate.queryForList("select affiliationId, affiliationName from tblAffiliation ");
				model.addAttribute("Adata", afflist);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblStaff where staffId = " + staffId +";");
				model.addAttribute("staffId", list.get(0).get("staffId"));
				model.addAttribute("staffName", list.get(0).get("staffName"));
				model.addAttribute("staffEMail", list.get(0).get("staffEMail"));
				model.addAttribute("staffTel", list.get(0).get("staffTel"));
				model.addAttribute("staffMobileTel", list.get(0).get("staffMobileTel"));
				model.addAttribute("staffPostCode", list.get(0).get("staffPostCode"));
				model.addAttribute("staffAdd", list.get(0).get("staffAdd"));
				model.addAttribute("staffNearestStation", list.get(0).get("staffNearestStation"));
				model.addAttribute("affiliationId", list.get(0).get("affiliationId"));
				model.addAttribute("staffRemarks", list.get(0).get("staffRemarks"));
				model.addAttribute("List", "edit");
				return "syainUpIn";
			}

			//派遣社員情報編集画面へ(編集ボタンを押した場合)
			@RequestMapping(value="/book/syainUpButton", method=RequestMethod.GET)
			public String SyainUp2(@ModelAttribute SyainModel syainmodel, Model model){
				String staffId = syainmodel.getStaffid();
				System.out.println(staffId);
				List<Map<String, Object>> afflist = jdbcTemplate.queryForList("select affiliationId, affiliationName from tblAffiliation ");
				model.addAttribute("Adata", afflist);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblStaff where staffId = " + staffId +";");
				model.addAttribute("staffId", list.get(0).get("staffId"));
				model.addAttribute("staffName", list.get(0).get("staffName"));
				model.addAttribute("staffEMail", list.get(0).get("staffEMail"));
				model.addAttribute("staffTel", list.get(0).get("staffTel"));
				model.addAttribute("staffMobileTel", list.get(0).get("staffMobileTel"));
				model.addAttribute("staffPostCode", list.get(0).get("staffPostCode"));
				model.addAttribute("staffAdd", list.get(0).get("staffAdd"));
				model.addAttribute("staffNearestStation", list.get(0).get("staffNearestStation"));
				model.addAttribute("affiliationId", list.get(0).get("affiliationId"));
				model.addAttribute("staffRemarks", list.get(0).get("staffRemarks"));
				model.addAttribute("List", "edit");
				return "syainUpIn";
			}

			//DB更新処理
			@RequestMapping(value="/book/syainUpSyori", method=RequestMethod.POST)
			public String SyainUp3(@Valid  @ModelAttribute SyainModel syainmodel, BindingResult result, Model model){
				if (result.hasErrors()) {
					model.addAttribute("message", "空欄があるか入力された数値が不適切です！");
				} else {
				String staffId= syainmodel.getStaffid();
				String staffName= syainmodel.getStaffname();
				String staffEmail= syainmodel.getStaffemail();
				String staffTel= syainmodel.getStafftel();
				String staffMobTel= syainmodel.getStaffmobiletel();
				String staffPostCode= syainmodel.getStaffpostcode();
				String staffAdd= syainmodel.getStaffadd();
				String staffNear= syainmodel.getStaffneareststation();
				String affiliationId = syainmodel.getAffiliationid();
				String staffRe= syainmodel.getStaffremarks();
				System.out.println(staffId);
				String sql = "update tblStaff set staffName=?, staffEMail=?, staffTel=?, staffMobileTel=?,"
						+ "staffPostCode=?, staffAdd=?, StaffNeareStstation=?, AffiliationId=?, staffRemarks=? where staffId=? ";
				jdbcTemplate.update(sql,new Object[]{staffName, staffEmail, staffTel, staffMobTel, staffPostCode, staffAdd, staffNear, affiliationId, staffRe, staffId});
				}
				//社員情報一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaff as tb1 "
						+ "left join tblAffiliation as tb2 on tb1.affiliationId = tb2.affiliationId ");
				model.addAttribute("data", list);
				return"syainInfo";
			}

			//派遣社員情報削除
			@RequestMapping(value="/book/syainDelete", method=RequestMethod.POST)
			public String SyainDe(@ModelAttribute SyainModel syainmodel, Model model){
				String staffId = syainmodel.getStaffid();
				jdbcTemplate.update("delete from tblStaff where staffId= " + staffId +";");
				//社員情報一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaff as tb1 "
						+ "left join tblAffiliation as tb2 on tb1.affiliationId = tb2.affiliationId ");
				model.addAttribute("data", list);
				return"syainInfo";
			}

			//派遣社員情報検索
			@RequestMapping(value="/book/syainSearch", method=RequestMethod.POST)
			public String SyainSe(@ModelAttribute SyainModel syainmodel, Model model){
				String syainSearch = syainmodel.getSsearch();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblStaff where staffName like '%"+syainSearch+"%';");
				model.addAttribute("data", list);
				return"syainInfo";
			}
}
