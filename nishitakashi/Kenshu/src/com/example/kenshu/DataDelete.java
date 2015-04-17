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
 * Servlet implementation class DataDelete
 */

//データをSQL文で削除するクラス
//成功時SuccessDel.jspへ遷移
//失敗時FalseDel.jspへ遷移

public class DataDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataDelete() {
        super();
        // TODO Auto-generated constructor stub
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
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = "jdbc:mysql://localhost:3306/db_customer";
	    String user = "root";
	    String password = "takashi3541";
	    String sql="delete  from tbAddress where id ="+Integer.parseInt(request.getParameter("del"));

	    PrintWriter out = response.getWriter();
	    out.println(request.getParameter("adr"));
		try {
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		  conn = DriverManager.getConnection(url, user, password);

		  stmt = conn.createStatement();
		  stmt.executeUpdate(sql);

	      stmt.close();
	    // 読み込み成功　JSPに遷移
	      RequestDispatcher disp = request.getRequestDispatcher("jsp/SuccessDel.jsp");
	      disp.forward(request, response);
		} catch(Exception e) {
			// 読み込み失敗　JSPに遷移
			  try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		      RequestDispatcher disp = request.getRequestDispatcher("jsp/FalseDel.jsp");
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
