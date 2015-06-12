package jp.co.hakenSystem;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HakenSystemController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ModelAttribute
	public DataSet setdataset() {
		// 現在の日付をセットする
		DataSet dataSet = new DataSet();
		Date date = new Date();
		String year = String.format("%tY", date);
		String month = String.format("%tm", date);
		dataSet.setYear(year);
		dataSet.setMonth(month);

		return dataSet;

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		DataSet formModel = new DataSet();
		model.addAttribute("formModel", formModel);
		return "showMessage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute FormModel formModel, Model model) {

		int check = jdbcTemplate
				.queryForInt(
						"SELECT Count(loginUserPass) FROM tblLoginUser where loginUser =?",
						formModel.getUserId());
		if (check == 0) {
			boolean loginfailureflag = true;
			model.addAttribute("loginfailureflag", loginfailureflag);
			return "showMessage";
		}

		List<Map<String, Object>> loginlist = jdbcTemplate.queryForList(
				"SELECT loginUserPass FROM tblLoginUser where loginUser =?",
				formModel.getUserId());

		if ((loginlist.get(0).get("loginUserPass")) != (null)
				&& (loginlist.get(0).get("loginUserPass").equals(formModel
						.getPass()))) {
			return "iframe";
		} else {
			boolean loginfailureflag = true;
			model.addAttribute("loginfailureflag", loginfailureflag);
			return "showMessage";
		}

	}

	@RequestMapping(value = "/HakensakiItiran", method = RequestMethod.GET)
	public String hakensaki(@ModelAttribute DataSet dataSet, Model model) {
		HakensakiItiranFormModel formModel = new HakensakiItiranFormModel();
		model.addAttribute("formModel", formModel);

		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * from tblStaffManagement left join tblclient using(clientId) left join tblStaff using(staffId) WHERE startDate<=(SELECT CURDATE()+0) && endDate>=(SELECT CURDATE()+0)");

		model.addAttribute("list", list);
		return "HakensakiItiran";
	}

	@RequestMapping(value = "/HakensakiHensyu", method = RequestMethod.POST)
	public String hakensakiupdate(
			@ModelAttribute HakensakiItiranFormModel formModel, Model model) {

		List<Map<String, Object>> stafflist = jdbcTemplate
				.queryForList("SELECT staffName,staffManId,staffId from tblStaff left join tblStaffManagement using(staffId)");
		List<Map<String, Object>> clientlist = jdbcTemplate
				.queryForList("SELECT clientName,staffManId,clientId from tblclient left join tblStaffManagement using(clientId)");
		List<Map<String, Object>> updatelist = jdbcTemplate
				.queryForList(
						"SELECT clientId,staffId,tblStaffManagement.amountMonth,tblStaffManagement.conditions,tblStaffManagement.deductionUnitPrice,tblStaffManagement.overtimeRate,tblStaffManagement.site,SUBSTR(startDate,1,4) AS startyear,SUBSTR(startDate,6,2) AS startmonth,SUBSTR(startDate,9,2) AS startday,SUBSTR(endDate,1,4) AS endyear,SUBSTR(endDate,6,2) AS endmonth,SUBSTR(endDate,9,2) AS endday,tblStaffManagement.staffManRemarks from tblStaffManagement left join tblclient using(clientId) left join tblStaff using(staffId) where staffManId =?",
						formModel.getUpdatestaffManId());
		model.addAttribute("stafflist", stafflist);
		model.addAttribute("clientlist", clientlist);
		model.addAttribute("updatelist", updatelist);
		model.addAttribute("staffManId", formModel.getUpdatestaffManId());
		model.addAttribute("formModel", formModel);

		return "HakensakiUpdate";
	}

	@RequestMapping(value = "/HakensakiUpdate", method = RequestMethod.POST)
	public String update(@ModelAttribute HakensakiItiranFormModel formModel,
			Model model) {
		// 個別で入力された年・月・日を結合させる
		StringBuilder startdate = new StringBuilder();
		startdate.append(formModel.getStartyear());
		startdate.append("-");
		startdate.append(formModel.getStartmonth());
		startdate.append("-");
		startdate.append(formModel.getStartday());
		String startDate = startdate.toString();

		StringBuilder enddate = new StringBuilder();
		enddate.append(formModel.getEndyear());
		enddate.append("-");
		enddate.append(formModel.getEndmonth());
		enddate.append("-");
		enddate.append(formModel.getEndday());
		String endDate = enddate.toString();

		jdbcTemplate
				.update("UPDATE tblStaffManagement SET staffId=?,clientId=?, amountMonth=?,conditions=?,deductionUnitPrice=?,overtimeRate=?,site=?,startDate=?,endDate=?,staffManRemarks=? WHERE staffManId=?",
						formModel.getSyainId(), formModel.getHakensakiId(),
						formModel.getTankin(), formModel.getJouken(),
						formModel.getKoujo(), formModel.getTyouka(),
						formModel.getSite(), startDate, endDate,
						formModel.getBikou(), formModel.getUpdatestaffManId());

		return "redirect:HakensakiItiran";
	}

	@RequestMapping(value = "/HakensakiInsert", method = RequestMethod.POST)
	public String insert(@ModelAttribute HakensakiItiranFormModel formModel,
			Model model) {
		// 個別で入力された年・月・日を結合させる
		StringBuilder startdate = new StringBuilder();
		startdate.append(formModel.getStartyear());
		startdate.append("-");
		startdate.append(formModel.getStartmonth());
		startdate.append("-");
		startdate.append(formModel.getStartday());
		String startDate = startdate.toString();

		StringBuilder enddate = new StringBuilder();
		enddate.append(formModel.getEndyear());
		enddate.append("-");
		enddate.append(formModel.getEndmonth());
		enddate.append("-");
		enddate.append(formModel.getEndday());
		String endDate = enddate.toString();

		jdbcTemplate
				.update("INSERT INTO tblStaffManagement (clientId,staffId,amountMonth,conditions,deductionUnitPrice,overtimeRate,site,startDate,endDate,staffManRemarks)VALUES(?,?,?,?,?,?,?,?,?,?)",
						formModel.getClientId(), formModel.getStaffId(),
						formModel.getTankin(), formModel.getJouken(),
						formModel.getKoujo(), formModel.getTyouka(),
						formModel.getSite(), startDate, endDate,
						formModel.getBikou());

		return "redirect:HakensakiItiran";
	}

	@RequestMapping(value = "/HakensakiTouroku", method = RequestMethod.POST)
	public String hakensakitouroku(Model model) {
		List<Map<String, Object>> stafflist = jdbcTemplate
				.queryForList("SELECT staffName,staffManId,staffId from tblStaff left join tblStaffManagement using(staffId)");
		List<Map<String, Object>> clientlist = jdbcTemplate
				.queryForList("SELECT clientName,staffManId,clientId from tblclient left join tblStaffManagement using(clientId)");
		HakensakiItiranFormModel formModel = new HakensakiItiranFormModel();
		model.addAttribute("formModel", formModel);
		model.addAttribute("stafflist", stafflist);
		model.addAttribute("clientlist", clientlist);
		return "HakensakiTouroku";
	}

	@RequestMapping(value = "/HakensakiKensaku", method = RequestMethod.POST)
	public String kensaku(@ModelAttribute HakensakiItiranFormModel formModel,
			@ModelAttribute DataSet dataSet, Model model) {

		model.addAttribute("formModel", formModel);

		dataSet.setYear(formModel.getYear());
		dataSet.setMonth(formModel.getMonth());
		// 選択された年と月を結合させる
		StringBuilder buf = new StringBuilder();
		buf.append(formModel.getYear());
		buf.append("-");
		buf.append(formModel.getMonth());
		String hyoujikikan = buf.toString();
		dataSet.setKensakuword(formModel.getKensakuword());
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList(
						"SELECT * from tblStaffManagement left join tblclient using(clientId) left join tblStaff using(staffId) WHERE SUBSTR(startDate,1,7)<=? and SUBSTR(endDate,1,7)>=? and staffName LIKE ? ",
						hyoujikikan, hyoujikikan,
						(('%') + formModel.getKensakuword()) + ('%'));
		model.addAttribute("list", list);
		model.addAttribute("updatelist", list);

		return "HakensakiItiran";
	}

	@RequestMapping(value = "/HakensyainItiran", method = RequestMethod.GET)
	public String hakensyainitiran(Model model) {
		UpdateFormModel formModel = new UpdateFormModel();
		model.addAttribute("formModel", formModel);

		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM tblStaff S,tblAffiliation A WHERE S.AffiliationId = A.AffiliationId ORDER BY affiliationName ASC");
		model.addAttribute("list", list);
		return "HakensyainItiran";
	}

	@RequestMapping(value = "/HakensyainTouroku", method = RequestMethod.POST)
	public String hakensyaintouroku(Model model) {
		UpdateFormModel formModel = new UpdateFormModel();
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM tblaffiliation");
		model.addAttribute("list", list);
		model.addAttribute("formModel", formModel);
		return "HakensyainTouroku";
	}

	@RequestMapping(value = "/HakensyainInsert", method = RequestMethod.POST)
	public String hakensyaininsert(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		jdbcTemplate
				.update("INSERT INTO tblstaff (staffName,staffEMail,staffTel,staffMobiletel,staffPostCode,staffAdd,staffNearestStation,AffiliationId,staffRemarks)VALUES(?,?,?,?,?,?,?,?,?)",
						formModel.getStaffname(), formModel.getStaffemail(),
						formModel.getStafftel(), formModel.getStaffmobiletel(),
						formModel.getStaffpostcode(), formModel.getStaffadd(),
						formModel.getStaffneareststation(),
						formModel.getAffiliationId(),
						formModel.getStaffremarks());
		model.addAttribute("formModel", formModel);
		return "redirect:HakensyainItiran";
	}

	@RequestMapping(value = "/HakensyainKensaku", method = RequestMethod.POST)
	public String hakensyainkensaku(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList(
						"SELECT * FROM tblStaff S,tblAffiliation A WHERE S.AffiliationId = A.AffiliationId AND StaffName LIKE ?",
						(('%') + formModel.getKensakuword()) + ('%'));
		model.addAttribute("list", list);
		model.addAttribute("formModel", formModel);
		return "HakensyainItiran";
	}

	@RequestMapping(value = "/HakensyainHensyu", method = RequestMethod.POST)
	public String hakensyainhensyu(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		List<Map<String, Object>> updatelist = jdbcTemplate.queryForList(
				"SELECT * FROM tblstaff WHERE staffId = ?",
				formModel.getUpdatestaffId());
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM tblaffiliation");

		model.addAttribute("updatestaffId", formModel.getUpdatestaffId());
		model.addAttribute("list", list);
		model.addAttribute("updatelist", updatelist);
		model.addAttribute("formModel", formModel);
		return "HakensyainUpdate";
	}

	@RequestMapping(value = "/HakensyainUpdate", method = RequestMethod.POST)
	public String hakensyainupdate(@ModelAttribute UpdateFormModel formModel,
			Model model) {

		jdbcTemplate
				.update("UPDATE tblStaff SET staffName=?,staffEmail=?,staffTel=?,staffMobiletel=?,staffPostCode=?,staffAdd=?,staffNearestStation=?,affiliationId=?,staffRemarks=? WHERE staffId=?",
						formModel.getStaffname(), formModel.getStaffemail(),
						formModel.getStafftel(), formModel.getStaffmobiletel(),
						formModel.getStaffpostcode(), formModel.getStaffadd(),
						formModel.getStaffneareststation(),
						formModel.getAffiliationId(),
						formModel.getStaffremarks(),
						formModel.getUpdatestaffId());
		model.addAttribute("formModel", formModel);

		return "redirect:HakensyainItiran";
	}

	@RequestMapping(value = "/SyozokumotoItiran", method = RequestMethod.GET)
	public String syozokumotoitiran(Model model) {
		UpdateFormModel formModel = new UpdateFormModel();
		model.addAttribute("formModel", formModel);

		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM tblaffiliation");
		model.addAttribute("list", list);
		return "SyozokumotoItiran";
	}

	@RequestMapping(value = "/SyozokumotoTouroku", method = RequestMethod.POST)
	public String syozokumototouroku(Model model) {
		UpdateFormModel formModel = new UpdateFormModel();
		model.addAttribute("formModel", formModel);
		return "SyozokumotoTouroku";
	}

	@RequestMapping(value = "/SyozokumotoInsert", method = RequestMethod.POST)
	public String syozokumotoinsert(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		jdbcTemplate
				.update("INSERT INTO tblAffiliation (affiliationName,affiliationTel,affiliationPostCode,affiliationAdd,affiliationNearestStation,affiliationRemarks)VALUES(?,?,?,?,?,?)",
						formModel.getAffiliationname(),
						formModel.getAffiliationtel(),
						formModel.getAffiliationpostcode(),
						formModel.getAffiliationadd(),
						formModel.getAffiliationneareststation(),
						formModel.getAffiliationremarks());
		model.addAttribute("formModel", formModel);
		return "redirect:SyozokumotoItiran";
	}

	@RequestMapping(value = "/SyozokumotoKensaku", method = RequestMethod.POST)
	public String syozokumotokensaku(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT * from tblAffiliation WHERE affiliationName LIKE ?",
				(('%') + formModel.getKensakuword()) + ('%'));
		model.addAttribute("list", list);
		model.addAttribute("formModel", formModel);
		return "SyozokumotoItiran";
	}

	@RequestMapping(value = "/SyozokumotoHensyu", method = RequestMethod.POST)
	public String syozokumotohensyu(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		List<Map<String, Object>> updatelist = jdbcTemplate.queryForList(
				"SELECT * FROM tblAffiliation WHERE affiliationId = ?",
				formModel.getUpdateaffiliationId());

		model.addAttribute("updateaffiliationId",
				formModel.getUpdateaffiliationId());
		model.addAttribute("updatelist", updatelist);
		model.addAttribute("formModel", formModel);
		return "SyozokumotoUpdate";
	}

	@RequestMapping(value = "/SyozokumotoUpdate", method = RequestMethod.POST)
	public String syozokumotoupdate(@ModelAttribute UpdateFormModel formModel,
			Model model) {

		jdbcTemplate
				.update("UPDATE tblAffiliation SET affiliationName=?,affiliationTel=?,affiliationPostCode=?,affiliationAdd=?,affiliationNearestStation=?,affiliationRemarks=? WHERE affiliationId=?",
						formModel.getAffiliationname(),
						formModel.getAffiliationtel(),
						formModel.getAffiliationpostcode(),
						formModel.getAffiliationadd(),
						formModel.getAffiliationneareststation(),
						formModel.getAffiliationremarks(),
						formModel.getUpdateaffiliationId());
		model.addAttribute("formModel", formModel);

		return "redirect:SyozokumotoItiran";
	}

	@RequestMapping(value = "/SyukkousakiItiran", method = RequestMethod.GET)
	public String syukkousakiitiran(Model model) {
		UpdateFormModel formModel = new UpdateFormModel();
		model.addAttribute("formModel", formModel);

		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM tblclient");
		model.addAttribute("list", list);
		return "SyukkousakiItiran";
	}

	@RequestMapping(value = "/SyukkousakiTouroku", method = RequestMethod.POST)
	public String syukkousakitouroku(Model model) {
		UpdateFormModel formModel = new UpdateFormModel();
		model.addAttribute("formModel", formModel);
		return "SyukkousakiTouroku";
	}

	@RequestMapping(value = "/SyukkousakiInsert", method = RequestMethod.POST)
	public String syukkousakiinsert(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		jdbcTemplate
				.update("INSERT INTO tblClient (clientName,clientTel,clientPostCode,clientAdd,clientNearestStation,clientRemarks)VALUES(?,?,?,?,?,?)",
						formModel.getClientname(), formModel.getClienttel(),
						formModel.getClientpostcode(),
						formModel.getClientadd(),
						formModel.getClientneareststation(),
						formModel.getClientremarks());
		model.addAttribute("formModel", formModel);
		return "redirect:SyukkousakiItiran";
	}

	@RequestMapping(value = "/SyukkousakiKensaku", method = RequestMethod.POST)
	public String syukkousakikensaku(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT * from tblClient WHERE clientName LIKE ?",
				(('%') + formModel.getKensakuword()) + ('%'));
		model.addAttribute("list", list);
		model.addAttribute("formModel", formModel);
		return "SyukkousakiItiran";
	}

	@RequestMapping(value = "/SyukkousakiHensyu", method = RequestMethod.POST)
	public String syukkousakihensyu(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		List<Map<String, Object>> updatelist = jdbcTemplate.queryForList(
				"SELECT * FROM tblClient WHERE clientId = ?",
				formModel.getUpdateclientId());

		model.addAttribute("updateclientId", formModel.getUpdateclientId());
		model.addAttribute("updatelist", updatelist);
		model.addAttribute("formModel", formModel);
		return "SyukkousakiUpdate";
	}

	@RequestMapping(value = "/SyukkousakiUpdate", method = RequestMethod.POST)
	public String syukkousakiupdate(@ModelAttribute UpdateFormModel formModel,
			Model model) {

		jdbcTemplate
				.update("UPDATE tblClient SET clientName=?,clientTel=?,clientPostCode=?,clientAdd=?,clientNearestStation=?,clientRemarks=? WHERE clientId=?",
						formModel.getClientname(), formModel.getClienttel(),
						formModel.getClientpostcode(),
						formModel.getClientadd(),
						formModel.getClientneareststation(),
						formModel.getClientremarks(),
						formModel.getUpdateclientId());
		model.addAttribute("formModel", formModel);

		return "redirect:SyukkousakiItiran";
	}

	@RequestMapping(value = "/UserItiran", method = RequestMethod.GET)
	public String useritiran(Model model) {
		UpdateFormModel formModel = new UpdateFormModel();
		model.addAttribute("formModel", formModel);

		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM tblLoginUser");
		model.addAttribute("list", list);
		return "UserItiran";
	}

	@RequestMapping(value = "/UserTouroku", method = RequestMethod.POST)
	public String usertouroku(Model model) {
		UpdateFormModel formModel = new UpdateFormModel();
		model.addAttribute("formModel", formModel);
		return "UserTouroku";
	}

	@RequestMapping(value = "/UserInsert", method = RequestMethod.POST)
	public String userinsert(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		if (formModel.getPassword().equals(formModel.getPasswordcheck())) {
			jdbcTemplate
					.update("INSERT INTO tblLoginUser (loginUserName,loginUser,loginUserPass)VALUES(?,?,?)",
							formModel.getUsername(), formModel.getUser(),
							formModel.getPassword());
		} else {
			boolean tourokufailureflag = true;
			model.addAttribute("loginfailureflag", tourokufailureflag);
			model.addAttribute("formModel", formModel);
			return "UserTouroku";
		}
		return "redirect:UserItiran";
	}

	@RequestMapping(value = "/UserKensaku", method = RequestMethod.POST)
	public String userkensaku(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT * from tblLoginUser WHERE loginUserName LIKE ?",
				(('%') + formModel.getKensakuword()) + ('%'));
		model.addAttribute("list", list);
		model.addAttribute("formModel", formModel);
		return "UserItiran";
	}

	@RequestMapping(value = "/UserHensyu", method = RequestMethod.POST)
	public String userhensyu(@ModelAttribute UpdateFormModel formModel,
			Model model) {
		List<Map<String, Object>> updatelist = jdbcTemplate.queryForList(
				"SELECT * FROM tblLoginUser WHERE loginUserID = ?",
				formModel.getUpdateloginUserId());
		model.addAttribute("updatelist", updatelist);
		formModel.setUpdateloginUserId(formModel.getUpdateloginUserId());
		model.addAttribute("updateloginUserId",
				formModel.getUpdateloginUserId());
		model.addAttribute("formModel", formModel);
		return "UserUpdate";
	}

	@RequestMapping(value = "/UserUpdate", method = RequestMethod.POST)
	public String userupdate(@ModelAttribute UpdateFormModel formModel,
			Model model) {

		jdbcTemplate
				.update("UPDATE tblLoginUser SET loginUserName=?,loginUser=?,loginUserPass=? WHERE loginUserID=?",
						formModel.getUsername(), formModel.getUser(),
						formModel.getPassword(),
						formModel.getUpdateloginUserId());
		model.addAttribute("formModel", formModel);

		return "redirect:UserItiran";
	}

	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute UpdateFormModel formModel, Model model) {
		jdbcTemplate.update("DELETE FROM " + formModel.getTablename()
				+ " WHERE " + formModel.getPrimay() + " = "
				+ formModel.getDeleteId());
		model.addAttribute("formModel", formModel);
		String Itiran = formModel.getItiran();
		return Itiran;
	}

}