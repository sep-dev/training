package com.attendance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.attendance.domain.AccessUser;
import com.attendance.entity.Student;
import com.attendance.entity.Teacher;
import com.attendance.form.LoginForm;
import com.attendance.form.TeacherAddForm;
import com.attendance.helper.ShareHelper;
import com.attendance.service.LoginService;
import com.attendance.service.StudentService;
import com.attendance.service.TeacherService;
import com.attendance.validator.TeacherPasswordValidator;

@Controller
public class LoginController extends AccessController{

    @Autowired
    private LoginService loginService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherPasswordValidator teacherValidator;

    @RequestMapping(value="/")
    public String index(Model model,SessionStatus status){
        status.setComplete(); //セッション情報削除
        return "index";
    }

    //ログアウト処理
    @RequestMapping(value="/logout")
    public String logout(AccessUser user){
        if(user.getUserType() == TYPE_MANAGER){
            return LOGIN_URL_MANAGER;
        }else{
            return LOGIN_URL_STUDENT;
        }
    }

    //管理者用ログインフォーム要求
    @RequestMapping(value={"/loginManager","/loginStudent"})
    public String loginManager(HttpServletRequest request,Model model,SessionStatus status){
        status.setComplete();
        model.addAttribute(new AccessUser());
        LoginForm loginForm = new LoginForm();
        if(request.getRequestURI().indexOf("loginManager") != -1){
            loginForm.setType(TYPE_MANAGER);
            model.addAttribute(loginForm);
            return "loginManager";
        }else{
            loginForm.setType(TYPE_STUDENT);
            model.addAttribute(loginForm);
            return "loginStudent";
        }
    }

    //管理者ログインページ内の「新規の方はこちら」リンク
    @RequestMapping(value="/newTeacher")
    public String requestNewTeacher(Model model){
        model.addAttribute(new TeacherAddForm());
        return "signUpTeacher";
    }

    //ログイン処理
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Valid LoginForm loginForm,BindingResult result,AccessUser user,Model model){

        Integer userType = loginForm.getType(); //ログインユーザタイプ取得

        if(result.hasErrors()){
            model.addAttribute("error","入力されたIDまたはパスワードが違います");
            return userType==TYPE_MANAGER ? "loginManager" : "loginStudent";
        }

        boolean checkResult = false; //認証結果格納
        String hashedPassword = ShareHelper.hashingMd5(loginForm.getPassword()); //パスワードをMD5にハッシュ化

        switch(userType){
        case 0:
            checkResult = loginService.isManager(loginForm.getId(), hashedPassword);
            if(checkResult){
                Teacher teacher = teacherService.teacherFindByTeacherId(loginForm.getId());
                user.setUserId(teacher.getTeacherId());
                user.setUserName(teacher.getTeacherName());
                user.setUserType(TYPE_MANAGER);
                model.addAttribute(user);
            }
            break;
        case 1:
            checkResult = loginService.isStudent(loginForm.getId(), hashedPassword);
            if(checkResult){
                Student student = studentService.studentFindByStudentId(loginForm.getId());
                user.setUserId(student.getStudentId());
                user.setUserName(student.getStudentName());
                user.setUserType(TYPE_STUDENT);
                model.addAttribute(user);
            }
            break;
        }

        if(!checkResult){
            model.addAttribute("error", "ユーザが存在しません");
            return userType==TYPE_MANAGER ? "loginManager" : "loginStudent";
        }

        return userType==TYPE_MANAGER ? "redirect:/manager/top" : "redirect:/student/top";
    }

    @InitBinder("teacherAddForm")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(this.teacherValidator); //パスワード確認用バリデータを追加
    }

    //講師追加
    @RequestMapping(value = "/teacherAdd",method = RequestMethod.POST)
    public String addTeacher(@Valid TeacherAddForm form,BindingResult result,Model model){
        if(result.hasErrors()) return "signUpTeacher";

        Teacher teacher = teacherService.updateTeacher(form);
        if(teacher != null){
            AccessUser user = new AccessUser();
            user.setUserId(teacher.getTeacherId());
            user.setUserName(teacher.getTeacherName());
            user.setUserType(TYPE_MANAGER);
            model.addAttribute(user);//変更が完了したら、ログイン済みにする
            return "redirect:manager/top";
        }
        return "signUpTeacher"; //更新に成功しなければ、リクエスト元を表示
    }

}
