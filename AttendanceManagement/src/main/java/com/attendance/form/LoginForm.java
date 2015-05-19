package com.attendance.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

//ログイン画面用フォームクラス
public class LoginForm {

    @NotNull(message="不適切な文字が入力されています")
    private Integer id;

    @NotEmpty
    @Length(min=8,message="半角英数8文字以上で入力してください")
    private String password;

    /*
     * ログインするのが管理者なのか、生徒なのかを識別する
     * 0 : 管理者
     * 1 : 生徒
    */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
