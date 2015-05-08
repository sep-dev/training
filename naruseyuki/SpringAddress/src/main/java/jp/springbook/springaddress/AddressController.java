package jp.springbook.springaddress;

import java.util.List;
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

@Controller
public class AddressController {

	@Autowired
	private JdbcTemplate jdbcTemplate;



	@RequestMapping(value = "/Addressup", method = RequestMethod.GET)
	public String addressup(Model model){

		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM tbaddress");
		model.addAttribute("title","会員登録");
		model.addAttribute("midashi","登録画面");
		FormModel formModel = new FormModel();
		model.addAttribute("formModel",formModel);
		model.addAttribute("kaiinlist",list);
		model.addAttribute("change","2");
		return "showMessage";

	}

	@RequestMapping(value = "/Addressup", method = RequestMethod.POST)
	public String addressupload(@Valid @ModelAttribute FormModel formModel,BindingResult result, Model model){

		model.addAttribute("title","確認画面");

		if(result.hasErrors()){
			model.addAttribute("midashi", "ERROR!フォームの内容を再確認してください");
			model.addAttribute("change","3");
		}else{
			model.addAttribute("midashi","登録しました！");
			model.addAttribute("change","4");
			int MaxId = jdbcTemplate.queryForInt("SELECT MAX(id) FROM tbaddress");
			int InsertId = MaxId +1;
			jdbcTemplate.update(
					"INSERT INTO tbaddress(id,name,address,tel) VALUES(?,?,?,?)",
					InsertId,
					formModel.getName(),
					formModel.getAddress(),
					formModel.getTel()
			);

		}

		return "showMessage";

	}


	@RequestMapping(value = "/Addresslist", method = RequestMethod.GET)
	public String addresslist(Model model){

		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM tbaddress");

		model.addAttribute("title","一覧画面");
		model.addAttribute("midashi","会員リスト");
		model.addAttribute("list1",list);
		model.addAttribute("change","1");

		FormModelRadio formModelRadio = new FormModelRadio();
		formModelRadio.setRadio1(null);
		model.addAttribute("formModelRadio",formModelRadio);
		return "showMessage";

	}

	@RequestMapping(value = "/Addressupdate", method = RequestMethod.POST)
	public String addressupdate(@ModelAttribute FormModelRadio formModelRadio, Model model){

		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM tbaddress WHERE id=?",
				formModelRadio. getRadio1()
		);
		model.addAttribute("title","更新画面");
		model.addAttribute("midashi","会員情報の更新");
		model.addAttribute("id",list.get(0).get("id"));
		model.addAttribute("name",list.get(0).get("name"));
		model.addAttribute("address",list.get(0).get("address"));
		model.addAttribute("tel",list.get(0).get("tel"));

		FormModelUpdate formModelUpdate = new FormModelUpdate();
		model.addAttribute("formModel",formModelUpdate);
		model.addAttribute("change","5");
		return "showMessage";

	}

	@RequestMapping(value = "/Addressupdated", method = RequestMethod.POST)
	public String addressupdated(@ModelAttribute FormModelUpdate formModelUpdate ,Model model){

		model.addAttribute("title","更新画面");
		model.addAttribute("midashi","更新が完了しました！");
		jdbcTemplate.update(
				"UPDATE tbaddress SET name=?, address=?, tel=? WHERE id=?",
				formModelUpdate.getName(),
				formModelUpdate.getAddress(),
				formModelUpdate.getTel(),
				formModelUpdate.getId()
		);
		model.addAttribute("change","6");
		return "showMessage";

	}

	@RequestMapping(value = "/Addressupdated", method = RequestMethod.POST, params = "delete")
	public String addressdelete(@ModelAttribute FormModelUpdate formModelUpdate ,Model model){

		model.addAttribute("title","削除画面");
		model.addAttribute("midashi","削除しました！");
		jdbcTemplate.update(
				"DELETE FROM tbaddress WHERE id=?",
				formModelUpdate.getId()
		);

		model.addAttribute("change","6");
		return "showMessage";

	}



}
