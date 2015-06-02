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

import FormModel.SyozokuModel;

//所属元情報ページのコントローラー
	@Controller
	public class SyozokuController {
		@Autowired
		private JdbcTemplate jdbcTemplate;
			//所属元一覧を表示
			@RequestMapping(value = "/book/syozokuInfo",  method = RequestMethod.GET)
			public String Syozoku(Locale locale, Model model) {
				//2つのテーブル（tblAffiliation,tblStaff）から情報を取得、結合
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblAffiliation");
				model.addAttribute("data", list);
				return"syozokuInfo";
			}


			//所属元新規登録
			@RequestMapping(value="/book/Syozoku", params="syozokuInsert1", method=RequestMethod.GET)
			public String SyozokuIn(Model model){
				SyozokuModel sm2 = new SyozokuModel();
				model.addAttribute("syozokuModel", sm2);
				model.addAttribute("List", "null");
				return "SyozokuUpIn";
			}
			//登録処理
			@RequestMapping(value="/book/Syozoku", params="syozokuInsert2", method=RequestMethod.POST)
			public String SyozokuIn2(@ModelAttribute SyozokuModel sm2, Model model){
				String an = sm2.getAffiliationname();
				String at = sm2.getAffiliationtel();
				String apc = sm2.getAffiliationpostcode();
				String aa = sm2.getAffiliationadd();
				String ans = sm2.getAffiliationneareststation();
				String ar = sm2.getAffiliationremarks();
				//DBに追加
				String sql ="insert into tblAffiliation (affiliationName, affiliationTel, affiliationPostCode, affiliationAdd,"
						+ " affiliationNearestStation, affiliationRemarks) values (?, ?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql,new Object[]{an, at, apc, aa, ans, ar});
				//所属元一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblAffiliation");
				model.addAttribute("data", list);
				return"syozokuInfo";
			}


			//所属元編集画面へ(企業名を押した場合)
			@RequestMapping(value="/book/syozokuUpdate3", method=RequestMethod.GET)
			public String SyozokuUp3(@ModelAttribute SyozokuModel sm2, Model model, @RequestParam("value") String ai){
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId = " + ai +";");
				model.addAttribute("affiliationName", list.get(0).get("affiliationName"));
				model.addAttribute("affiliationTel", list.get(0).get("affiliationTel"));
				model.addAttribute("affiliationPostCode", list.get(0).get("affiliationPostCode"));
				model.addAttribute("affiliationAdd", list.get(0).get("affiliationAdd"));
				model.addAttribute("affiliationNearestStation", list.get(0).get("affiliationNearestStation"));
				model.addAttribute("affiliationRemarks", list.get(0).get("affiliationRemarks"));
				model.addAttribute("List", "edit");
				return "SyozokuUpIn";
			}
			//所属元編集画面へ(編集ボタンを押した場合)
			@RequestMapping(value="/book/Syozoku", params="syozokuUpdate1", method=RequestMethod.GET)
			public String SyozokuUp1(@ModelAttribute SyozokuModel sm2, Model model){
				String ai = sm2.getAffiliationid();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId = " + ai +";");
				model.addAttribute("affiliationName", list.get(0).get("affiliationName"));
				model.addAttribute("affiliationTel", list.get(0).get("affiliationTel"));
				model.addAttribute("affiliationPostCode", list.get(0).get("affiliationPostCode"));
				model.addAttribute("affiliationAdd", list.get(0).get("affiliationAdd"));
				model.addAttribute("affiliationNearestStation", list.get(0).get("affiliationNearestStation"));
				model.addAttribute("affiliationRemarks", list.get(0).get("affiliationRemarks"));
				model.addAttribute("List", "edit");
				return "SyozokuUpIn";
			}
			@RequestMapping(value="/book/Syozoku", params="syozokuUpdate2", method=RequestMethod.POST)
			public String SyozokuUp2(@ModelAttribute SyozokuModel sm2, Model model){
				String ai = sm2.getAffiliationid();
				String an = sm2.getAffiliationname();
				String at = sm2.getAffiliationtel();
				String aps = sm2.getAffiliationpostcode();
				String aa = sm2.getAffiliationadd();
				String ans = sm2.getAffiliationneareststation();
				String ar = sm2.getAffiliationremarks();
				//DBを更新
				String sql = "update tblAffiliation set affiliationName=?, affiliationTel=?, affiliationPostCode=?, affiliationAdd=?,"
						+ "affiliationNearestStation=?, affiliationRemarks=? where affiliationId=? ";
				jdbcTemplate.update(sql,new Object[]{an, at, aps, aa, ans, ar, ai});
				//所属元一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblAffiliation");
				model.addAttribute("data", list);
				return"syozokuInfo";
			}


			//所属元情報削除
			@RequestMapping(value="/book/Syozoku", params="syozokuDelete", method=RequestMethod.POST)
			public String SyozokuDe(@ModelAttribute SyozokuModel sm2, Model model){
				String ai = sm2.getAffiliationid();
				jdbcTemplate.update("delete from tblAffiliation where affiliationId= " + ai +";");
				//所属元一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblAffiliation");
				model.addAttribute("data", list);
				return"syozokuInfo";
			}


			//	所属元情報検索
			@RequestMapping(value="/book/Syozoku", params="syozokuSearch", method=RequestMethod.POST)
			public String SyozokuSe(@ModelAttribute SyozokuModel sm2, Model model){
				String as = sm2.getAsearch();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationName like '%"+as+"%';");
				model.addAttribute("data", list);
				return "syozokuInfo";
			}

	}
