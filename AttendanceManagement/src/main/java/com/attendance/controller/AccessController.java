package com.attendance.controller;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.attendance.domain.AccessUser;

@SessionAttributes(types={AccessUser.class})
public class AccessController {

    //ユーザタイプ
    protected final Integer TYPE_MANAGER = 0; //管理者
    protected final Integer TYPE_STUDENT = 1; //生徒

    private Integer permitType;

    protected final String LOGIN_URL_STUDENT = "redirect:/loginStudent"; //生徒ログイン画面
    protected final String LOGIN_URL_MANAGER = "redirect:/loginManager"; //管理者ログイン画面

    protected void setPermitUserType(Integer permitUserType){
        this.permitType = permitUserType;
    }

    protected boolean isPermitUser(AccessUser user){
        if(user == null) return false;
        if(user.getUserId() == null || user.getUserName().equals("") ||
                user.getUserType() == null || user.getUserType() != this.permitType){
            return false;
        }
        /* ID、氏名、ユーザタイプのどれか一つでも欠落していればfalse。
         * また、格納されているユーザタイプが許可するユーザタイプと異なっていてもfalse
         */
        return true;
    }

    protected boolean isPermitUser(AccessUser user,Integer permitUserType){
        this.permitType = permitUserType;
        return isPermitUser(user);
    }


    protected void print(String msg){
        System.out.println(msg);
    }

    protected void print(Integer num){
        System.out.println(num.toString());
    }
}
