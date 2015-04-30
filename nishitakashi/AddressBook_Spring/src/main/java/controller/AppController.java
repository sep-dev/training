package controller;

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

import dao.MyDao;
import dao.MyParameter;
import form.DeleteForm;
import form.InsertForm;
import form.SelectForm;
import form.UpdateForm;

/**
 * 住所録のコントローラ
 */
@Controller
public class AppController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MyDao dao;

    /**
     * 新規登録(初期画面）
     * @param jt jdbcTemplateでデータベースの準備用
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
    public String init(Model model) {
        model.addAttribute("title","会員登録");
        model.addAttribute("message", "新規登録の方は記入してください");
        model.addAttribute("insertForm",new InsertForm());
        JdbcTemplate jt=jdbcTemplate;
        dao.setJDBCTemplate(jt);
        return "/home";
    }
    /**
     * 入力文字をチェック後、問題なければ登録成功画面への入力フォーム送信
     */
    @RequestMapping(value="/",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public String add(@Valid @ModelAttribute InsertForm insertForm,BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("title", "[ERROR]");
            model.addAttribute("message", "値を再度入力してください");
            return "/home";
        }else{
            dao.insert(insertForm);
            model.addAttribute("title","登録成功！");
            model.addAttribute("insertForm",insertForm);
            return "/successView";
        }
    }

    /**
     * 全会員データを一覧表示画面へ送信(前画面でリクエストがある場合)
     * @param list データを格納したリスト
     */
    @RequestMapping(value = "/successView", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
    public String success(@Valid @ModelAttribute InsertForm insertForm,BindingResult result,Model model) {
        SelectForm selectForm=new SelectForm();
        model.addAttribute("title","会員選択");
        model.addAttribute("message", "一つだけ選択");
        model.addAttribute("selectForm",selectForm);
        List<Map<String, Object>> list = dao.selectAll();
        model.addAttribute("data", list);
        return "/showList";
    }

    /**
     * 全会員データを一覧表示画面へ送信(前画面でリクエストが無い場合)
     * @param list データを格納したリスト
     */
    @RequestMapping(value = "/showList", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
    public String init_showList(Model model) {
        SelectForm selectForm=new SelectForm();
        model.addAttribute("title","会員選択");
        model.addAttribute("message", "一つだけ選択");
        model.addAttribute("selectForm",selectForm);
        List<Map<String, Object>> list = dao.selectAll();
        model.addAttribute("data", list);
        return "/showList";
    }

    /**
     * 全会員データから一件選択し、そのデータを更新・削除画面へ送信
     * @param list データを格納したリスト
     * @param res 選択したデータの表示用
     */
    @RequestMapping(value="/showList",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public String selectList(@Valid @ModelAttribute SelectForm selectForm,BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("title", "[ERROR]");
            model.addAttribute("message", "一つ選んでください");
            List<Map<String, Object>> list = dao.selectAll();
            model.addAttribute("data", list);
            model.addAttribute("selectForm",selectForm);
            return "/showList";
        }else{
            UpdateForm updateForm=new UpdateForm();
            updateForm.setRadio1(MyParameter.id);
            dao.selectRadio(selectForm);
            String res="氏名："+MyParameter.name+"　住所："+MyParameter.address+"　電話番号："+MyParameter.tel;
            model.addAttribute("updateForm",updateForm);
            model.addAttribute("title","更新");
            model.addAttribute("selectForm",selectForm);
            model.addAttribute("message",res);
            return "/updateData";
        }
    }

    /**
     * 入力文字をチェック後、問題なければデータを更新し更新成功画面へメッセージを送信
     * @param res 選択したデータの表示用
     */
    @RequestMapping(value="/updateData",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public String update(@Valid @ModelAttribute UpdateForm updateForm,BindingResult result,Model model){
        updateForm.setRadio1(MyParameter.id);
        String res="氏名："+MyParameter.name+"　住所："+MyParameter.address+"　電話番号："+MyParameter.tel;
        if(result.hasErrors()){
            model.addAttribute("title", "[ERROR]");
            model.addAttribute("message", "値を再度入力してください");
            model.addAttribute("updateForm",updateForm);
            return "/updateData";
        }else{
            dao.update(updateForm,MyParameter.id);
            model.addAttribute("updateForm",updateForm);
            model.addAttribute("title","更新成功！");
            model.addAttribute("message","更新が成功しました！");
            return "/successUpdate";
        }
    }

    /**
     * 削除確認画面へメッセージの送信
     * @param res 選択したデータの表示用
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
    public String init_delete(Model model) {
        DeleteForm deleteForm=new DeleteForm();
        String res="氏名："+MyParameter.name+"　住所："+MyParameter.address+"　電話番号："+MyParameter.tel;
        model.addAttribute("title","削除");
        model.addAttribute("message",res);
        model.addAttribute("confirm","本当に削除しますか？");
        model.addAttribute("DeleteForm",deleteForm);
        return "/delete";
    }

    /**
     * 選択IDをもとにデータを削除。メッセージを削除成功画面へ送信
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public String delete(@Valid @ModelAttribute DeleteForm deleteForm,BindingResult result,Model model){
        model.addAttribute("title","削除成功");
        model.addAttribute("message","削除しました！");
        dao.delete(MyParameter.id);
        return "/successDel";
    }
}
