package com.attendance.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class TeacherAddForm implements Serializable {

    @NotNull(message="入力してください")

    private Integer teacherId; //講師ID

    @NotEmpty(message="未入力です。文字を入力してください")
    private String teacherName; //講師氏名

    @NotEmpty(message="未入力です。文字を入力してください")
    private String teacherAddress; //講師住所

    @Pattern(regexp="^[0-9]{10,11}$",message="半角数字10文字か11文字で入力してください")
    private String teacherTel; //講師電話番号

    @NotEmpty(message="入力してください")
    @Length(min=8,message="半角英数8文字以上で入力してください")
    private String password; //パスワード

    @NotEmpty
    @Length(min=8,message="半角英数8文字以上で入力してください")
    private String passwordConfirm; //確認パスワード

    public Integer getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getTeacherAddress() {
        return teacherAddress;
    }
    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }
    public String getTeacherTel() {
        return teacherTel;
    }
    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }


}
