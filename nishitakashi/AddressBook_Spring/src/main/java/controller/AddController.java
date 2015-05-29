package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import form.InsertForm;

/**
 * 住所録のコントローラ
 */
@Controller
public class AddController extends CommonController{
    /**
     * 新規登録(初期画面）
     * @param jt jdbcTemplateでデータベースの準備用
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
    public String init(Model model) {
        addAttribute(model, new InsertForm());
        return "/home";
    }
    /**
     * 入力文字をチェック後、問題なければ登録成功画面への入力フォーム送信
     */
    @RequestMapping(value="/",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public String add(@Valid @ModelAttribute InsertForm insertForm,BindingResult result,Model model){
        if(isError(result)){
            return "/home";
        }else{
            insertData(insertForm);
            addAttribute(model, insertForm);
            return "/successView";
        }
    }
}
