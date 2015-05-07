package controller;

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

/**
 * 住所録のコントローラ
 */
@Controller
public class AddController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MyDao dao;
    @Autowired
    private UserParameter user;
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
}
