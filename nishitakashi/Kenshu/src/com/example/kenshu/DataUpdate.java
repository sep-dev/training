package com.example.kenshu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataUpdate
 */

//データをSQL文で更新するクラス
//成功時SuccessUpd.jspへ遷移
//失敗時FalseUpd.jspへ遷移

public class DataUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String [] col;
	private DatabaseManager dm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataUpdate() {
        super();
        col=new String[DatabaseHelper.COL_NUM];
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//DatabaseManagementへ移動予定
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/db_customer";
	    String user = "root";
	    String password = "takashi3541";

	    String sql1="update  tbAddress set name= \""+col[DatabaseHelper.NAME]+"\" where id="+Integer.parseInt(request.getParameter("upd"));
	    String sql2="update  tbAddress set address=\""+ col[DatabaseHelper.ADDRESS]+"\" where id="+Integer.parseInt(request.getParameter("upd"));
	    String sql3="update  tbAddress set tel=\""+col[DatabaseHelper.TEL]+"\" where id="+Integer.parseInt(request.getParameter("upd"));


	    PrintWriter out = response.getWriter();
		try {
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		  conn = DriverManager.getConnection(url, user, password);

		  stmt = conn.createStatement();
		  stmt.executeUpdate(sql1);
		  stmt.executeUpdate(sql2);
		  stmt.executeUpdate(sql3);

	      stmt.close();
	    // 読み込み成功　JSPに遷移
	      RequestDispatcher disp = request.getRequestDispatcher("jsp/SuccessUpdate.jsp");
	      disp.forward(request, response);
		} catch(Exception e) {
			// 読み込み失敗　JSPに遷移
		      RequestDispatcher disp = request.getRequestDispatcher("jsp/FalseUpdate.jsp");
		      disp.forward(request, response);
		      e.printStackTrace();
		}finally{
		      try{
		        if (conn != null){
		          conn.close();
		        }
		      }catch (SQLException e){
		        out.println("SQLException:" + e.getMessage());
		      }
		}
	}

}
