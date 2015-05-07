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
import form.DeleteForm;

@Controller
public class DelController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MyDao dao;
    @Autowired
    private UserParameter user;

    /**
     * 削除確認画面へメッセージの送信
     * @param res 選択したデータの表示用
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
    public String init_delete(Model model) {
        DeleteForm deleteForm=new DeleteForm();
        String res="氏名："+user.getName()+"　住所："+user.getAddress()+"　電話番号："+user.getTel();
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
        dao.delete(user);
        model.addAttribute("title","削除成功");
        model.addAttribute("message","削除しました！");
        return "/successDel";
    }
}
