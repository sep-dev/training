package com.example.kenshu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//データベースへの接続やデータ操作を行うクラス
public class DatabaseManager {
    private String [] col;
    private HttpServletRequest request;
    private  HttpServletResponse response;
    private Connection conn;

    public DatabaseManager(){
        col=new String[DatabaseHelper.COL_NUM];
    }

    //データベースと接続
    public Connection datalink(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;
        try {
            request.setCharacterEncoding("utf-8");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DatabaseHelper.URL, DatabaseHelper.USER, DatabaseHelper.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //SQLの実行
    public boolean excute(int command){
        boolean isError=false;
        switch(command){
        //データの追加処理
        case DatabaseHelper.INSERT:
            isError=insertSQL(conn);
            break;
        //データの更新処理
        case DatabaseHelper.UPDATE:
            isError=updateSQL(conn);
            break;
        //データの削除
        case DatabaseHelper.DELETE:
            isError=deleteSQL(conn);
            break;
        }
        return isError;
    }

    //トランザクション処理
    //問題がなければコミットし、問題発生時ロールバックする
    public void transaction(boolean isError){
        if(isError){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //追加処理
    private boolean insertSQL(Connection conn){
        boolean isError=false;
        try{
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            col[DatabaseHelper.ID]=request.getParameter(DatabaseHelper.COL_NAME[DatabaseHelper.ID]);
            //SQLインジェクション対策として特殊文字をエスケープ
            for(int i=DatabaseHelper.INNIT_COL_NO;i<DatabaseHelper.COL_NUM;i++){
                col[i]=SQLEscape.sqlEscape(request.getParameter(DatabaseHelper.COL_NAME[i]));
            }
            String sql="insert into "+ DatabaseHelper.TABLE_NAME+"("+DatabaseHelper.COL_NAME[DatabaseHelper.NAME]
                 +","+DatabaseHelper.COL_NAME[DatabaseHelper.ADDRESS]
                 +","+DatabaseHelper.COL_NAME[DatabaseHelper.TEL]+")"
                 + "values(\""+col[DatabaseHelper.NAME]+"\",\""+col[DatabaseHelper.ADDRESS]+"\",\""+col[DatabaseHelper.TEL]+"\")";
            int num = stmt.executeUpdate(sql);
            isError=false;
            stmt.close();
        }catch(Exception e){
            isError=true;
            e.printStackTrace();
        }
        return isError;
    }

    //更新処理
    private boolean updateSQL(Connection conn){
        boolean isError=false;
        try{
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            col[DatabaseHelper.ID]=request.getParameter(DatabaseHelper.COL_NAME[DatabaseHelper.ID]);
            //SQLインジェクション対策として特殊文字をエスケープ
            for(int i=DatabaseHelper.INNIT_COL_NO;i<DatabaseHelper.COL_NUM;i++){
                col[i]=SQLEscape.sqlEscape(request.getParameter(DatabaseHelper.COL_NAME[i]));
            }
            for(int current_col_No=DatabaseHelper.INNIT_COL_NO;current_col_No<DatabaseHelper.COL_NUM;current_col_No++){
                String sql=null;
                sql="update "+ DatabaseHelper.TABLE_NAME+" set "+ DatabaseHelper.COL_NAME[current_col_No]+"= \""+col[current_col_No]+"\" where id="+request.getParameter("upd")+";";
                int num = stmt.executeUpdate(sql);
            }
            isError=false;
            stmt.close();
        }catch(Exception e){
            isError=true;
            e.printStackTrace();
        }
        return isError;
    }

    //削除処理
    private boolean deleteSQL(Connection conn){
        boolean isError=false;
        try{
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            col[DatabaseHelper.ID]=request.getParameter("del");
            String sql="delete  from tbAddress where id ="+col[DatabaseHelper.ID]+";";
            int num = stmt.executeUpdate(sql);
            isError=false;
            stmt.close();
        }catch(Exception e){
            isError=true;
            e.printStackTrace();
        }
        return isError;
    }
}
