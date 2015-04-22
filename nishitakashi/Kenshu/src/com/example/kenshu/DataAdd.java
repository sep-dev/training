package com.example.kenshu;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataAdd
 */

//データをSQL文で挿入するクラス
//成功時SuccessAdd.jspへ遷移
//失敗時FalseAdd.jspへ遷移

public class DataAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseManager dm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataAdd() {
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
    	//データベース接続
    	Connection conn = dm.datalink(request, response);
    	//SQLの実行
        dm.excute(conn, DatabaseHelper.INSERT);

    }


}
