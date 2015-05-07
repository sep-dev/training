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
import dao.UserParameter;
import form.InsertForm;
import form.SelectForm;
import form.UpdateForm;

@Controller
public class SelectController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MyDao dao;
    @Autowired
    private UserParameter user;

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
            updateForm.setRadio1(user.getId());
            dao.selectRadio(selectForm,user);
            String res="氏名："+user.getName()+"　住所："+user.getAddress()+"　電話番号："+user.getTel();
            model.addAttribute("updateForm",updateForm);
            model.addAttribute("title","更新");
            model.addAttribute("selectForm",selectForm);
            model.addAttribute("message",res);
            return "/updateData";
        }
    }
}
