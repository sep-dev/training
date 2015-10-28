package sample.web.test;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
	@Autowired
    private JdbcTemplate jdbcTemplate;


//教員トップページ

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home( Model model) {


		String msg = "",msg2 ="",msg3 = "",msg4 = "";
		   List<Map<String, Object>> gettable = jdbcTemplate.queryForList("select * from tblschool");
		   for (Map<String, Object > map :gettable) {
			   msg4 =map.get("schoolId").toString();
			   msg ="<option value="+msg4+">";

			   msg2 =  map.get("schoolName").toString()+"</option>";
			   msg3 = msg3+msg + msg2;
		   	   }

		   model.addAttribute("msg", msg3);

		return "home";
	}


	//教員Topページ決定ボタン

	@RequestMapping(value = "/", params = "a",method = RequestMethod.POST)
	public String a(@RequestParam("example4") String schoolId,Locale locale, Model model) {
		String msg = "";
		   List<Map<String, Object>> gettable = jdbcTemplate.queryForList("select * from tblYear where schoolId="+schoolId+"");

		   int c =0;
		   for (Map<String, Object > map :gettable) {
			   if(c < 1){
			         msg = msg + "<input type='radio'  name='id' value="+map.get("yearNumber").toString()+" checked= 'checked'>";
			   }else{

				   msg = msg + "<input type='radio'  name='id' value="+map.get("yearNumber").toString()+">";
		            }
			   msg = msg + map.get("yearNumber").toString() +"年生" ;
		   List<Map<String, Object>> i = jdbcTemplate.queryForList("select * from tblschool where schoolId="+schoolId+"");

		   List<Map<String, Object>> a = jdbcTemplate.queryForList("select * from tblclass where schoolId="+schoolId+"");
		           a.get(0).get("yearId");
		   List<Map<String, Object>> b = jdbcTemplate.queryForList("select * from tblclass where schoolId="+schoolId+"");
		           b.get(0).get("schoolId");
		           c= c+1;

		   model.addAttribute("message", msg);
		   model.addAttribute("message1", a.get(0).get("yearId"));
		   model.addAttribute("message2", b.get(0).get("schoolId"));
		   model.addAttribute("msg",  i.get(0).get("schoolName"));

			}
		   return "classselect";
	}

	//学年選択 決定ボタン

	@RequestMapping(value = "/", params = "b",method = RequestMethod.POST)
	public String b(@RequestParam("id") String id,Locale locale,FormModel fm, Model model) {

		String msg = "",msg2 ="",msg3 = "",msg4 = "";

           int yearId2=fm.getYearId();


           int schoolId2=fm.getSchoolId();


		   List<Map<String, Object>> gettable = jdbcTemplate.queryForList("select * from tblClass where yearId="+yearId2+"");
		  for (Map<String, Object > map :gettable) {

			   msg4 =map.get("className").toString();
			   msg ="<option value="+msg4+">";

			   msg2 =  map.get("className").toString()+"</option>";
			   msg3 = msg3+msg + msg2;
		   	   }

		  //List<Map<String, Object>> a = jdbcTemplate.queryForList("select * from tblclass where schoolId="+schoolId2+"");
          //a.get(0).get("yearId");
          List<Map<String, Object>> b = jdbcTemplate.queryForList("select * from tblclass where schoolId="+schoolId2+"");
          b.get(0).get("schoolId");

          List<Map<String, Object>> i = jdbcTemplate.queryForList("select * from tblschool where schoolId="+schoolId2+"");

		   model.addAttribute("msg1", i.get(0).get("schoolName"));
		   model.addAttribute("message", msg3);
		   model.addAttribute("msg",id );
		   model.addAttribute("message1", id);
		   model.addAttribute("message2", b.get(0).get("schoolId"));

		   return "classselect2";


	}


	//学年選択 戻るボタン

		@RequestMapping(value = "/", params = "c",method = RequestMethod.POST)
		public String c(@RequestParam("example4") String Id, Locale locale, Model model) {
			String msg = "",msg2 ="",msg3 = "",msg4 = "";
			   List<Map<String, Object>> gettable = jdbcTemplate.queryForList("select * from tblschool");
			   for (Map<String, Object > map :gettable) {
				   msg4 =map.get("schoolId").toString();
				   msg ="<option value="+msg4+">";

				   msg2 =  map.get("schoolName").toString()+"</option>";
				   msg3 = msg3+msg + msg2;
			   	   }

			   model.addAttribute("msg", msg3);

			return "home";
				}


		//学年選択 戻るボタン
@RequestMapping(value = "/", params = "d",method = RequestMethod.POST)
public String d(Locale locale, Model model) {




return "classselect";
}

//クラス選択 決定ボタン
@RequestMapping(value = "/", params = "e",method = RequestMethod.POST)
public String e(Locale locale,FormModel fm, Model model) {

    String className1=fm.getClassName();
	int yearId=fm.getYearId();

    int schoolId=fm.getSchoolId();
    List<Map<String, Object>> i = jdbcTemplate.queryForList("select * from tblschool where schoolId= "+schoolId+" ");

    model.addAttribute("message", i.get(0).get("schoolName") );
   model.addAttribute("message1", yearId);
   model.addAttribute("msg",className1);

return "subjectselect";
}
//学年選択 戻るボタン
@RequestMapping(value = "/", params = "j",method = RequestMethod.POST)
public String j(Locale locale, Model model) {


return "classselect2";
}
//国語ボタン
@RequestMapping(value = "/", params = "f",method = RequestMethod.POST)
public String f(Locale locale,FormModel fm,Model model) {


return "category(kokugo)";
}
}