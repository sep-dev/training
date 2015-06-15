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
			@RequestMapping(value="/book/syozokuInsert1", method=RequestMethod.GET)
			public String SyozokuIn1(Model model){
				SyozokuModel syozokumodel = new SyozokuModel();
				model.addAttribute("syozokuModel", syozokumodel);
				model.addAttribute("List", "null");
				return "syozokuUpIn";
			}
			//登録処理
			@RequestMapping(value="/book/syozokuInsert2", method=RequestMethod.POST)
			public String SyozokuIn2(@Valid @ModelAttribute SyozokuModel syozokumodel, BindingResult result, Model model){
				if (result.hasErrors()) {
					model.addAttribute("message", "空欄があるか入力された数値が不適切です！");
				} else {
				String affName = syozokumodel.getAffiliationname();
				String affTel = syozokumodel.getAffiliationtel();
				String affPostCode = syozokumodel.getAffiliationpostcode();
				String affAdd = syozokumodel.getAffiliationadd();
				String affNear = syozokumodel.getAffiliationneareststation();
				String affRe = syozokumodel.getAffiliationremarks();
				//DBに追加
				String sql ="insert into tblAffiliation (affiliationName, affiliationTel, affiliationPostCode, affiliationAdd,"
						+ " affiliationNearestStation, affiliationRemarks) values (?, ?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql,new Object[]{affName, affTel, affPostCode, affAdd, affNear, affRe});
				}
				//所属元一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblAffiliation");
				model.addAttribute("data", list);
				return"syozokuInfo";
			}

			//所属元編集画面へ(企業名を押した場合)
			@RequestMapping(value="/book/syozokuUpdate1", method=RequestMethod.GET)
			public String SyozokuUp1(@ModelAttribute SyozokuModel syozokumodel, Model model, @RequestParam("value") String affId){
				System.out.println(affId);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId = " + affId +";");
				model.addAttribute("affiliationId", list.get(0).get("affiliationId"));
				model.addAttribute("affiliationName", list.get(0).get("affiliationName"));
				model.addAttribute("affiliationTel", list.get(0).get("affiliationTel"));
				model.addAttribute("affiliationPostCode", list.get(0).get("affiliationPostCode"));
				model.addAttribute("affiliationAdd", list.get(0).get("affiliationAdd"));
				model.addAttribute("affiliationNearestStation", list.get(0).get("affiliationNearestStation"));
				model.addAttribute("affiliationRemarks", list.get(0).get("affiliationRemarks"));
				model.addAttribute("List", "edit");
				return "syozokuUpIn";
			}

			//所属元編集画面へ(編集ボタンを押した場合)
			@RequestMapping(value="/book/syozokuUpdate2", method=RequestMethod.GET)
			public String SyozokuUp2(@ModelAttribute SyozokuModel syozokumodel, Model model){
				String affId = syozokumodel.getAffiliationid();
				System.out.println(affId);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationId = " + affId +";");
				model.addAttribute("affiliationId", list.get(0).get("affiliationId"));
				model.addAttribute("affiliationName", list.get(0).get("affiliationName"));
				model.addAttribute("affiliationTel", list.get(0).get("affiliationTel"));
				model.addAttribute("affiliationPostCode", list.get(0).get("affiliationPostCode"));
				model.addAttribute("affiliationAdd", list.get(0).get("affiliationAdd"));
				model.addAttribute("affiliationNearestStation", list.get(0).get("affiliationNearestStation"));
				model.addAttribute("affiliationRemarks", list.get(0).get("affiliationRemarks"));
				model.addAttribute("List", "edit");
				return "syozokuUpIn";
			}

			//DB更新処理
			@RequestMapping(value="/book/syozokuUpdate3", method=RequestMethod.POST)
			public String SyozokuUp3(@Valid @ModelAttribute SyozokuModel syozokumodel, BindingResult result, Model model){
				if (result.hasErrors()) {
					model.addAttribute("message", "空欄があるか入力された数値が不適切です！");
				} else {
				String affId = syozokumodel.getAffiliationid();
				String affName = syozokumodel.getAffiliationname();
				String affTel = syozokumodel.getAffiliationtel();
				String affPostCode = syozokumodel.getAffiliationpostcode();
				String affAdd = syozokumodel.getAffiliationadd();
				String affNear = syozokumodel.getAffiliationneareststation();
				String affRe = syozokumodel.getAffiliationremarks();
				System.out.println(affId);
				//DBを更新
				String sql = "update tblAffiliation set affiliationName=?, affiliationTel=?, affiliationPostCode=?, affiliationAdd=?,"
						+ "affiliationNearestStation=?, affiliationRemarks=? where affiliationId=? ";
				jdbcTemplate.update(sql,new Object[]{affName, affTel, affPostCode, affAdd, affNear, affRe, affId});
				}
				//所属元一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblAffiliation");
				model.addAttribute("data", list);
				return"syozokuInfo";
			}

			//所属元情報削除
			@RequestMapping(value="/book/syozokuDelete", method=RequestMethod.POST)
			public String SyozokuDe(@ModelAttribute SyozokuModel syozokumodel, Model model){
				String affId = syozokumodel.getAffiliationid();
				System.out.println(affId);
				jdbcTemplate.update("delete from tblAffiliation where affiliationId= " + affId +";");
				//所属元一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblAffiliation");
				model.addAttribute("data", list);
				return"syozokuInfo";
			}

			//	所属元情報検索
			@RequestMapping(value="/book/syozokuSearch", method=RequestMethod.POST)
			public String SyozokuSe(@ModelAttribute SyozokuModel syozokumodel, Model model){
				String affSearch = syozokumodel.getAsearch();
				System.out.println(affSearch);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblAffiliation where affiliationName like '%"+affSearch+"%';");
				model.addAttribute("data", list);
				return "syozokuInfo";
			}
	}
