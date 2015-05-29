package controller;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import form.UpdateForm;

@Controller
public class UpdateController extends CommonController{
    /**
     * 入力文字をチェック後、問題なければデータを更新し更新成功画面へメッセージを送信
     * @param res 選択したデータの表示用
     */
    @RequestMapping(value="/updateData",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
    public String update(@Valid @ModelAttribute UpdateForm updateForm,BindingResult result,Model model){
        if(isError(result)){
            addAttribute(model, updateForm);
            return "/updateData";
        }else{
            addAttribute(model, updateForm);
            updateData(updateForm);
            return "/successUpdate";
        }
    }
}
