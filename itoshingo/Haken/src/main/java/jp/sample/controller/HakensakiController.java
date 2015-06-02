package jp.sample.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import jp.sample.form.HakensakiForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HakensakiController {

	@Autowired
	private JdbcTemplate jt;

	@RequestMapping(value="/haken/hakensakiInfo",method = RequestMethod.GET)
	public String hakensakiInfo(Model model){

		List<Map<String,Object>> data = jt.queryForList("select * from ("
				+ "select * from tblstaffmanagement) as t1 "
				+ "left join (select staffid,staffname from tblstaff) as t2 "
				+ "on t1.staffid = t2.staffid "
				+ "left join (select clientid,clientname from tblclient) as t3 "
				+ "on t1.clientid = t3.clientid "
				+ "order by startdate asc;");

		model.addAttribute("data",data);

		List<Map<String, Object>> list = jt.queryForList("select distinct year(startdate) as year from tblstaffmanagement "
														+ "order by year asc");

		model.addAttribute("year",list);

		return "hakensakiInfo";
	}

	@RequestMapping(value="/haken/hakensakiInsert",method = RequestMethod.GET)
	public String hakensakiInsertform(Model model){

		model.addAttribute("message","<br>");
		model.addAttribute("title","派遣先登録");

		model.addAttribute("form",new HakensakiForm());

		List<Map<String,Object>> list1 = jt.queryForList("select staffid,staffName from tblstaff");
		model.addAttribute("staffList",list1);
		List<Map<String,Object>> list2 = jt.queryForList("select clientid,clientName from tblClient");
		model.addAttribute("clientList",list2);

		model.addAttribute("amountMonth","");
		model.addAttribute("conditions","");
		model.addAttribute("deductionUnitprice","");
		model.addAttribute("overtimeRate","");
		model.addAttribute("startYear","");
		model.addAttribute("startMonth","");
		model.addAttribute("startDay","");
		model.addAttribute("endYear","");
		model.addAttribute("endMonth","");
		model.addAttribute("endDay","");
		model.addAttribute("site","");
		model.addAttribute("staffManRemarks","");

		model.addAttribute("submit","登録");


		return "hakensakiInput";
	}

	@RequestMapping(value="/haken/hakensakiInsert",method = RequestMethod.POST)
	public String hakensakiInsert(@Valid @ModelAttribute HakensakiForm form,BindingResult result,Model model,HttpServletRequest request){

		if(result.hasErrors()){
			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");

		}else{

			int staffid = form.getStaffid();
			int clientid = form.getClientid();
			int amountmonth = form.getAmountmonth();
			String conditions = form.getConditions();
			int deductionunitprice = form.getDeductionunitprice();
			int overtimerate = form.getOvertimerate();
			String site = form.getSite();

			String startdate = form.getStartyear() + form.getStartmonth() + form.getStartday();

			String enddate = form.getEndyear() + form.getEndmonth() + form.getEndday();

			String remarks = form.getRemarks();


			if(startdate.equals("")){
				startdate = null;
			}

			if(enddate.equals("")){
				enddate = null;
			}

			if((startdate != null)&&(enddate != null)){
				if(Integer.parseInt(startdate)<=Integer.parseInt(enddate)){
					String sql = "insert into tblStaffManagement(staffId,clientId,amountMonth,conditions,deductionUnitPrice,"
							+ "overtimeRate,site,startDate,endDate,staffManRemarks) values(?,?,?,?,?,?,?,?,?,?)";
					try{
						jt.update(sql,new Object[]{staffid,clientid,amountmonth,conditions,deductionunitprice,overtimerate,
									site,startdate,enddate,remarks});
						model.addAttribute("message","登録に成功しました。");
					}catch(Exception e){
						model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
					}
				}else{
					model.addAttribute("message","<font color=\"red\">終了日を開始日より後にしてください。</font>");
				}
			}else{
				String sql = "insert into tblStaffManagement(staffId,clientId,amountMonth,conditions,deductionUnitPrice,"
						+ "overtimeRate,site,startDate,endDate,staffManRemarks) values(?,?,?,?,?,?,?,?,?,?)";
				try{
					jt.update(sql,new Object[]{staffid,clientid,amountmonth,conditions,deductionunitprice,overtimerate,
								site,startdate,enddate,remarks});
					model.addAttribute("message","登録に成功しました。");
				}catch(Exception e){
					model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");
				}
			}
		}
		model.addAttribute("title","派遣先登録");

		model.addAttribute("form",new HakensakiForm());

		List<Map<String,Object>> list1 = jt.queryForList("select staffid,staffName from tblstaff");
		model.addAttribute("staffList",list1);
		List<Map<String,Object>> list2 = jt.queryForList("select clientid,clientName from tblClient");
		model.addAttribute("clientList",list2);

		model.addAttribute("amountMonth","");
		model.addAttribute("conditions","");
		model.addAttribute("deductionUnitprice","");
		model.addAttribute("overtimeRate","");
		model.addAttribute("startYear","");
		model.addAttribute("startMonth","");
		model.addAttribute("startDay","");
		model.addAttribute("endYear","");
		model.addAttribute("endMonth","");
		model.addAttribute("endDay","");
		model.addAttribute("site","");
		model.addAttribute("staffManRemarks","");

		model.addAttribute("submit","登録");


		return "hakensakiInput";
	}

	@RequestMapping(value="/haken/hakensakiUpdate",method = RequestMethod.GET)
	public String hakensakiUpdateform(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		List<Map<String,Object>> data = jt.queryForList("select *,lpad(year(startdate),4,'0') as startyear,"
				+ "lpad(month(startdate),2,'0') as startmonth,lpad(day(startdate),2,'0') as startday,"
				+ "lpad(year(enddate),4,'0') as endyear,lpad(month(enddate),2,'0') as endmonth,"
				+ "lpad(day(enddate),2,'0') as endday from tblStaffManagement where staffManId = "+ id +";");

		model.addAttribute("message","<br>");
		model.addAttribute("title","派遣先編集");

		model.addAttribute("form",new HakensakiForm());

		model.addAttribute("staffid",data.get(0).get("staffId"));

		List<Map<String,Object>> list1 = jt.queryForList("select staffid,staffName from tblstaff");
		model.addAttribute("staffList",list1);

		model.addAttribute("clientid",data.get(0).get("clientId"));

		List<Map<String,Object>> list2 = jt.queryForList("select clientid,clientName from tblClient");
		model.addAttribute("clientList",list2);

		model.addAttribute("amountMonth",data.get(0).get("amountMonth"));
		model.addAttribute("conditions",data.get(0).get("conditions"));
		model.addAttribute("deductionUnitprice",data.get(0).get("deductionUnitPrice"));
		model.addAttribute("overtimeRate",data.get(0).get("overtimeRate"));
		model.addAttribute("startYear",data.get(0).get("startyear"));
		model.addAttribute("startMonth",data.get(0).get("startmonth"));
		model.addAttribute("startDay",data.get(0).get("startday"));
		model.addAttribute("endYear",data.get(0).get("endyear"));
		model.addAttribute("endMonth",data.get(0).get("endmonth"));
		model.addAttribute("endDay",data.get(0).get("endday"));
		model.addAttribute("site",data.get(0).get("site"));
		model.addAttribute("staffManRemarks",data.get(0).get("staffManRemarks"));

		model.addAttribute("submit","確定");

		return "hakensakiInput";
	}

	@RequestMapping(value="/haken/hakensakiUpdate",method = RequestMethod.POST)
	public String hakensakiUpdate(@Valid @ModelAttribute HakensakiForm form,BindingResult result,Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		if(result.hasErrors()){
			model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");

		}else{

			int staffid = form.getStaffid();
			int clientid = form.getClientid();
			int amountmonth = form.getAmountmonth();
			String conditions = form.getConditions();
			int deductionunitprice = form.getDeductionunitprice();

			int overtimerate = form.getOvertimerate();
			String site = form.getSite();
			String startdate = form.getStartyear() + form.getStartmonth() + form.getStartday();

			String enddate = form.getEndyear() + form.getEndmonth() + form.getEndday();

			if(startdate.equals("")){
				startdate = null;
			}

			if(enddate.equals("")){
				enddate = null;
			}

			String remarks = form.getRemarks();

			if((startdate != null)&&(enddate != null)){
				if(Integer.parseInt(startdate)<=Integer.parseInt(enddate)){

					String sql = "update tblStaffManagement set staffid = ?,clientid = ?,amountMonth = ?,conditions = ?,"
							+ "deductionUnitPrice = ?,overtimeRate = ?,site = ?,startDate = ?,endDate = ?,staffManRemarks = ?"
							+ "where staffManId = ?";

					try{
						jt.update(sql,new Object[]{staffid,clientid,amountmonth,conditions,deductionunitprice,overtimerate,site,startdate,enddate,remarks,id});

						model.addAttribute("message","更新に成功しました。");

					}catch(Exception e){
						model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");

					}
				}else{
					model.addAttribute("message","<font color=\"red\">終了日を開始日より後にしてください。</font>");
				}
			}else{
				String sql = "update tblStaffManagement set staffid = ?,clientid = ?,amountMonth = ?,conditions = ?,"
						+ "deductionUnitPrice = ?,overtimeRate = ?,site = ?,startDate = ?,endDate = ?,staffManRemarks = ?"
						+ "where staffManId = ?";

				try{
					jt.update(sql,new Object[]{staffid,clientid,amountmonth,conditions,deductionunitprice,overtimerate,site,startdate,enddate,remarks,id});

					model.addAttribute("message","更新に成功しました。");

				}catch(Exception e){
					model.addAttribute("message","<font color=\"red\">入力値にエラーがあります。</font>");

				}

			}
		}
		List<Map<String,Object>> data = jt.queryForList("select *,lpad(year(startdate),4,'0') as startyear,lpad(month(startdate),2,'0') as startmonth,"
				+ "lpad(day(startdate),2,'0') as startday,lpad(year(enddate),4,'0') as endyear,lpad(month(enddate),2,'0') as endmonth,lpad(day(enddate),2,'0') as endday "
				+ "from tblStaffManagement where staffManId = "+ id +";");

		model.addAttribute("title","派遣先編集");

		model.addAttribute("form",new HakensakiForm());

		List<Map<String,Object>> list1 = jt.queryForList("select staffid,staffName from tblstaff");
		model.addAttribute("staffList",list1);

		model.addAttribute("staffid",data.get(0).get("staffId"));

		model.addAttribute("clientid",data.get(0).get("clientId"));

		List<Map<String,Object>> list2 = jt.queryForList("select clientid,clientName from tblClient");
		model.addAttribute("clientList",list2);

		model.addAttribute("amountMonth",data.get(0).get("amountMonth"));
		model.addAttribute("conditions",data.get(0).get("conditions"));
		model.addAttribute("deductionUnitprice",data.get(0).get("deductionUnitPrice"));
		model.addAttribute("overtimeRate",data.get(0).get("overtimeRate"));
		model.addAttribute("startYear",data.get(0).get("startyear"));
		model.addAttribute("startMonth",data.get(0).get("startmonth"));
		model.addAttribute("startDay",data.get(0).get("startday"));
		model.addAttribute("endYear",data.get(0).get("endyear"));
		model.addAttribute("endMonth",data.get(0).get("endmonth"));
		model.addAttribute("endDay",data.get(0).get("endday"));
		model.addAttribute("site",data.get(0).get("site"));
		model.addAttribute("staffManRemarks",data.get(0).get("staffManRemarks"));

		model.addAttribute("submit","確定");

		return "hakensakiInput";
	}

	@RequestMapping(value="/haken/hakensakiDelete",method = RequestMethod.POST)
	public String hakensakiDelete(Model model,HttpServletRequest request){

		int id = Integer.parseInt(request.getParameter("id"));

		String sql = "delete from tblStaffManagement where staffManId = ? ;";

		jt.update(sql,new Object[]{id});


		return "redirect:/haken/hakensakiInfo";
	}

	@RequestMapping(value="/haken/hakensakiSelect",method = RequestMethod.POST)
	public String hakensakiSelect(Model model,HttpServletRequest request){

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String input = request.getParameter("input");

		String start = year + month +"31";
		String end = year + month + "01";


		List<Map<String,Object>> data = jt.queryForList("select * from ("
							+ "select * from tblstaffmanagement where startdate <="+ start +" AND enddate >= "+ end +") as t1 "
							+ "left join (select staffid,staffname from tblstaff where staffname like \'%"+ input +"%\') as t2 "
							+ "on t1.staffid = t2.staffid "
							+ "left join (select clientid,clientname from tblclient) as t3 "
							+ "on t1.clientid = t3.clientid;");

		model.addAttribute("data",data);

		List<Map<String, Object>> list = jt.queryForList("select distinct year(startdate) as year from tblstaffmanagement "
														+ "order by year asc");

		model.addAttribute("year",list);

		return "hakensakiInfo";
	}
}
