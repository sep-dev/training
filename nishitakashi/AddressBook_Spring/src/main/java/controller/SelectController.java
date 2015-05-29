package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import form.InsertForm;
import form.SelectForm;
import form.UpdateForm;

@Controller
public class SelectController extends CommonController{
    /**
     * 全会員データを一覧表示画面へ送信(前画面でリクエストがある場合)
     * @param list データを格納したリスト
     */
    @RequestMapping(value = "/successView", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
    public String success(@Valid @ModelAttribute InsertForm insertForm,BindingResult result,Model model) {
        createListAll();
        addAttribute(model,insertForm);
        return "/showList";
    }

    /**
     * 全会員データを一覧表示画面へ送信(前画面でリクエストが無い場合)
     * @param list データを格納したリスト
     */
    @RequestMapping(value = "/showList", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
    public String init_showList(Model model) {
        SelectForm selectForm=new SelectForm();
        createListAll();
        addAttribute(model,selectForm);
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
        	createListAll();
            addAttribute(model,selectForm);
            return "/showList";
        }else{
            UpdateForm updateForm=new UpdateForm();
            selectData(selectForm);
            addAttribute(model,updateForm);
            return "/updateData";
        }
    }
}
