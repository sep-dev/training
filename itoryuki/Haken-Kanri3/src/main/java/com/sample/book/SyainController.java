package com.sample.book;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


			//派遣社員新規登録
			@RequestMapping(value="/book/Syain", params="syainInsert1", method=RequestMethod.GET)
			public String SyainIn(Model model){
				SyainModel sm1 = new SyainModel();
				model.addAttribute("syainModel", sm1);
				List<Map<String, Object>> afflist = jdbcTemplate.queryForList("select affiliationId, affiliationName from tblAffiliation ");
				model.addAttribute("Adata", afflist);
				model.addAttribute("List", "null");
				return "SyainUpIn";
			}
			@RequestMapping(value="/book/Syain", params="syainInsert2", method=RequestMethod.POST)
			public String SyainIn2(@ModelAttribute SyainModel sm1, Model model){
				String sn = sm1.getStaffname();
				String se = sm1.getStaffemail();
				String st = sm1.getStafftel();
				String smt = sm1.getStaffmobiletel();
				String spc = sm1.getStaffpostcode();
				String sa= sm1.getStaffadd();
				String sns = sm1.getStaffneareststation();
				String ai = sm1.getAffiliationid();
				String sr = sm1.getStaffremarks();
				String sql = "insert into tblStaff (staffName, staffEMail, staffTel, staffMobileTel, staffPostCode, staffAdd, "
						+ "staffNearestStation, affiliationId, staffRemarks) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql,new Object[]{sn, se, st, smt, spc, sa, sns, ai, sr});
				//一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaff as tb1 "
						+ "left join tblAffiliation as tb2 on tb1.affiliationId = tb2.affiliationId ");
				model.addAttribute("data", list);
				return"syainInfo";
			}


			//派遣社員情報編集(社員名を押した場合)
			@RequestMapping(value="/book/syainUpdate3", method=RequestMethod.GET)
			public String SyainUp1(@ModelAttribute SyainModel sm1, Model model, @RequestParam("value") String si){
				List<Map<String, Object>> afflist = jdbcTemplate.queryForList("select affiliationId, affiliationName from tblAffiliation ");
				model.addAttribute("Adata", afflist);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblStaff where staffId = " + si +";");
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
				return "SyainUpIn";
			}

			//派遣社員情報編集画面へ(編集ボタンを押した場合)
			@RequestMapping(value="/book/Syain", params="syainUpdate1", method=RequestMethod.GET)
			public String SyainUp2(@ModelAttribute SyainModel sm1, Model model){
				String si = sm1.getStaffid();
				List<Map<String, Object>> afflist = jdbcTemplate.queryForList("select affiliationId, affiliationName from tblAffiliation ");
				model.addAttribute("Adata", afflist);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblStaff where staffId = " + si +";");
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
				return "SyainUpIn";
			}

			//DB更新処理
			@RequestMapping(value="/book/Syain", params="syainUpdate2", method=RequestMethod.POST)
			public String SyainUp3(@ModelAttribute SyainModel sm1, Model model){
				String si= sm1.getStaffid();
				String sn= sm1.getStaffname();
				String sem= sm1.getStaffemail();
				String st= sm1.getStafftel();
				String smt= sm1.getStaffmobiletel();
				String spc= sm1.getStaffpostcode();
				String sa= sm1.getStaffadd();
				String ai = sm1.getAffiliationid();
				String sr= sm1.getStaffremarks();
				String sql = "update tblStaff set staffName=?, staffEMail=?, staffTel=?, staffMobileTel=?,"
						+ "staffPostCode=?, staffAdd=?, AffiliationId=?, staffRemarks=? where staffId=? ";
				jdbcTemplate.update(sql,new Object[]{sn, sem, st, smt, spc, sa, ai, sr, si});
				//社員情報一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaff as tb1 "
						+ "left join tblAffiliation as tb2 on tb1.affiliationId = tb2.affiliationId ");
				model.addAttribute("data", list);
				return"syainInfo";
			}


			//派遣社員情報削除
			@RequestMapping(value="/book/Syain", params="syainDelete", method=RequestMethod.POST)
			public String SyainDe(@ModelAttribute SyainModel sm1, Model model){
				String si = sm1.getStaffid();
				jdbcTemplate.update("delete from tblStaff where staffId= " + si +";");
				//社員情報一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaff as tb1 "
						+ "left join tblAffiliation as tb2 on tb1.affiliationId = tb2.affiliationId ");
				model.addAttribute("data", list);
				return"syainInfo";
			}


			//派遣社員情報検索
			@RequestMapping(value="/book/Syain", params="syainSearch", method=RequestMethod.POST)
			public String SyainSe(@ModelAttribute SyainModel sm1, Model model){
				String ss = sm1.getSsearch();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblStaff where staffName like '%"+ss+"%';");
				model.addAttribute("data", list);
				return"syainInfo";
			}


}
