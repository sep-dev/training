package com.example.kenshu;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataDelete
 */

//データをSQL文で削除するクラス
//成功時SuccessDel.jspへ遷移
//失敗時FalseDel.jspへ遷移

public class DataDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseManager dm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataDelete() {
        super();
        dm=new DatabaseManager();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int command=DatabaseHelper.DELETE;
        //データベース接続
        Connection conn = dm.datalink(request, response);
        //SQLの実行
        //実行の正否判定
        boolean isError=dm.excute(command);
        //トランザクション処理
        dm.transaction(isError);
        if(isError){
            //エラー時は登録失敗画面へ遷移
            RequestDispatcher disp = request.getRequestDispatcher("jsp/FalseDel.jsp");
            disp.forward(request, response);
        }else{
            //問題なければ登録成功画面へ遷移
            RequestDispatcher disp = request.getRequestDispatcher("jsp/SuccessDel.jsp");
            disp.forward(request, response);
        }
    }
}
