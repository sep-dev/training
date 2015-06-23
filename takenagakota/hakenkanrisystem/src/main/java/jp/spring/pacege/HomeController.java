package jp.spring.pacege;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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


		//ログインの処理
	@RequestMapping(value ="/login",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
		public String login(@RequestParam(value="ID") String ID,@RequestParam(value="Pass")String pass,Model model){
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblloginuser where loginUser=?",ID);
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



	//派遣先情報画面の検索ボタンの処理
	@RequestMapping(value="/kensaku",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
		public String sagasu(@ModelAttribute FormModel formmodel,Model model){
			String name =formmodel.getName();
			String nen =formmodel.getNen();
			String gatu=formmodel.getGatu();
			List<Map<String,Object>> list =jdbcTemplate.queryForList("select * from tblStaffManagement "
				+ "inner join tblstaff  on tblStaffManagement.staffid = tblstaff.staffID    		   "
				+ "inner join tblclient on tblStaffManagement.clientid = tblclient.clientId      	   "
				+ " where startDate <= concat("+nen+",-"+gatu+",-31) and "
				+ " endDate >= concat("+nen+",-"+gatu+",-31)             "
				+ " and staffName   like '%"+name+"%'");
			List<Map<String,Object>> sday=jdbcTemplate.queryForList("select year(startDate) as sday from tblStaffManagement group by sday"
			+ " ");
				model.addAttribute("sday",sday.get(0).get("sday"));
				model.addAttribute("sday",sday);
				model.addAttribute("data",list);
				return "itirann";
		}


	//派遣先編集画面への遷移
	@RequestMapping(value = "/hen", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
		public String hen(@RequestParam(value="hensyuu") int id, Model model) {
			List<Map<String,Object>> ID =jdbcTemplate.queryForList("select * from tblStaffManagement where staffManID = " + id);
			List<Map<String,Object>> syain =jdbcTemplate.queryForList("select * from tblstaff");
			List<Map<String,Object>> kigyou =jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("id",ID);
				model.addAttribute("haken",syain);
				model.addAttribute("kaisya",kigyou);
			FormModel form = new FormModel();
				form.setId(id);
				model.addAttribute("FormModel",form);
				return "hennsyuu";
		}



	//派遣先の編集の処理
	@RequestMapping(value="/update",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
		public String kousin(@Valid @ModelAttribute FormModel formmodel, BindingResult result, Model model){
			if(result.hasErrors()){
				int id = formmodel.getId();
				List<Map<String,Object>> ID =jdbcTemplate.queryForList("select * from tblStaffManagement where staffManID = " + id);
				List<Map<String,Object>> syain =jdbcTemplate.queryForList("select * from tblstaff");
				List<Map<String,Object>> kigyou =jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("id",ID);
				model.addAttribute("haken",syain);
				model.addAttribute("kaisya",kigyou);
				FormModel form = new FormModel();
				form.setId(id);
				model.addAttribute("FormModel",form);
				model.addAttribute("message","失敗しました");
				return "hennsyuu";
			}else{
				int syainID 	= formmodel.getSyainID();
				int kaisya 		= formmodel.getKaisya() ;
				int tankin 		= formmodel.getTankin( );
				String zyouken  = formmodel.getZyouken();
				int kouzyo	    = formmodel.getKouzyo() ;
				int tyouka	    = formmodel.getTyouka() ;
				String saito	= formmodel.getSaito()  ;
				String nen 		= formmodel.getNen()    ;
				String gatu	    = formmodel.getGatu()   ;
				String hi		=formmodel.getHi()		;
				String onen	    =formmodel.getOnen()	;
				String ogatu    =formmodel.getOgatu()	;
				String ohi      = formmodel.getOhi()    ;
				String bikou    =formmodel.getBikou()   ;
				int id          = formmodel.getId()     ;
			jdbcTemplate.update("update tblStaffManagement"
					+ " set "
					+"staffid         =" +syainID+	 ""
					+",clientID       =" + kaisya
					+",amountMonth    =" +tankin +	 ""
					+",conditions     ='"+ zyouken + "'"
					+",deductions     =" + kouzyo
					+",overtimeRate   =" + tyouka
					+",startDate      =concat(" + nen + ",-" + gatu + ",-" + hi + "),site='" + saito + "',"
					+"endDate         =concat("+ onen +",-" + ogatu + ",-" + ohi + ")"
					+",staffManRemarks='"+ bikou +"' "
					+"where staffManId=" + id );
			}
			return "redirect:/zenn";
		}



	@RequestMapping(value="/modoru",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
		public String kaeru(Model model){
		return "redirect:/zenn";
	}



	//派遣先登録画面への遷移
	@RequestMapping(value="sinki",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
		public String sinki(Model model){
			List<Map<String,Object>>syain=jdbcTemplate.queryForList  ("select * from tblstaff") ;
			List<Map<String,Object>>kaisya =jdbcTemplate.queryForList("select * from tblClient");
			model.addAttribute("syamei",syain)  ;
			model.addAttribute("kaisya",kaisya) ;
			FormModel form = new FormModel();
			model.addAttribute("FormModel",form);
		return "touroku";
	}


	//派遣先の登録処理
	@RequestMapping(value="/touroku",method=RequestMethod.POST ,produces="text/plain;charset=UTF-8")
		public String touroku(@Valid @ModelAttribute FormModel formmodel, BindingResult result,Model model){
			if(result.hasErrors()){
				int id = formmodel.getId();
				List<Map<String,Object>>syain=jdbcTemplate.queryForList("select * from tblstaff");
				List<Map<String,Object>>kaisya =jdbcTemplate.queryForList("select * from tblClient");
				model.addAttribute("syamei",syain);
				model.addAttribute("kaisya",kaisya);
				FormModel form = new FormModel();
				form.setId(id);
				model.addAttribute("FormModel",form);
				model.addAttribute("message","失敗です");
				return "touroku";
			}else{
				int syainID    =formmodel.getSyainID();
				int kaisya     =formmodel.getKaisya();
				int tankin     =formmodel.getTankin();
				String zyouken =formmodel.getZyouken();
				int kouzyo 	   =formmodel.getKouzyo();
				int tyouka     =formmodel.getTyouka();
				String saito   =formmodel.getSaito();
				String nen     =formmodel.getNen();
				String gatu    =formmodel.getGatu();
				String hi      =formmodel.getHi();
				String onen    =formmodel.getOnen();
				String ogatu   =formmodel.getOgatu();
				String ohi     =formmodel.getOhi();
				String bikou   =formmodel.getBikou();
			jdbcTemplate.update("insert into tblStaffManagement "
					+ "(staffId,clientId,amountMonth,conditions,deductions,overtimeRate,site,"
					+ "startDate,endDate,staffManRemarks)"
					+ "value"
					+ "("+syainID+","+kaisya+","+tankin+",'"+zyouken+"',"+kouzyo+","+tyouka+",'"+saito+"',"
						 + "concat("+nen+",-"+gatu+",-"+hi+"),concat("+onen+",-"+ogatu+",-"+ohi+"),'"+bikou+"')");
			}
			return "redirect:/zenn";
		}



	//ログイン後の派遣先の表示
	@RequestMapping(value="/zenn",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	 	public String sagasu(Model model){
				List<Map<String,Object>> list =jdbcTemplate.queryForList("select * from tblStaffManagement "
					+ "inner join tblstaff on tblStaffManagement.staffid  = tblstaff.staffID  "
					+"inner join tblclient on tblStaffManagement.clientid = tblclient.clientId");
				List<Map<String,Object>> sday=jdbcTemplate.queryForList("select year(startDate) as sday from tblStaffManagement group by sday"
			        + " ");
				model.addAttribute("sday",sday.get(0).get("sday"));
				model.addAttribute("sday",sday);
				FormModel form = new FormModel();
				model.addAttribute("FormModel",form);
				model.addAttribute("data",list);
			return "itirann";
		}



	//派遣先の削除画面への遷移
	@RequestMapping(value="/sakuzyogamen",method=RequestMethod.POST)
		public String kesugamen(@RequestParam(value="kesu")int kesu,Model model){
			model.addAttribute("kesu",kesu);
		return "sakuzyogamen";
	}



	//派遣先の削除処理
	@RequestMapping(value="/sakuzyo",method=RequestMethod.POST)
		public String kesu(@RequestParam(value="kesu")int kesu,Model model){
			jdbcTemplate.update("delete from tblStaffManagement where StaffManId = "+kesu+"");
		return "redirect:/zenn";
	}



	//派遣社員情報の表示
	@RequestMapping(value="/syain",method=RequestMethod.GET)
	public String syain(Model model){
		List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblstaff"
				+ " inner join tblAffiliation on tblstaff.affiliationId = tblAffiliation.affiliationId");
		model.addAttribute("data",list)      ;
		FormModel2 form = new FormModel2() ;
		model.addAttribute("FormModel2",form);
		return "syainhensyuu";
	}


	//派遣社員情報の検索ボタンの処理
	@RequestMapping(value = "/syainsagasu", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String syainsagasu(@ModelAttribute FormModel2 formmodel,Model model){
		String name =formmodel.getName();
		List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblStaff"
				+ " inner join tblAffiliation on tblstaff.affiliationId = tblAffiliation.affiliationId"
				+ " where staffName like '%"+name+"%'");
		model.addAttribute("data",list);
		return "syainhensyuu";
	}



	//派遣社員登録画面の遷移
	@RequestMapping(value = "/syainsinki",method = RequestMethod.GET)
		public String syaintouroku(Model model){
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblAffiliation");
			model.addAttribute("data",list);
			FormModel2 form = new FormModel2();
			model.addAttribute("FormModel2",form);
			return "syaintouroku";
	}



	//派遣社員の登録処理
	@RequestMapping(value = "/syaintouroku",method=RequestMethod.POST)
		public String touroku(@Valid @ModelAttribute FormModel2 formmodel, BindingResult result,Model model){
			if(result.hasErrors()){
				List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblAffiliation");
				model.addAttribute("data",list);
				FormModel2 form = new FormModel2();
				model.addAttribute("FormModel2",form);
				model.addAttribute("message","失敗です。");
				return "syaintouroku";
			}else{
				String name   =formmodel .getName()    ;
				String meru   =formmodel .getMeru()    ;
				String dennwa =formmodel .getDennwa()  ;
				String keitai =formmodel .getKeitai()  ;
				String yuubinn=formmodel .getYuubinn() ;
				String zyusyo =formmodel .getZyusyo()  ;
				String eki    =formmodel .getEki()     ;
				int kaisyaID  = formmodel.getKaisyaID();
				String bikou  = formmodel.getBikou()   ;
					jdbcTemplate.update("insert into tblStaff "
						+ "(staffName                 ,"
						+ "staffMail                  ,"
						+ "staffTel                   ,"
						+ "staffMobileTel             ,"
						+ "staffPostCode              ,"
						+ "staffAdd                   ,"
						+ "staffNearestStation        ,"
						+ "affiliationId              ,"
						+ "staffRemarks               )"
						+ " value("
						+ "'" +name+   "',"
						+ "'" +meru+   "',"
						+ "'" +dennwa+ "',"
						+ "'" +keitai+ "',"
						+ "'" +yuubinn+"',"
						+ "'" +zyusyo+ "',"
						+ "'" +eki+    "',"
						+ "'" +kaisyaID+ "',"
						+ "'" +bikou+  "')");
				return "redirect:/syain";
			}
		}



	//派遣社員編集画面への遷移
	@RequestMapping(value="syainhen",method=RequestMethod.GET)
		public String hensyu(@RequestParam(value="hensyuu")int hensyuu,Model model){
				List<Map<String,Object>>list = jdbcTemplate.queryForList("select * from tblstaff "
					+ " where staffId="+hensyuu+"");
				List<Map<String,Object>>syozoku=jdbcTemplate.queryForList("select * from tblAffiliation");
				FormModel2 form = new FormModel2();
				model.addAttribute("FormModel2"         ,form);
				model.addAttribute("staffName"          ,list.get(0).get("staffName"));
				model.addAttribute("staffMail"          ,list.get(0).get("staffMail"));
				model.addAttribute("staffPostCode"      ,list.get(0).get("staffPostCode"));
				model.addAttribute("staffAdd"           ,list.get(0).get("staffAdd"));
				model.addAttribute("staffTel"           ,list.get(0).get("staffTel"));
				model.addAttribute("staffMobileTel"     ,list.get(0).get("staffMobileTel"));
				model.addAttribute("affiliationId"      ,list.get(0).get("affiliationId"));
				model.addAttribute("staffRemarks"       ,list.get(0).get("staffRemarks"));
				model.addAttribute("staffNearestStation",list.get(0).get("staffNearestStation"));
				model.addAttribute("data",syozoku) ;
				model.addAttribute("id",hensyuu)   ;
			return "syainhen";
	}


	//派遣社員の編集処理
	@RequestMapping(value="syainhensyu",method=RequestMethod.POST)
		public String kaeru(@Valid @ModelAttribute FormModel2 formmodel,BindingResult result,Model model){
			int id = formmodel.getId();
			String dennwa =formmodel.getDennwa();
			String keitai =formmodel.getKeitai();
			String yuubinn=formmodel.getYuubinn();
			String name   =formmodel.getName();
			System.out.println(name)   ;
			System.out.println(dennwa) ;
			System.out.println(keitai) ;
			System.out.println(yuubinn);
		if(result.hasErrors()){
			List<Map<String,Object>>list = jdbcTemplate.queryForList("select * from tblstaff "
					+ " where staffId="+id+"");
			List<Map<String,Object>>syozoku=jdbcTemplate.queryForList("select * from tblAffiliation");
			FormModel2 form = new FormModel2();
			model.addAttribute("FormModel2",form);
			model.addAttribute("staffName",list.get(0).get("staffName"));
			model.addAttribute("staffMail",list.get(0).get("staffMail"));
			model.addAttribute("staffPostCode",list.get(0).get("staffPostCode"));
			model.addAttribute("staffAdd",list.get(0).get("staffAdd"));
			model.addAttribute("staffTel",list.get(0).get("staffTel"));
			model.addAttribute("staffMobileTel",list.get(0).get("staffMobileTel"));
			model.addAttribute("affiliationId",list.get(0).get("affiliationId"));
			model.addAttribute("staffRemarks",list.get(0).get("staffRemarks"));
			model.addAttribute("staffNearestStation",list.get(0).get("staffNearestStation"));
			model.addAttribute("data",syozoku);
			model.addAttribute("id",id);
			model.addAttribute("message","失敗です。");
			return "syainhen";
		}else{
			String meru   =formmodel.getMeru();
			String zyusyo =formmodel.getZyusyo();
			String eki    =formmodel.getEki ();
			int kaisyaID  = formmodel.getKaisyaID();
			String bikou  = formmodel.getBikou();
			jdbcTemplate.update("update tblstaff"
					+ " set "
					+ "staffName           = '"+name   +"',"
					+ "staffMail           = '"+meru   +"',"
					+ "staffTel            = '"+dennwa +"',"
					+ "staffmobileTel      = '"+keitai +"',"
					+ "staffPostCode       = '"+yuubinn+"',"
					+ "staffAdd            = '"+zyusyo +"',"
					+ "staffNearestStation = '"+eki    +"',"
					+ "affiliationId       = '"+kaisyaID +"',"
					+ "staffRemarks        = '"+bikou  +"'"
					+ " where  staffId     =  "+id     +"");
		return  "redirect:/syain";
		}
	}



	//派遣社員編集の処理
	@RequestMapping(value="syainsakuzyo",method=RequestMethod.POST)
		public String syainsakuzyo(@RequestParam(value="kesu")int kesu,Model model){
			jdbcTemplate.update("delete from tblstaff where staffId="+kesu+"");
		return "redirect:/syain";
	}



	//所属元情報の表示
	@RequestMapping(value="/syozoku",method=RequestMethod.GET)
	public String syozoku(Model model){
		List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblAffiliation");
		model.addAttribute("data",list);
		FormModel2 form = new FormModel2();
		model.addAttribute("FormModel2",form);
		return "syozoku";
	}



	//所属元情報の検索ボタンの処理
	@RequestMapping(value="/syozokusagasu",method=RequestMethod.POST)
		public String syozokusagasu(@ModelAttribute FormModel2 formmodel,Model model){
			String name=formmodel.getName();
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblaffiliation"
					+ " where affiliationName like '%"+name+"%'");
			model.addAttribute("data",list);
		return "syozoku";
	}



	//所属元登録画面の遷移
	@RequestMapping(value="/syozokusinki",method=RequestMethod.GET)
		public String syozokusinki(Model model){
		FormModel3 form = new FormModel3();
			model.addAttribute("FormModel3",form);
		return "syozokutouroku";
	}



	//所属元の登録処理
	@RequestMapping(value="/syozokutouroku",method=RequestMethod.POST)
		public String syozokutouroku(@Valid FormModel3 formmodel,BindingResult result,Model model){
			if(result.hasErrors()){
				FormModel3 form = new FormModel3();
				model.addAttribute("FormModel3",form);
				model.addAttribute("message","失敗です");
		return "syozokutouroku";
		}else{
				String name   =formmodel.getName();
				String dennwa =formmodel.getDennwa();
				String yuubinn=formmodel.getYuubinn();
				String zyusyo =formmodel.getZyusyo();
				String eki    =formmodel.getEki();
				String bikou  =formmodel.getBikou();
		jdbcTemplate.update("insert into tblaffiliation"
				+ "("
				+ "affiliationName,"
				+ "affiliationTel,"
				+ "affiliationPostCode,"
				+ "affiliationNearestStation,"
				+ "affiliationAdd,"
				+ "affiliationRemarks)"
				+ "value"
				+ "("
				+ "'"+name   +"',"
				+ "'"+dennwa +"',"
				+ "'"+yuubinn+"',"
				+ "'"+zyusyo +"',"
				+ "'"+eki    +"',"
				+ "'"+bikou  +"')");
		return "redirect:syozoku";
		}
	}




	//所属元登録処理
	@RequestMapping(value="/syozokuhensyu",method=RequestMethod.POST)
		public String syozokuhensyu(@Valid FormModel3 formmodel,BindingResult result,Model model){
			int id=formmodel.getId();
			if(result.hasErrors()){
				List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblaffiliation"
						+ " where affiliationId ="+id+"");
				FormModel3 form = new FormModel3();
				model.addAttribute("FormModel3",form);
				model.addAttribute("affiliationName",list.get(0).get("affiliationName"));
				model.addAttribute("affiliationPostCode",list.get(0).get("affiliationPostCode"));
				model.addAttribute("affiliationAdd",list.get(0).get("affiliationAdd"));
				model.addAttribute("affiliationTel",list.get(0).get("affiliationTel"));
				model.addAttribute("affiliationId",list.get(0).get("affiliationId"));
				model.addAttribute("affiliationRemarks",list.get(0).get("affiliationRemarks"));
				model.addAttribute("affiliationNearestStation",list.get(0).get("affiliationNearestStation"));
				model.addAttribute("data",list);
				model.addAttribute("id",id);
				model.addAttribute("message","失敗です");
		return "syozokuhensyu";
		}else{
					String name   =formmodel.getName();
					String dennwa =formmodel.getDennwa();
					String yuubinn=formmodel.getYuubinn();
					String zyusyo =formmodel.getZyusyo();
					String eki    =formmodel.getEki();
					String bikou  = formmodel.getBikou();
		jdbcTemplate.update("update tblaffiliation"
				+ " set "
				+ "affiliationName           = '"+name   +"',"
				+ "affiliationTel            = '"+dennwa +"',"
				+ "affiliationPostCode       = '"+yuubinn+"',"
				+ "affiliationAdd            = '"+zyusyo +"',"
				+ "affiliationNearestStation = '"+eki    +"',"
				+ "affiliationRemarks        = '"+bikou  +"'"
				+ " where  affiliationId     =  "+id     +"");
		return "redirect:syozoku";
		}
	}



	//所属元編集画面の遷移
	@RequestMapping(value="/syozokuhen",method=RequestMethod.POST)
		public String syozokuhen(@RequestParam(value="id")int id, Model model){
				List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblaffiliation"
					+ " where affiliationId ="+id+"");
				FormModel3 form = new FormModel3();
				model.addAttribute("FormModel3",form);
				model.addAttribute("affiliationName",list.get(0).get("affiliationName"));
				model.addAttribute("affiliationPostCode",list.get(0).get("affiliationPostCode"));
				model.addAttribute("affiliationAdd",list.get(0).get("affiliationAdd"));
				model.addAttribute("affiliationTel",list.get(0).get("affiliationTel"));
				model.addAttribute("affiliationId",list.get(0).get("affiliationId"));
				model.addAttribute("affiliationRemarks",list.get(0).get("affiliationRemarks"));
				model.addAttribute("affiliationNearestStation",list.get(0).get("affiliationNearestStation"));
				model.addAttribute("data",list);
				model.addAttribute("id",id);
		return "syozokuhensyu";
	}



	//所属元の削除の処理
	@RequestMapping(value="/syozokusakuzyo",method = RequestMethod.POST)
		public String syozokusakuzyo(@RequestParam(value="kesu")int kesu, Model model){
			jdbcTemplate.update("delete from tblaffiliation where affiliationId= "+kesu+"");
		return "redirect:/syozoku";
	}



	//出向先情報の表示
	@RequestMapping(value="/syukkou",method=RequestMethod.GET)
		public String syukkou(Model model){
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblclient");
			model.addAttribute("data",list);
			FormModel3 form = new FormModel3();
			model.addAttribute("FormModel3",form);
			return "syukkou";
	}



	//出向先情報の検索ボタンの処理
	@RequestMapping(value="/syukkousagasu",method=RequestMethod.POST)
		public String syukkousagasu(@ModelAttribute FormModel3 formmodel,Model model){
			String name=formmodel.getName();
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblclient"
				+ " where clientName like '%"+name+"%'");
			System.out.println("name:"+list);
			model.addAttribute("data",list);
		return "syukkou";
	}



	//出向先登録画面の遷移
	@RequestMapping(value="/syukkousinki",method=RequestMethod.GET)
		public String syukkousinki(Model model){
				FormModel3 form = new FormModel3();
				model.addAttribute("FormModel3",form);
		return "syukkoutouroku";
	}



	//出向先登録処理
	@RequestMapping(value="/syukkoutouroku",method=RequestMethod.POST)
	public String syukkoutouroku(@Valid FormModel3 formmodel,BindingResult result,Model model){
		if(result.hasErrors()){
			FormModel3 form = new FormModel3();
			model.addAttribute("FormModel3",form);
			model.addAttribute("message","失敗です");
			return "syukkoutouroku";
		}else{
			String name =formmodel.getName();
			String dennwa =formmodel.getDennwa();
			String yuubinn=formmodel.getYuubinn();
			String zyusyo =formmodel.getZyusyo();
			String eki =formmodel.getEki();
			String bikou = formmodel.getBikou();
		jdbcTemplate.update("insert into tblClient"
				+ "("
				+ "clientName          ,"
				+ "clientTel           ,"
				+ "clientPostCode      ,"
				+ "clientNearestStation,"
				+ "clientAdd           ,"
				+ "clientRemarks)"
				+ "value"
				+ "("
				+ "'"+name   +"',"
				+ "'"+dennwa +"',"
				+ "'"+yuubinn+"',"
				+ "'"+zyusyo +"',"
				+ "'"+eki    +"',"
				+ "'"+bikou  +"')");
		return "redirect:syukkou";
		}
	}



	//出向先編集画面の遷移
	@RequestMapping(value="/syukkouhen",method=RequestMethod.GET)
		public String syukkouhen(@RequestParam(value="id")int id, Model model){
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblclient"
					+ " where clientId ="+id+"");
			FormModel3 form = new FormModel3();
			model.addAttribute("FormModel3",form);
			model.addAttribute("clientName",list.get(0).get("clientName"));
			model.addAttribute("clientPostCode",list.get(0).get("clientPostCode"));
			model.addAttribute("clientAdd",list.get(0).get("clientAdd"));
			model.addAttribute("clientTel",list.get(0).get("clientTel"));
			model.addAttribute("clientId",list.get(0).get("clientId"));
			model.addAttribute("clientRemarks",list.get(0).get("clientRemarks"));
			model.addAttribute("clientNearestStation",list.get(0).get("clientNearestStation"));
			model.addAttribute("data",list);
			model.addAttribute("id",id);
		return "syukkouhensyu";
	}



	//出向先の編集の処理
	@RequestMapping(value="/syukkouhensyu",method=RequestMethod.POST)
		public String syukkouhensyu(@Valid FormModel3 formmodel,BindingResult result,Model model){
			int id =formmodel.getId();
			if(result.hasErrors()){
				List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblclient"
						+ " where clientId ="+id+"");
				FormModel3 form = new FormModel3();
				model.addAttribute("FormModel3",form);
				model.addAttribute("clientName",list.get(0).get("clientName"));
				model.addAttribute("clientPostCode",list.get(0).get("clientPostCode"));
				model.addAttribute("clientAdd",list.get(0).get("clientAdd"));
				model.addAttribute("clientTel",list.get(0).get("clientTel"));
				model.addAttribute("clientId",list.get(0).get("clientId"));
				model.addAttribute("clientRemarks",list.get(0).get("clientRemarks"));
				model.addAttribute("clientNearestStation",list.get(0).get("clientNearestStation"));
				model.addAttribute("data",list);
				model.addAttribute("id",id);
				model.addAttribute("message","失敗です");
				return "syukkouhensyu";
			}else{
					String name   =formmodel.getName();
					String dennwa =formmodel.getDennwa();
					String yuubinn=formmodel.getYuubinn();
					String zyusyo =formmodel.getZyusyo();
					String eki =formmodel.getEki();
					String bikou = formmodel.getBikou();
				jdbcTemplate.update("update tblclient"
					+ " set "
					+ "clientName           = '"+name   +"',"
					+ "clientTel            = '"+dennwa +"',"
					+ "clientPostCode       = '"+yuubinn+"',"
					+ "clientAdd            = '"+zyusyo +"',"
					+ "clientNearestStation = '"+eki    +"',"
					+ "clientRemarks        = '"+bikou  +"'"
					+ " where  clientId     =  "+id     +"");
		return "redirect:syukkou";
		}
	}



	//出向先の削除の処理
	@RequestMapping(value="/syukkousakuzyo",method = RequestMethod.POST)
		public String syukkousakuzyo(@RequestParam(value="kesu")int kesu, Model model){
			jdbcTemplate.update("delete from tblclient where clientId= "+kesu+"");
		return "redirect:/syukkou";
	}



	//ログインユーザー情報の表示
	@RequestMapping(value="/user",method=RequestMethod.GET)
		public String user(Model model){
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblloginUser");
				model.addAttribute("data",list);
			FormModel4 form = new FormModel4();
				model.addAttribute("FormModel4",form);
		return "user";
	}



	//ログインユーザー情報の探すボタンの処理
	@RequestMapping(value="/usersagasu",method=RequestMethod.POST)
		public String usersagasu(@ModelAttribute FormModel4 formmodel,Model model){
				String name=formmodel.getName();
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblloginuser"
					+ " where loginuserName like '%"+name+"%'");
				System.out.println("name:"+list);
			model.addAttribute("data",list);
		return "user";
	}



	//管理者登録画面への遷移
	@RequestMapping(value="/usersinki",method=RequestMethod.GET)
	public String usersinki(Model model){
			FormModel4 form = new FormModel4();
			model.addAttribute("FormModel4",form);
		return "usertouroku";
	}



	//管理者の登録
	@RequestMapping(value="/usertouroku",method=RequestMethod.POST)
		public String usertouroku(@Valid FormModel4 formmodel,BindingResult result,Model model){
			if(result.hasErrors()){
			FormModel4 form = new FormModel4();
				model.addAttribute("FormModel4",form);
				model.addAttribute("message","失敗です");
				return "usertouroku";
			}else{
				String name     =formmodel.getName();
				String loginid  =formmodel.getLoginID();
				String pass     =formmodel.getPass();
				String kakuninn =formmodel.getKakuninn();
			if(!(pass.equals(kakuninn))){
				FormModel4 form = new FormModel4();
				 model.addAttribute("FormModel4",form);
				 model.addAttribute("message","失敗です");
				return "usertouroku";

			}else{
				jdbcTemplate.update("insert into tblloginUser"
						+ "("
						+ "loginUserName,"
						+ "loginUser    ,"
						+ "loginUserPass"
						+ ")"
						+ "values"
						+ "("
						+ "'"+name   +"',"
						+ "'"+loginid+"',"
						+ "'"+pass   +"')");
		return "redirect:user";
			}
		}
	}



	//管理者編集画面の遷移
	@RequestMapping(value="/userhen",method=RequestMethod.POST)
		public String userhen(@RequestParam(value="id")int id, Model model){
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblLoginUser"
					+ " where loginUserId ="+id+"");
			FormModel4 form = new FormModel4();
			model.addAttribute("FormModel4",form);
			model.addAttribute("loginUserName",list.get(0).get("loginUserName"));
			model.addAttribute("loginUser",list.get(0).get("loginUser"));
			model.addAttribute("data",list);
			model.addAttribute("id",id);
		return "userhensyu";
	}



	//管理者の編集処理
	@RequestMapping(value="/userhensyu",method=RequestMethod.POST)
		public String userhensyu(@Valid FormModel4 formmodel,BindingResult result,Model model){
			int id =formmodel.getId();
		if(result.hasErrors()){
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblLoginUser"
					+ " where loginUserId ="+id+"");
			FormModel4 form = new FormModel4();
			model.addAttribute("FormModel4",form);
			model.addAttribute("loginUserName",list.get(0).get("loginUserName"));
			model.addAttribute("loginUser",list.get(0).get("loginUser"));
			model.addAttribute("data",list);
			model.addAttribute("id",id);
			model.addAttribute("message","失敗です");
			return "userhensyu";
		}
			String name     =formmodel.getName();
			String loginID  =formmodel.getLoginID();
			String pass     =formmodel.getPass();
			String kakuninn =formmodel.getKakuninn();
		if(!(pass.equals(kakuninn))){
			List<Map<String,Object>>list=jdbcTemplate.queryForList("select * from tblLoginUser"
					+ " where loginUserId ="+id+"");
			FormModel4 form = new FormModel4();
			model.addAttribute("FormModel4",form);
			model.addAttribute("loginUserName",list.get(0).get("loginUserName"));
			model.addAttribute("loginUser",list.get(0).get("loginUser"));
			model.addAttribute("data",list);
			model.addAttribute("id",id);
			model.addAttribute("message","失敗です");
			return "userhensyu";
			}else{
		jdbcTemplate.update("update tblloginUser"
				+ " set "
				+ "loginUserName        = '"+name     +"',"
				+ "loginUser            = '"+loginID  +"',"
				+ "loginUserPass        = '"+pass     +"'"
				+ " where  loginUserID     =  "+id    +"");
		return "redirect:user";
		}
	}
	@RequestMapping(value="/usersakuzyo",method = RequestMethod.POST)
	public String usersakuzyo(@RequestParam(value="kesu")int kesu, Model model){
		jdbcTemplate.update("delete from tblloginUser where loginUserId= "+kesu+"");
		return "redirect:/user";
	}
	@RequestMapping(value="/RINKU",method=RequestMethod.GET)
	public String RINKU(@ModelAttribute FormModel2 formmodel,@RequestParam(value="hensyuu")int hensyuu,Model model){
		System.out.println(hensyuu);
		List<Map<String,Object>>list = jdbcTemplate.queryForList("select * from tblstaff "
				+ " where staffId="+hensyuu+"");
		List<Map<String,Object>>syozoku=jdbcTemplate.queryForList("select * from tblAffiliation");
		FormModel2 form = new FormModel2();
		model.addAttribute("FormModel2",form);
		model.addAttribute("staffName",list.get(0).get("staffName"));
		model.addAttribute("staffMail",list.get(0).get("staffMail"));
		model.addAttribute("staffPostCode",list.get(0).get("staffPostCode"));
		model.addAttribute("staffAdd",list.get(0).get("staffAdd"));
		model.addAttribute("staffTel",list.get(0).get("staffTel"));
		model.addAttribute("staffMobileTel",list.get(0).get("staffMobileTel"));
		model.addAttribute("affiliationId",list.get(0).get("affiliationId"));
		model.addAttribute("staffRemarks",list.get(0).get("staffRemarks"));
		model.addAttribute("staffNearestStation",list.get(0).get("staffNearestStation"));
		model.addAttribute("data",syozoku);
		model.addAttribute("id",hensyuu);
		return "syainhen";
	}
}



