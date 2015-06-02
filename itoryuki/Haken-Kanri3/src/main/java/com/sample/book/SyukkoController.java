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
			@RequestMapping(value="/book/Syukko", params="syukkoInsert", method=RequestMethod.GET)
			public String SyukkoIn(Model model){
				SyukkoModel sm3 = new SyukkoModel();
				model.addAttribute("syukkoModel", sm3);
				model.addAttribute("List", "null");
				return "SyukkoUpIn";
			}
			@RequestMapping(value="/book/Syukko", params="syukkoInsert2", method=RequestMethod.POST)
			public String SyukkoIn2(@ModelAttribute SyukkoModel sm3, Model model){
				String cn = sm3.getClientname();
				String ct = sm3.getClienttel();
				String cpc = sm3.getClientpostcode();
				String ca = sm3.getClientadd();
				String cns = sm3.getClientneareststation();
				String cr = sm3.getClientremarks();
				String sql = "insert into tblClient (clientName, clientTel, clientPostCode, clientAdd, clientNearestStation, clientRemarks) values (?, ?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql,new Object[]{cn, ct, cpc, ca, cns, cr});
				//出向先一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("data", list);
				return"syukkoInfo";
			}


			//出向先編集
			@RequestMapping(value="/book/Syukko", params="syukkoUpdate1", method=RequestMethod.GET)
			public String SyukkoUp(@ModelAttribute SyukkoModel sm3, Model model){
				String ci = sm3.getClientid();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId = " + ci +";");
				model.addAttribute("clientName", list.get(0).get("clientName"));
				model.addAttribute("clientTel", list.get(0).get("clientTel"));
				model.addAttribute("clientPostCode", list.get(0).get("clientPostCode"));
				model.addAttribute("clientAdd", list.get(0).get("clientAdd"));
				model.addAttribute("clientNearestStation", list.get(0).get("clientNearestStation"));
				model.addAttribute("clientRemarks", list.get(0).get("clientRemarks"));
				model.addAttribute("clientId", list.get(0).get("clientId"));
				model.addAttribute("List", "edit");
				return "SyukkoUpIn";
			}
			@RequestMapping(value="/book/Syukko", params="syukkoUpdate2", method=RequestMethod.POST)
			public String SyukkoUp2(@ModelAttribute SyukkoModel sm3, Model model){
				String ci = sm3.getClientid();
				String cn = sm3.getClientname();
				String ct = sm3.getClienttel();
				String cpc = sm3.getClientpostcode();
				String ca = sm3.getClientadd();
				String cns = sm3.getClientneareststation();
				String cr = sm3.getClientremarks();
				//DBを更新
				String sql = "update tblClient set clientName=?, clientTel=?, clientPostCode=?, clientAdd=?, "
						+ "clientNearestStation=?, clientRemarks=? where clientId=? ";
				jdbcTemplate.update(sql,new Object[]{cn, ct, cpc, ca, cns, cr, ci});
				//出向先一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("data", list);
				return"syukkoInfo";
			}


			//出向先削除
			@RequestMapping(value="/book/Syukko", params="syukkoDelete", method=RequestMethod.POST)
			public String SyukkoDe(@ModelAttribute SyukkoModel sm3, Model model){
				String ci = sm3.getClientid();
				jdbcTemplate.update("delete from tblClient where clientId= " + ci +";");
				//出向先一覧に戻る
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("data", list);
				return"syukkoInfo";
			}


			//出向先検索
			@RequestMapping(value="/book/Syukko", params="syukkoSearch", method=RequestMethod.POST)
			public String SyukkoSe(@ModelAttribute SyukkoModel sm3, Model model){
				String cs = sm3.getCsearch();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientName like '%"+cs+"%';");
				model.addAttribute("data", list);
				return "syukkoInfo";
			}


			@RequestMapping(value="/book/syukkoUpdate3", method=RequestMethod.GET)
			public String SyukkoUp3(@ModelAttribute SyukkoModel sm3, Model model, @RequestParam("value") String ci){
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tblClient where clientId = " + ci +";");
				model.addAttribute("clientName", list.get(0).get("clientName"));
				model.addAttribute("clientTel", list.get(0).get("clientTel"));
				model.addAttribute("clientPostCode", list.get(0).get("clientPostCode"));
				model.addAttribute("clientAdd", list.get(0).get("clientAdd"));
				model.addAttribute("clientNearestStation", list.get(0).get("clientNearestStation"));
				model.addAttribute("clientRemarks", list.get(0).get("clientRemarks"));
				model.addAttribute("clientId", list.get(0).get("clientId"));
				model.addAttribute("List", "edit");
				return "SyukkoUpIn";
			}
	}
