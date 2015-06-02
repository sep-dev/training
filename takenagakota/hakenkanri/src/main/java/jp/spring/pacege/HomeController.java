package jp.spring.pacege;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "home";
	}
	@RequestMapping(value ="/login",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String login(@RequestParam(value="ID") String ID,@RequestParam(value="Pass")String pass,Model model){
		List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblloginuser where loginUserID=?",ID);
		if(list.size()>=1){
			if(list.get(0).get("loginUserPass").equals(pass)){
				return "mein";
			}else{
				model.addAttribute("dame","ユーザが違います");
				return "home";
			}
		}else{
			model.addAttribute("dame","ユーザが違います");
			return "home";
		}
	}
	@RequestMapping(value="/kensaku",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String sagasu(@ModelAttribute FormModel formmodel,Model model){
	String name =formmodel.getName();
	String nen =formmodel.getNen();
	String gatu=formmodel.getGatu();
		List<Map<String,Object>> list =jdbcTemplate.queryForList("select * from tblStaffManagement "
				+ "inner join tblstaff on tblStaffManagement.staffid = tblstaff.staffID "
				+"inner join tblclient on tblStaffManagement.clientid = tblclient.clientId"
				+ " where startDate <= concat("+nen+",-"+gatu+",-31) and"
				+  " endDate >= concat("+nen+",-"+gatu+",-31)"
						+ " and staffName like '%"+name+"%'");
		model.addAttribute("data",list);
		return "itirann";

	}
	@RequestMapping(value = "/hen", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String hen(@RequestParam(value="hensyuu") int id, Model model) {
		System.out.println("a");
		List<Map<String,Object>> ID =jdbcTemplate.queryForList("select * from tblStaff where staffID = " + id);
		List<Map<String,Object>> syamei =jdbcTemplate.queryForList("select * from tblstaff");
		List<Map<String,Object>> kigyou =jdbcTemplate.queryForList("select * from tblClient");
		model.addAttribute("id2",id);
		model.addAttribute("id",ID);
		model.addAttribute("haken",syamei);
		model.addAttribute("kaisya",kigyou);
		System.out.println("b");
		FormModel form = new FormModel();
		form.setId(id);
		model.addAttribute("FormModel",form);
		return "hennsyuu";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String kousin(@ModelAttribute FormModel formmodel,Model model){
		int syamei =formmodel.getSyamei();
		int kaisya =formmodel.getKaisya();
		String tankin =formmodel.getTankin();
		String zyouken = formmodel.getZyouken();
		String kouzyo = formmodel.getKouzyo();
		String tyouka = formmodel.getTyouka();
		String saite=formmodel.getSaite();
		String nen = formmodel.getNen();
		String gatu =formmodel.getGatu();
		String hi=formmodel.getHi();
		String onen =formmodel.getOnen();
		String ogatu =formmodel.getOgatu();
		String ohi = formmodel.getOhi();
		String bikou =formmodel.getBikou();
		int id = formmodel.getId();
		jdbcTemplate.update("update tblStaffManagement"
				+ " set staffId="+syamei
				+",clientID="+ kaisya
				+",amountMonth="+tankin + ""
				+",conditions='"+ zyouken + "'"
				+",deductions="+ kouzyo
				+",overtimeRate="+ tyouka
				+",startDate = concat(" + nen + ",-" + gatu + ",-" + hi + "),site='" + saite + "',"
				+ "endDate=concat("+ onen +",-" + ogatu + ",-" + ohi + ")"
				+",staffManRemarks='" + bikou +"' "
				+ "where staffId=" + id );
		return "itirann";
	}
	@RequestMapping(value="/modoru",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public String kaeru(Model model){
		return "itirann";
	}
	@RequestMapping(value="/sakuzyo",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public String nakunaru(@RequestParam(value="kesu") int kesu,Model model){
		model.addAttribute("kesu",kesu);
		return "sakuzyo";
	}
	@RequestMapping(value="sinki",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public String sinki(Model model){
		List<Map<String,Object>>syain=jdbcTemplate.queryForList("select * from tblstaff");
		List<Map<String,Object>>kaisya =jdbcTemplate.queryForList("select * from tblClient");
		model.addAttribute("syamei",syain);
		model.addAttribute("kaisya",kaisya);
		FormModel form = new FormModel();
		model.addAttribute("FormModel",form);
		return "touroku";
	}
	@RequestMapping(value="/touroku",method=RequestMethod.POST ,produces="text/plain;charset=UTF-8")
	public String touroku(@ModelAttribute FormModel formmodel,Model model){
		int syamei =formmodel.getSyamei();
		int kaisya =formmodel.getKaisya();
		String tankin =formmodel.getTankin();
		String zyouken = formmodel.getZyouken();
		String kouzyo = formmodel.getKouzyo();
		String tyouka = formmodel.getTyouka();
		String saite=formmodel.getSaite();
		String nen = formmodel.getNen();
		String gatu =formmodel.getGatu();
		String hi=formmodel.getHi();
		String onen =formmodel.getOnen();
		String ogatu =formmodel.getOgatu();
		String ohi = formmodel.getOhi();
		String bikou =formmodel.getBikou();
		jdbcTemplate.update("insert into tblStaffManagement "
				+ "(staffId,clientId,amountMonth,conditions,deductions,overtimeRate,site,"
				+ "startDate,endDate,staffManRemarks)"
				+ "value"
				+ "("+syamei+","+kaisya+","+tankin+",'"+zyouken+"',"+kouzyo+","+tyouka+",'"+saite+"',"
						+ "concat("+nen+",-"+gatu+",-"+hi+"),concat("+onen+",-"+ogatu+",-"+ohi+"),'"+bikou+"')");
		return "itirann";
	}
	@RequestMapping(value="/zenn",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public String sagasu(Model model){
		List<Map<String,Object>> list =jdbcTemplate.queryForList("select * from tblStaffManagement "
				+ "inner join tblstaff on tblStaffManagement.staffid = tblstaff.staffID "
				+"inner join tblclient on tblStaffManagement.clientid = tblclient.clientId");
		FormModel form = new FormModel();
		model.addAttribute("FormModel",form);
		model.addAttribute("data",list);
		return "itirann";
	}
	}


