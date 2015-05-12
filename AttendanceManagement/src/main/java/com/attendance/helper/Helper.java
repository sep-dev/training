package com.attendance.helper;

import org.springframework.util.DigestUtils;

public class Helper {

    //ログインユーザタイプ
    public static final int TYPE_MANAGER = 0; //管理者
    public static final int TYPE_STUDENT = 1; //生徒

    //引数の文字列をMD5でハッシュ化する
    public static String hashingMd5(String target){
        return DigestUtils.md5DigestAsHex(target.getBytes());
    }
}
