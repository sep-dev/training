package com.attendance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.attendance.domain.AccessUser;
import com.attendance.entity.Student;
import com.attendance.entity.Teacher;
import com.attendance.form.LoginForm;
import com.attendance.helper.ShareHelper;
import com.attendance.service.LoginService;
import com.attendance.service.StudentService;
import com.attendance.service.TeacherService;

@Controller
public class LoginController extends AccessController{

    @Autowired
    private LoginService loginService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    
    @RequestMapping(value="/")
    public String index(Model model,SessionStatus status){
        status.isComplete(); //セッション情報削除
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
    @RequestMapping(value="/loginManager")
    public String loginManager(Model model,SessionStatus status){
        status.setComplete();
        model.addAttribute(new AccessUser());
        model.addAttribute(getLoginForm(TYPE_MANAGER));
        return "loginManager";
    }

    @RequestMapping(value="/newTeacher")
    public String requestNewTeacher(Model model){
        model.addAttribute(new Teacher());
        return "signUpTeacher";
    }

    //生徒用ログインフォーム要求
    @RequestMapping(value="/loginStudent")
    public String loginStudent(Model model,SessionStatus status){
        status.setComplete();
        model.addAttribute(new AccessUser());
        model.addAttribute(getLoginForm(TYPE_STUDENT));
        return "loginStudent";
    }

    //引数にログインユーザ識別番号を渡し、LoginFormオブジェクトを生成して返す
    private LoginForm getLoginForm(int loginUserType){
        LoginForm loginForm = new LoginForm();
        loginForm.setType(loginUserType);
        return loginForm;
    }

   //ログイン処理
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Valid LoginForm loginForm,BindingResult result,RedirectAttributes attribute,AccessUser user){
        StringBuilder redirectUrl = new StringBuilder("redirect:"); //リダイレクト先を作成するStringBuilder
        int loginUserType = loginForm.getType();
        String successUrl = loginUserType==ShareHelper.TYPE_MANAGER ? "/manager/top" : "/student/top"; //認証成功時リダイレクト先
        String notSuccessUrl = loginUserType==ShareHelper.TYPE_MANAGER ? "/loginManager" : "/loginStudent"; //認証失敗時リダイレクト先

        if(result.hasErrors()){
            attribute.addFlashAttribute("error","管理者IDまたはパスワードが違います");
            redirectUrl.append(notSuccessUrl);
            return redirectUrl.toString();
        }

        boolean checkResult = false; //認証結果格納
        String hashedPassword = ShareHelper.hashingMd5(loginForm.getPassword()); //パスワードをMD5にハッシュ化

        switch(loginForm.getType()){
        case ShareHelper.TYPE_MANAGER:
            checkResult = loginService.isManager(loginForm.getId(), hashedPassword);
            if(checkResult){
                Teacher teacher = teacherService.teacherFindByTeacherId(loginForm.getId());
                user.setUserId(teacher.getTeacherId());
                user.setUserName(teacher.getTeacherName());
                user.setUserType(TYPE_MANAGER);
                attribute.addFlashAttribute(user);
            }
            break;
        case ShareHelper.TYPE_STUDENT:
            checkResult = loginService.isStudent(loginForm.getId(), hashedPassword);

            if(checkResult){
                Student student = studentService.studentFindByStudentId(loginForm.getId());
                user.setUserId(student.getStudentId());
                user.setUserName(student.getStudentName());
                user.setUserType(TYPE_STUDENT);
                attribute.addFlashAttribute(user);
            }
            break;
        }

        redirectUrl.append(checkResult ? successUrl : notSuccessUrl);
        if(!checkResult) attribute.addFlashAttribute("error", "ユーザが存在しません");

        return redirectUrl.toString();
    }

    //講師追加
    @RequestMapping(value = "/teacherAdd",method = RequestMethod.POST)
    public String addTeacher(@Valid @ModelAttribute Teacher teacher,BindingResult result,
            @RequestParam("kakuninPassword") String kakuninPassword,RedirectAttributes attribute){
        String successUrl = "redirect:manager/top";
        String notSuccessUrl = "redirect:newTeacher";

        //入力された値のチェックと、パスワード比較
        if(result.hasErrors() || !teacher.getTeacherPassword().equals(kakuninPassword)){
            attribute.addFlashAttribute("error","登録できませんでした");
            return notSuccessUrl;
        }

        //更新処理
        if(loginService.teacherUpdate(teacher)){
            AccessUser user = new AccessUser();
            user.setUserId(teacher.getTeacherId());
            user.setUserName(teacher.getTeacherName());
            user.setUserType(TYPE_MANAGER);
            attribute.addFlashAttribute(user); //変更が完了したら、ログイン済みにする
            return successUrl;
        }else{
            attribute.addFlashAttribute("error", "許可されてないユーザIDです");
            return notSuccessUrl;
        }

    }
}
