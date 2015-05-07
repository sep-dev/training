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
import form.UpdateForm;

@Controller
public class UpdateController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MyDao dao;
    @Autowired
    private UserParameter user;

    /**
     * 入力文字をチェック後、問題なければデータを更新し更新成功画面へメッセージを送信
     * @param res 選択したデータの表示用
     */
    @RequestMapping(value="/updateData",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public String update(@Valid @ModelAttribute UpdateForm updateForm,BindingResult result,Model model){
        updateForm.setRadio1(user.getId());
        String res="氏名："+user.getName()+"　住所："+user.getAddress()+"　電話番号："+user.getTel();
        if(result.hasErrors()){
            model.addAttribute("title", "[ERROR]");
            model.addAttribute("message", "値を再度入力してください");
            model.addAttribute("updateForm",updateForm);
            return "/updateData";
        }else{
            dao.update(updateForm,user);
            model.addAttribute("updateForm",updateForm);
            model.addAttribute("title","更新成功！");
            model.addAttribute("message","更新が成功しました！");
            return "/successUpdate";
        }
    }
}
