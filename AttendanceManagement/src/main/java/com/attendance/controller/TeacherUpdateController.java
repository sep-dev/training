package com.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;
import com.attendance.editor.ClassPropertyEditor;
import com.attendance.entity.Clas;
import com.attendance.entity.Teacher;
import com.attendance.repository.ClassRepository;
import com.attendance.repository.TeacherRepository;
import com.attendance.service.PasswordManager;
/**
 * 講師更新のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class TeacherUpdateController extends AccessController{
    @Autowired
    private ClassRepository class_repository;
    @Autowired
    private TeacherRepository repository;
    @Autowired
    private PasswordManager pm;

    @RequestMapping(value = "/teacherUpdate", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String entry(HttpServletRequest request, Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        int id = Integer.parseInt(request.getParameter("id"));
        // 既存のパスワードのデータを検索し、保存
        pm = new PasswordManager();
        Teacher teacher = repository.findOne(id);
        pm.setForwardHash(teacher.getTeacherPassword());
        model.addAttribute("teacher", teacher);
        createList(model);
        return "/teacherUpdate";
    }

    @RequestMapping(value = "/teacherUpdate", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String updateData(@Valid @ModelAttribute Teacher data,HttpServletRequest request, Errors result,
            Model model,AccessUser user) {
    	/*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*文字チェック後問題なければ更新*/
        if (isError(request, result, data, model)){
        	createList(model);
        	return "/teacherUpdate";
        } else {
            data.setTeacherPassword(pm.hashCreate(data.getTeacherPassword()));
            repository.saveAndFlush(data);
            List<Teacher> list = repository.findAll();
            model.addAttribute("datalist", list);
            return "/teacherList";
        }
    }

    /*型変換用*/
    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Clas.class, new ClassPropertyEditor(
                class_repository));
    }

    /*検索用リストの生成*/
    private void createList(Model model){
    	 List<Clas> class_list = class_repository.findAll();
         model.addAttribute("selectClass", class_list);
    }

    /*入力文字チェック*/
    private boolean isError(HttpServletRequest request,Errors result,Teacher data,Model model){
    	if (result.hasErrors()) {
            model.addAttribute("message", "エラーが発生しました");
            return true;
        }else if(!(request.getParameter("passwordConfirm").equals(data.getTeacherPassword()))
                &&!(pm.hashCreate(request.getParameter("passwordConfirm")).equals(repository.findByTeacherId(data.getTeacherId()).getTeacherPassword()))){
            model.addAttribute("message", "入力パスワードが異なっています");
            return true;
        } else {
            return false;
        }
    }
}
