package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import dao.MyDao;
import dao.UserParameter;
import form.Form;
/**
 * コントローラで使用するメソッド
 * @param res 選択したデータの表示用
 */
public class CommonController {
    List<Map<String, Object>> list;
    @Autowired
    private UserParameter user;
    @Autowired
    private MyDao dao;

    public void addAttribute(Model model,Form form){
         String res="氏名："+user.getName()+"　住所："+user.getAddress()+"　電話番号："+user.getTel();
         model.addAttribute("form",form);
         model.addAttribute("selectData", res);
         model.addAttribute("data", list);
    }

    public boolean isError(Errors result){
        if(result.hasErrors()){
            return true;
        }
        return false;
    }

    public void createListAll(){
        list = dao.selectAll();
    }

    public void selectData(Form form){
        dao.selectRadio(form,user);
    }

    public void deleteData(){
        dao.delete(user);
    }

    public void insertData(Form form){
        dao.insert(form);
    }

    public void updateData(Form form){
        form.setRadio1(user.getId());
        dao.update(form,user);
    }
}
