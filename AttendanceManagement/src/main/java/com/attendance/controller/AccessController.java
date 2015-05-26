package com.attendance.controller;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;

@SessionAttributes(types={AccessUser.class})
public class AccessController {

    //ユーザタイプ
    protected final Integer TYPE_MANAGER = 0; //管理者
    protected final Integer TYPE_STUDENT = 1; //生徒

    protected final String LOGIN_URL_STUDENT = "redirect:/loginStudent"; //生徒ログイン画面
    protected final String LOGIN_URL_MANAGER = "redirect:/loginManager"; //管理者ログイン画面

    protected boolean isPermitUser(AccessUser user,Integer permitUserType){
        if(user == null) return false;
        if(user.getUserId() == null ||user.getUserName().equals("") ||
                user.getUserType() == null || user.getUserType() != permitUserType){
            return false;
        }
        /* ID、氏名、ユーザタイプのどれか一つでも欠落していればfalse。
         * また、格納されているユーザタイプが許可するユーザタイプと異なっていてもfalse
         */
        return true;
    }

    protected void print(String msg){
        System.out.println(msg);
    }

    protected void print(Integer num){
        System.out.println(num.toString());
    }
}
