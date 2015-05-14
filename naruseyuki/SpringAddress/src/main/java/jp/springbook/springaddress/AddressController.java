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
	//JDBCテンプレートを用意
	private JdbcTemplate jdbcTemplate;

	//Viewに会員登録用ページのデータを送信
	@RequestMapping(value = "/Addressbook", method = RequestMethod.GET)
	public String addressbook(Model model){

		model.addAttribute("title","会員登録");
		model.addAttribute("midashi","登録画面");
		FormModel formModel = new FormModel();
		model.addAttribute("formModel",formModel);
		//viewで表示する内容を判定する数値を代入
		model.addAttribute("change","2");
		return "showMessage";

	}

	//Viewで入力したデータを元にエラー判定後、ページ遷移
	@RequestMapping(value = "/Addressbook", method = RequestMethod.POST)
	public String addressbook(@Valid @ModelAttribute FormModel formModel,BindingResult result, Model model){

		model.addAttribute("title","確認画面");
		//入力にエラーがあった場合はifを、そうでなければelseを実行しデータテーブルにデータを追加する
		if(result.hasErrors()){
			model.addAttribute("midashi", "ERROR!フォームの内容を再確認してください");
			//viewで表示する内容を判定する数値を代入
			model.addAttribute("change","2");
		}else{
			model.addAttribute("midashi","登録しました！");
			//viewで表示する内容を判定する数値を代入
			model.addAttribute("change","3");
			//idの最大値をMaxIdに代入
			int MaxId = jdbcTemplate.queryForInt("SELECT MAX(id) FROM tbaddress");
			//MaxIdに1を加算しInsertIdに代入
			int InsertId = MaxId +1;
			//INSERT文を実行しテーブルにデータを追加
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

	//SQL実行後、Viewに結果を送信する
	@RequestMapping(value = "/Addresslist", method = RequestMethod.GET)
	public String addresslist(Model model){

		//SELECT文を実行し結果をlistに格納
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM tbaddress");

		model.addAttribute("title","一覧画面");
		model.addAttribute("midashi","会員リスト");
		//SQL実行結果を送信
		model.addAttribute("list1",list);
		//viewで表示する内容を判定する数値を代入
		model.addAttribute("change","1");

		FormModelRadio formModelRadio = new FormModelRadio();
		model.addAttribute("formModelRadio",formModelRadio);
		return "showMessage";

	}

	//データを選択し更新or削除ボタンを押した場合に実行
	@RequestMapping(value = "/Addressupdate", method = RequestMethod.POST)
	public String addressupdate(@ModelAttribute FormModelRadio formModelRadio, Model model){

		//会員一覧ページで選択したデータをSELECT文でもう一度listに格納する
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT * FROM tbaddress WHERE id=?",
				formModelRadio. getRadio()
		);
		model.addAttribute("title","更新画面");
		model.addAttribute("midashi","会員情報の更新");
		//SELECT文の結果を送信
		model.addAttribute("id",list.get(0).get("id"));
		model.addAttribute("name",list.get(0).get("name"));
		model.addAttribute("address",list.get(0).get("address"));
		model.addAttribute("tel",list.get(0).get("tel"));

		FormModelUpdate formModelUpdate = new FormModelUpdate();
		model.addAttribute("formModelUpdate",formModelUpdate);
		//viewで表示する内容を判定する数値を代入
		model.addAttribute("change","4");
		return "showMessage";

	}

	//更新画面で更新ボタンが押された場合に実行
	@RequestMapping(value = "/Addressupdated", method = RequestMethod.POST, params = "update")
	public String addressupdated(@ModelAttribute FormModelUpdate formModelUpdate ,Model model){

		model.addAttribute("title","更新画面");
		model.addAttribute("midashi","更新が完了しました！");
		//入力したデータを元にUPDATE文でデータを更新
		jdbcTemplate.update(
				"UPDATE tbaddress SET name=?, address=?, tel=? WHERE id=?",
				formModelUpdate.getName(),
				formModelUpdate.getAddress(),
				formModelUpdate.getTel(),
				formModelUpdate.getId()
		);
		//viewで表示する内容を判定する数値を代入
		model.addAttribute("change","3");
		return "showMessage";

	}

	//更新画面で削除ボタンが押された場合に実行
	@RequestMapping(value = "/Addressupdated", method = RequestMethod.POST, params = "delete")
	public String addressdelete(@ModelAttribute FormModelUpdate formModelUpdate ,Model model){

		model.addAttribute("title","削除画面");
		model.addAttribute("midashi","削除しました！");
		//表示されているデータを削除するDELETE文を実行
		jdbcTemplate.update(
				"DELETE FROM tbaddress WHERE id=?",
				formModelUpdate.getId()
		);
		//viewで表示する内容を判定する数値を代入
		model.addAttribute("change","3");
		return "showMessage";

	}

}
