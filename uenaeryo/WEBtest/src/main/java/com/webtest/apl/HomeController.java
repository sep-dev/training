package com.webtest.apl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		FormModel fm = new FormModel();

		fm.setuser("");
		fm.setpass("");

		model.addAttribute("formModel", fm);

		return "home";
	}

	/*----------------------------------ログインテーブル：tblLoginChild ----------------------------------*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home(FormModel form, BindingResult result, Model model) {

		try {
			Object user = form.getUser();
			Object pass = form.getPass();
			// true=真 false=偽
			boolean userflg = false, passflg = false;

			List<Map<String, Object>> gettable = jdbcTemplate
					.queryForList("select * from tblLoginChild where loginChild = '"
							+ user + "' ");

			Object sqluser = gettable.get(0).get("loginChild");
			Object sqlpass = gettable.get(0).get("loginChildPass");

			// ログイン判別IF
			if (user.equals(sqluser)) {
				// あたり
				userflg = true;
				if (pass.equals(sqlpass)) {
					// あたり
					passflg = true;
				} else {
					passflg = false;
				}
			} else {
				passflg = false;
			}

			if (userflg == true && passflg == true) {
				return "select";

			} else {
			}

		} catch (Exception e) {

		}

		model.addAttribute("message",
				"<font size=5 color='red'>ログインできませんでした</font>");

		return "home";

	}

	/*------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------問題選択ページ-----------------------------------------*/
	/*------------------------------------------------------------------------------------------------*/

	/*-----------------------------------------国語問題ページ-----------------------------------------*/
	@RequestMapping(value = "/", params = "Japanese", method = RequestMethod.POST)
	public String japbtn(@ModelAttribute FormModel form, Model model) {

		// 教科ＩＤ　国語=501
		model.addAttribute("caid", "501");
		model.addAttribute("btn1", "四字熟語");
		model.addAttribute("btn2", "漢字(読み仮名)");
		model.addAttribute("btn3", "俳句");

		model.addAttribute("name1", "Zyukugo");
		model.addAttribute("name2", "Kanzi");
		model.addAttribute("name3", "Haiku");

		return "Select2";
	}

	/*-----------------------------------------算数問題ページ-----------------------------------------*/
	@RequestMapping(value = "/", params = "Arithmetic", method = RequestMethod.POST)
	public String Aritbtn(@ModelAttribute FormModel form, Model model) {

		// 教科ＩＤ　算数=502
		model.addAttribute("caid", "502");
		model.addAttribute("btn1", "算数１");
		model.addAttribute("btn2", "算数２");
		model.addAttribute("btn3", "算数３");

		return "Select2";
	}

	/*-----------------------------------------問題ページ----------------------------------*/
	@RequestMapping(value = "/", params = "Zyukugo", method = RequestMethod.POST)
	public String zyukugobtn(@ModelAttribute FormModel form, Model model) {

		String problem = "", title = "", qst = "", id = "", tag = "", tag2 = "", radio = "";
		String pic;
		int i = 1;

		List<Map<String, Object>> photo = jdbcTemplate
				.queryForList("select * from tblquestionimage where questionid = 1013");
		pic = photo.get(0).get("imagedata").toString();

		// 問題文表示
		List<Map<String, Object>> gettable = jdbcTemplate
				.queryForList("select * from tblQuestion ");
		/* テーブル内容表示 */
		for (Map<String, Object> map : gettable) {
			title = title + "<td width='300'>問題" + i + "</td>";
			problem = problem + "<td>" + map.get("questionText").toString()
					+ "</td>";
			id = map.get("questionid").toString();
			qst = "";

			// 解答文表示
			tag = "<td>";
			List<Map<String, Object>> gettable2 = jdbcTemplate
					.queryForList("select * from tblQuestionDetail where questionId="
							+ id + " ");
			for (Map<String, Object> map2 : gettable2) {

				radio = "<input type='radio' name='qst" + id + "' value="
						+ map2.get("questionIdSub").toString()
						+ " checked= 'checked'>";
				qst = qst + radio + map2.get("questionDetail").toString()
						+ "<br>";
			}
			tag2 = tag2 + tag + qst + "</td>";

			i = i + 1;

		}

		// タイトル
		model.addAttribute("title", title);

		// 問題
		model.addAttribute("problem", problem);

		// 解答
		model.addAttribute("qst", tag2);

		model.addAttribute("pic", photo);

		return "Problem";
	}

	/*-----------------------------------------画像アップロードページ-----------------------------------------*/

	@RequestMapping(value = "/", params = "upbtn", method = RequestMethod.POST)
	public String readFileToByte(String filePath, Model model) throws Exception {

		byte[] b = new byte[16384];
		int readByte = 0;
		int id = 2006;
		String datain = "C:/test/neko.png";
		String dataout = "C:/test/bynary.txt";

		// 画像元
		FileInputStream dataInStream = new FileInputStream(datain);
		File fil = new File(dataout);
		 FileOutputStream dataOutStream = new FileOutputStream(fil);
		while ((readByte = dataInStream.read(b)) != -1) {
			// .jpg出力
			dataOutStream.write(b, 0, readByte);
		}


		File x = new File(dataout);
/*
		jdbcTemplate.update("insert into tblquestionimage(questionId,imageName,imageData)value('"+id+"','title','')");
		jdbcTemplate.update("update tblquestionimage set imageData=load_file('"+dataout+"') where questionId = '"+id+"' ");

*/
		dataInStream.close();
		dataOutStream.close();

		return "upload";
	}

	@RequestMapping(value = "/", params = "upbtn2", method = RequestMethod.POST)
	public String readFileeToByte(String filePath, Model model)
			throws Exception {


		byte[] b = new byte[256];
		int readByte = 0;

		List<Map<String, Object>> photo = jdbcTemplate
				.queryForList("select * from tblquestionimage where questionid = 2006");


		String str = photo.get(0).get("imagedata").toString();
		InputStream fis = new ByteArrayInputStream(str.getBytes());


//		String pic = photo.get(0).get("imagedata").toString();
//		byte[] i = pic.getBytes();
//		InputStream x = getBinaryStream();
		String outputFileName = "c:/test/test.jpg";
		File outputFile = new File(outputFileName);
		FileOutputStream fos = new FileOutputStream(outputFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		PrintWriter pw = new PrintWriter(osw);

		pw.println(fis);

		pw.close();


/*

		FileOutputStream out = new FileOutputStream("C:/test/bynary.txt");

		while(rs.next()){
			  FileOutputStream fs = new FileOutputStream(file);
			  fs.write(photo.getBlob(0).getBytes("imagedata"));
			 }*/

/*		InputStream input = new InputStream(pic);
		FileOutputStream output = new FileOutputStream("C:/test/testimage2.jpg");

		int len;
		 while((len=input.read(b))!=-1){
		   output.write(b,0,len);
		 }

		 output.flush();
		 output.close();
		input.close();*/


		model.addAttribute("message", "ああああああああああ");

		return "upload";
	}


}
