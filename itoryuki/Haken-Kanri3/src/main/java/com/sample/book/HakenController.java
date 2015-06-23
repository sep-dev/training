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

import FormModel.HakenModel;

//派遣先情報のコントローラー
	@Controller
	public class HakenController {
		@Autowired
		private JdbcTemplate jdbcTemplate;
			//派遣先一覧を表示
			@RequestMapping(value="/book/hakenInfo", method = RequestMethod.GET)
			public String hakensaki(Locale locale, Model model) {
				//セレクトボックスの項目(年)をDBから取得
				List<Map<String, Object>> ylist = jdbcTemplate.queryForList("select year(startDate) as syear from tblStaffManagement  group by syear");
				model.addAttribute("syear", ylist.get(0).get("syear"));
				model.addAttribute("ydata", ylist);
				//３つのテーブル（tblClient,tblStaff,tblStaffManagement）から情報を取得、結合
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaffManagement as tb1 "
						+ "left join tblStaff as tb2 on tb1.staffId = tb2.staffId "
						+ "left join tblClient as tb3 on tb1.clientId = tb3.clientId");
				model.addAttribute("data", list);
				return"hakenInfo";
			}

			//派遣先新規登録画面へ
			@RequestMapping(value="/book/hakenInGamen", method=RequestMethod.GET)
			public String HakenIn1(Model model){
				HakenModel hm = new HakenModel();
				model.addAttribute("hakenModel", hm);
				//セレクトボックスの項目(社員名、企業名)をDBから取得
				List<Map<String, Object>> slist = jdbcTemplate.queryForList("select staffId, staffName from tblStaff ");
				List<Map<String, Object>> clist = jdbcTemplate.queryForList("select clientId, clientName from tblClient ");
				model.addAttribute("Sdata", slist);
				model.addAttribute("Cdata", clist);
				model.addAttribute("List", "null");
				return "hakenUpIn";
			}
			//登録処理
			@RequestMapping(value="/book/hakenInSyori", method=RequestMethod.POST)
			public String HakenIn2(@Valid @ModelAttribute HakenModel hm, BindingResult result, Model model){
				if (result.hasErrors()) {
					model.addAttribute("message", "空欄があるか入力された数値が不適切です！");
				} else {
					String staffId = hm.getStaffid();
					String clientId = hm.getClientid();
					String amount= hm.getAmountmonth();
					String conditions = hm.getConditions();
					String deduction = hm.getDeductionunitprice();
					String overtime = hm.getOvertimerate();
					String site = hm.getSite();
					String startdate1 = hm.getStartdate1(); //年
					String startdate2 = hm.getStartdate2(); //月
					String startdate3 = hm.getStartdate3(); //日
					String enddate1 = hm.getEnddate1(); //年
					String enddate2 = hm.getEnddate2(); //月
					String enddate3 = hm.getEnddate3(); //日
					String staManRe = hm.getStaffmanremarks();
					String start = startdate1+startdate2+startdate3;
					String end = enddate1+enddate2+enddate3;
				//DBに追加
					String sql  ="insert into tblStaffManagement (staffId, clientId, amountMonth, conditions, deductionUnitPrice, "
							+ "overtimeRate, site, startDate, endDate, staffManRemarks) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					jdbcTemplate.update(sql,new Object[]{staffId, clientId, amount, conditions, deduction, overtime, site, start, end, staManRe});
				}
				//一覧に戻る
				List<Map<String, Object>> ylist = jdbcTemplate.queryForList("select year(startDate) as syear from tblStaffManagement  group by syear");
				model.addAttribute("syear", ylist.get(0).get("syear"));
				model.addAttribute("ydata", ylist);
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaffManagement as tb1 "
						+ "left join tblStaff as tb2 on tb1.staffId = tb2.staffId "
						+ "left join tblClient as tb3 on tb1.clientId = tb3.clientId");
				model.addAttribute("data", list);
				return "hakenInfo";
			}

			//派遣先編集画面へ
			@RequestMapping(value="/book/hakenUpGamen", method=RequestMethod.GET)
			public String HakenUp1(@ModelAttribute HakenModel hm, Model model){
				String smi = hm.getStaffmanid();
				//セレクトボックスの中身を取得
				List<Map<String, Object>> slist = jdbcTemplate.queryForList("select staffId, staffName from tblStaff ");
				List<Map<String, Object>> clist = jdbcTemplate.queryForList("select clientId, clientName from tblClient ");
				model.addAttribute("Sdata", slist);
				model.addAttribute("Cdata", clist);
				//編集したいデータを表示
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select *, year(startDate) as syear, month(startDate) as smonth,"
						+ "day(startDate) as sday, year(endDate) as eyear, month(endDate) as emonth, day(endDate) as eday from tblStaffManagement where staffManId = " + smi +";");
				model.addAttribute("staffManId", list.get(0).get("staffManId"));
				model.addAttribute("amountMonth", list.get(0).get("amountMonth"));
				model.addAttribute("conditions", list.get(0).get("conditions"));
				model.addAttribute("deductionUnitPrice", list.get(0).get("deductionUnitPrice"));
				model.addAttribute("overtimeRate", list.get(0).get("overtimeRate"));
				model.addAttribute("site", list.get(0).get("site"));
				model.addAttribute("syear", list.get(0).get("syear"));
				model.addAttribute("smonth", list.get(0).get("smonth"));
				model.addAttribute("sday", list.get(0).get("sday"));
				model.addAttribute("eyear", list.get(0).get("eyear"));
				model.addAttribute("emonth", list.get(0).get("emonth"));
				model.addAttribute("eday", list.get(0).get("eday"));
				model.addAttribute("List", "edit");//編集に飛ばす
				return "hakenUpIn";
			}
			//DB更新処理
			@RequestMapping(value="/book/hakenUpSyori", method=RequestMethod.POST)
			public String HakenUp2(@Valid @ModelAttribute HakenModel hm, BindingResult result, Model model){
				if (result.hasErrors()) {
					model.addAttribute("message", "空欄があるか入力された数値が不適切です！");
				} else {
					String staffManId = hm.getStaffmanid();
					String staffId = hm.getStaffid();
					String clientId = hm.getClientid();
					String amount= hm.getAmountmonth();
					String conditoins = hm.getConditions();
					String deduction = hm.getDeductionunitprice();
					String overtime = hm.getOvertimerate();
					String site = hm.getSite();
					String startdate1 = hm.getStartdate1(); //年
					String startdate2 = hm.getStartdate2(); //月
					String startdate3 = hm.getStartdate3(); //日
					String enddate1 = hm.getEnddate1(); //年
					String enddate2 = hm.getEnddate2(); //月
					String enddate3 = hm.getEnddate3(); //日
					String staffManRe = hm.getStaffmanremarks();
					String start = startdate1+startdate2+startdate3;
					String end = enddate1+enddate2+enddate3;
				//DBを更新
					String sql = "update tblStaffManagement set staffId=?, clientId=?, amountMonth=?, conditions=?, deductionUnitPrice=?,"
							+ "overtimeRate=?, site=?, startDate=?, endDate=?, staffManRemarks=? where staffManId=? ";
					jdbcTemplate.update(sql,new Object[]{staffId, clientId, amount, conditoins, deduction, overtime, site, start, end, staffManRe, staffManId});
				}
				//一覧に戻る
				List<Map<String, Object>> ylist = jdbcTemplate.queryForList("select year(startDate) as syear from tblStaffManagement  group by syear");
				model.addAttribute("syear", ylist.get(0).get("syear"));
				model.addAttribute("ydata", ylist);
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaffManagement as tb1 "
						+ "left join tblStaff as tb2 on tb1.staffId = tb2.staffId "
						+ "left join tblClient as tb3 on tb1.clientId = tb3.clientId");
				model.addAttribute("data", list);
				return "hakenInfo";
			}

			//派遣先情報削除
			@RequestMapping(value="/book/hakenDelete", method=RequestMethod.POST)
			public String hakenDe(@ModelAttribute HakenModel hm, Model model){
				String staffManId = hm.getStaffmanid();
				System.out.println(staffManId);
				//削除処理
				jdbcTemplate.update("delete from tblStaffManagement where staffManId= " + staffManId +";");
				//一覧に戻る
				List<Map<String, Object>> ylist = jdbcTemplate.queryForList("select year(startDate) as syear from tblStaffManagement  group by syear");
				model.addAttribute("syear", ylist.get(0).get("syear"));
				model.addAttribute("ydata", ylist);
				List<Map<String, Object>> list
				= jdbcTemplate.queryForList("select * from tblStaffManagement as tb1 "
						+ "left join tblStaff as tb2 on tb1.staffId = tb2.staffId "
						+ "left join tblClient as tb3 on tb1.clientId = tb3.clientId");
				model.addAttribute("data", list);
				return "hakenInfo";
			}

			//派遣社員検索
			@RequestMapping(value="/book/hakenSearch", method=RequestMethod.POST)
			public String HakenSe(@ModelAttribute HakenModel hm, Model model){
				String hakenSearch = hm.getHsearch();//検索したいワード
				String startDate1 = hm.getStartdate1();//選択した年
				String startDate2 = hm.getStartdate2();//選択した月
				String start = startDate1+"-"+startDate2+"-"+"31";
				String end = startDate1+"-"+startDate2+"-"+"01";
				System.out.println(hakenSearch);
				List<Map<String, Object>> list= jdbcTemplate.queryForList(
						"select * from tblStaffManagement as tb1 "
						+ "left join tblStaff as tb2 on tb1.staffId = tb2.staffId "
						+ "left join tblClient as tb3 on tb1.clientId = tb3.clientId "
						+ "where tb1.endDate >='"+end+"' "
						+ "and tb1.startDate <= '"+start+"' "
						+ "and tb2.staffName like '%"+hakenSearch+"%';");
				System.out.println(list);
				System.out.println(startDate1);
				System.out.println(startDate2);
				model.addAttribute("data", list);
				//一覧に戻る
				List<Map<String, Object>> ylist = jdbcTemplate.queryForList("select year(startDate) as syear from tblStaffManagement  group by syear");
				model.addAttribute("syear", ylist.get(0).get("syear"));
				model.addAttribute("ydata", ylist);
				return "hakenInfo";
			}
	}
