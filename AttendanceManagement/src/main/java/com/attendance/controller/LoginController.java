package com.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.attendance.entity.Teacher;
import com.attendance.form.LoginForm;
import com.attendance.helper.ShareHelper;
import com.attendance.service.LoginService;

@Controller
@RequestMapping(value="/login")
public class LoginController {

    @RequestMapping(value="/")
    public String index(){
        return "index";
    }

    //管理者用ログインフォーム要求
    @RequestMapping(value="/loginManager")
    public String loginManager(Model model){
        model.addAttribute(getLoginForm(ShareHelper.TYPE_MANAGER));
        return "loginManager";
    }

    @RequestMapping(value="/newTeacher")
    public String requestNewTeacher(Model model){
        model.addAttribute(new Teacher());
        return "signUpTeacher";
    }

    //生徒用ログインフォーム要求
    @RequestMapping(value="/loginStudent")
    public String loginStudent(Model model){
        model.addAttribute(getLoginForm(ShareHelper.TYPE_STUDENT));
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
    public String login(@Valid LoginForm loginForm,BindingResult result,RedirectAttributes attribute){
        StringBuilder redirectUrl = new StringBuilder("redirect:"); //リダイレクト先を作成するStringBuilder
        int loginUserType = loginForm.getType();
        String successUrl = loginUserType==ShareHelper.TYPE_MANAGER ? "/manager/top" : "/student/lectureList"; //認証成功時リダイレクト先
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
            break;
        case ShareHelper.TYPE_STUDENT:
            checkResult = loginService.isStudent(loginForm.getId(), hashedPassword);
            break;
        }

        redirectUrl.append(checkResult ? successUrl : notSuccessUrl);
        if(!checkResult) attribute.addFlashAttribute("error", "ユーザが存在しません");

        return redirectUrl.toString();
    }

    @RequestMapping(value="student")
    public String loginStudent(){
        return "";
    }
}
