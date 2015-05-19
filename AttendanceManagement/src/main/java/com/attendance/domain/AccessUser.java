package com.attendance.domain;

import java.io.Serializable;

public class AccessUser implements Serializable {

    private Integer userId; //ユーザID
    private String userName; //ユーザ氏名
    private Integer userType; //生徒 or 管理者

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}
