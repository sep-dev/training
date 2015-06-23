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

import FormModel.SyukkoModel;

//出向先情報一覧ページのコントローラー
	@Controller
	public class SyukkoController {
		@Autowired
		private JdbcTemplate jdbcTemplate;
			//出向先一覧を表示
			@RequestMapping(value = "/book/syukkoInfo",  method = RequestMethod.GET)
			public String Syukko(Locale locale, Model model) {
				//2つのテーブル（tblAffiliation,tblStaff）から情報を取得、結合
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("data", list);
				return"syukkoInfo";
			}

			//出向先新規登録
			@RequestMapping(value="/book/syukkoInGamen", method=RequestMethod.GET)
			public String SyukkoIn1(Model model){
				SyukkoModel syukkomodel = new SyukkoModel();
				model.addAttribute("syukkoModel", syukkomodel);
				model.addAttribute("List", "null");
				return "syukkoUpIn";
			}
			@RequestMapping(value="/book/syukkoInSyori", method=RequestMethod.POST)
			public String SyukkoIn2(@Valid@ModelAttribute SyukkoModel syukkomodel, BindingResult result, Model model){
				if (result.hasErrors()) {
					model.addAttribute("message", "空欄があるか入力された数値が不適切です！");
				} else {
				String clientName = syukkomodel.getClientname();
				String clientTel = syukkomodel.getClienttel();
				String clientPostCode = syukkomodel.getClientpostcode();
				String clientAdd= syukkomodel.getClientadd();
				String clientNear = syukkomodel.getClientneareststation();
				String clientRe = syukkomodel.getClientremarks();
				String sql = "insert into tblClient (clientName, clientTel, clientPostCode, clientAdd, clientNearestStation, clientRemarks) values (?, ?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql,new Object[]{clientName, clientTel, clientPostCode, clientAdd, clientNear, clientRe});
				}
				//出向先一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("data", list);
				return"syukkoInfo";
			}

			//出向先編集画面へ(企業名を押した場合)
			@RequestMapping(value="/book/syukkoUpLink", method=RequestMethod.GET)
			public String SyukkoUp1(@ModelAttribute SyukkoModel syukkomodel, Model model, @RequestParam("value") String clientId){
				System.out.println(clientId);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId = " + clientId +";");
				model.addAttribute("clientId", list.get(0).get("clientId"));
				model.addAttribute("clientName", list.get(0).get("clientName"));
				model.addAttribute("clientTel", list.get(0).get("clientTel"));
				model.addAttribute("clientPostCode", list.get(0).get("clientPostCode"));
				model.addAttribute("clientAdd", list.get(0).get("clientAdd"));
				model.addAttribute("clientNearestStation", list.get(0).get("clientNearestStation"));
				model.addAttribute("clientRemarks", list.get(0).get("clientRemarks"));
				model.addAttribute("clientId", list.get(0).get("clientId"));
				model.addAttribute("List", "edit");
				return "syukkoUpIn";
			}

			//出向先編集画面へ(編集ボタンを押した場合)
			@RequestMapping(value="/book/syukkoUpButton", method=RequestMethod.GET)
			public String SyukkoUp2(@ModelAttribute SyukkoModel syukkomodel, Model model){
				String clientId = syukkomodel.getClientid();
				System.out.println(clientId);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId = " + clientId +";");
				model.addAttribute("clientId", list.get(0).get("clientId"));
				model.addAttribute("clientName", list.get(0).get("clientName"));
				model.addAttribute("clientTel", list.get(0).get("clientTel"));
				model.addAttribute("clientPostCode", list.get(0).get("clientPostCode"));
				model.addAttribute("clientAdd", list.get(0).get("clientAdd"));
				model.addAttribute("clientNearestStation", list.get(0).get("clientNearestStation"));
				model.addAttribute("clientRemarks", list.get(0).get("clientRemarks"));
				model.addAttribute("clientId", list.get(0).get("clientId"));
				model.addAttribute("List", "edit");
				return "syukkoUpIn";
			}

			//DB更新処理
			@RequestMapping(value="/book/syukkoUpSyori", method=RequestMethod.POST)
			public String SyukkoUp3(@Valid @ModelAttribute SyukkoModel syukkomodel, BindingResult result, Model model){
				if (result.hasErrors()) {
					model.addAttribute("message", "空欄があるか入力された数値が不適切です！");
				} else {
				String clientId = syukkomodel.getClientid();
				String clientName = syukkomodel.getClientname();
				String clientTel = syukkomodel.getClienttel();
				String clientPostCode = syukkomodel.getClientpostcode();
				String clientAdd = syukkomodel.getClientadd();
				String ClientNear = syukkomodel.getClientneareststation();
				String ClientRe = syukkomodel.getClientremarks();
				System.out.println(clientId);
				//DBを更新
				String sql = "update tblClient set clientName=?, clientTel=?, clientPostCode=?, clientAdd=?, "
						+ "clientNearestStation=?, clientRemarks=? where clientId=? ";
				jdbcTemplate.update(sql,new Object[]{clientName, clientTel, clientPostCode, clientAdd, ClientNear, ClientRe, clientId});
				}
				//出向先一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("data", list);
				return"syukkoInfo";
			}

			//出向先削除
			@RequestMapping(value="/book/syukkoDelete", method=RequestMethod.POST)
			public String SyukkoDe(@ModelAttribute SyukkoModel syukkomodel, Model model){
				String clientId = syukkomodel.getClientid();
				System.out.println(clientId);
				jdbcTemplate.update("delete from tblClient where clientId= " + clientId +";");
				//出向先一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("data", list);
				return"syukkoInfo";
			}

			//出向先検索
			@RequestMapping(value="/book/syukkoSearch", method=RequestMethod.POST)
			public String SyukkoSe(@ModelAttribute SyukkoModel syukkomodel, Model model){
				String clientSearch = syukkomodel.getCsearch();
				System.out.println(clientSearch);
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientName like '%"+clientSearch+"%';");
				model.addAttribute("data", list);
				return "syukkoInfo";
			}
	}
