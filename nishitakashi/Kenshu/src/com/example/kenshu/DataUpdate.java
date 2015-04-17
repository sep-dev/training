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

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataUpdate() {
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
		String url = "jdbc:mysql://localhost:3306/db_customer";
	    String user = "root";
	    String password = "takashi3541";
	    String name=request.getParameter("name");
	    String adress=request.getParameter("adr");
	    String tel=request.getParameter("tel");
	    //SQLインジェクション対策として特殊文字をエスケープ
	    name=SQLEscape.sqlEscape(name);
	    adress=SQLEscape.sqlEscape(adress);
	    tel=SQLEscape.sqlEscape(tel);
	    String sql1="update  tbAddress set name= \""+name+"\" where id="+Integer.parseInt(request.getParameter("upd"));
	    String sql2="update  tbAddress set address=\""+ adress+"\" where id="+Integer.parseInt(request.getParameter("upd"));
	    String sql3="update  tbAddress set tel=\""+ tel+"\" where id="+Integer.parseInt(request.getParameter("upd"));
	    response.setContentType("text/html; charset=utf-8");

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
