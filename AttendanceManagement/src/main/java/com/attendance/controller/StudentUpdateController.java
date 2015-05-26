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
import com.attendance.entity.Student;
import com.attendance.repository.ClassRepository;
import com.attendance.repository.StudentRepository;
import com.attendance.service.PasswordManager;
/**
 * 生徒更新のコントローラ
 */
@Controller
@SessionAttributes("accessUser")
@RequestMapping(value = "/manager")
public class StudentUpdateController extends AccessController{
    @Autowired
    private ClassRepository class_repository;
    @Autowired
    private StudentRepository repository;
    @Autowired
    private PasswordManager pm;

    @RequestMapping(value = "/studentUpdate", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String entry(HttpServletRequest request, Model model,AccessUser user) {
        /*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        int id = Integer.parseInt(request.getParameter("id"));
        // 既存のパスワードのデータを検索し、保存
        pm = new PasswordManager();
        Student student = repository.findOne(id);
        pm.setForwardHash(student.getStudentPassword());
        List<Clas> class_list = class_repository.findAll();
        model.addAttribute("selectClass", class_list);
        model.addAttribute("id", student.getClas().getClassId());
        model.addAttribute("student", student);

        return "/studentUpdate";
    }

    @RequestMapping(value = "/studentUpdate", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String updateData(@Valid @ModelAttribute Student data,HttpServletRequest request, Errors result,
            Model model,AccessUser user) {
        /*管理者かどうかの判定*/
        if(!isPermitUser(user, TYPE_MANAGER)) return LOGIN_URL_MANAGER;
        /*文字チェック後問題なければ登録*/
        if (isError(request, result, data, model)){
            createList(model);
            return "/studentUpdate";
        } else {
            data.setStudentPassword(pm.hashCreate(data.getStudentPassword()));
            repository.saveAndFlush(data);
            List<Student> list = repository.findAll();
            model.addAttribute("datalist", list);
            return "/studentList";
        }
    }

    /*型変換用*/
    @InitBinder
    protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Clas.class, new ClassPropertyEditor(class_repository));
    }

    /*検索用リストの生成*/
    private void createList(Model model){
        List<Clas> class_list = class_repository.findAll();
        model.addAttribute("selectClass", class_list);
    }

    /*入力文字チェック*/
    private boolean isError(HttpServletRequest request,Errors result,Student data,Model model){
        if (result.hasErrors()) {
            model.addAttribute("message", "エラーが発生しました");
            return true;
        }else if(!(request.getParameter("passwordConfirm").equals(data.getStudentPassword()))
                &&!(pm.hashCreate(request.getParameter("passwordConfirm")).equals(repository.findByStudentId(data.getStudentId()).getStudentPassword()))){
            model.addAttribute("message", "入力パスワードが異なっています");
            createList(model);
            return true;
        } else {
            return false;
        }
    }
}
