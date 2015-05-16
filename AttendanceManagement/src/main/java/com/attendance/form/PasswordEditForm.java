package com.attendance.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

public class PasswordEditForm implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer studentId;

    @Length(min=8,message="文字数が不足しています")
    private String password; //現在のパスワード

    @Length(min=8,message="文字数が不足しています")
    private String newPassword; //新パスワード

    @Length(min=8,message="文字数が不足しています")
    private String newPasswordConfirm; //新パスワード確認用

    public PasswordEditForm(){
    }

    public PasswordEditForm(Integer studentId){
        this.studentId = studentId;
    }

    public Integer getStudentId(){
        return this.studentId;
    }

    public void setStudentId(Integer studentId){
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }
    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}
