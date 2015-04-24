package com.example.kenshu;

//データベースで使用するクラスの補助クラス
public class DatabaseHelper {
    //データベース情報
    public static final String DB_NAME="db_customer";
    public static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    public static final String USER = "root";
    public static final String PASSWORD = "takashi3541";

    //テーブル情報
    public static final String TABLE_NAME="tbAddress";
    public static final String [] COL_NAME={"id","name","address","tel"};
    public static final int ID=0;
    public static final int NAME=1;
    public static final int ADDRESS=2;
    public static final int TEL=3;
    public static final int COL_NUM=COL_NAME.length;
    public static final int INNIT_COL_NO = NAME;//どのカラムから処理していくか（今回はidがオートインクリメントなのでnameから）

    //顧客情報検索用
    public static final int MAX_CUSTOMER_NUM=100;
    public static String [][] customer_current=new String[COL_NUM][MAX_CUSTOMER_NUM];

    //SQLコマンド
    public static final int COMMAND_NUM=4;
    public static final int INSERT=0;
    public static final int UPDATE=1;
    public static final int DELETE=2;
    public static final int SELECT=3;
}
