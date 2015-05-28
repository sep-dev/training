package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import form.DeleteForm;

@Controller
public class DelController extends CommonController{
    /**
     * 削除確認画面へメッセージの送信
     * @param res 選択したデータの表示用
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
    public String init_delete(Model model) {
        addAttribute(model,new DeleteForm());
        return "/delete";
    }

    /**
     * 選択IDをもとにデータを削除。メッセージを削除成功画面へ送信
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public String delete(@Valid @ModelAttribute DeleteForm deleteForm,BindingResult result,Model model){
        deleteData();
        return "/successDel";
    }
}
