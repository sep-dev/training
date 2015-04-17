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
 * Servlet implementation class DataAdd
 */

//データをSQL文で挿入するクラス
//成功時SuccessAdd.jspへ遷移
//失敗時FalseAdd.jspへ遷移

public class DataAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataAdd() {
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

	    String sql="insert into tbAddress (name,address,tel) "
	    		+ "values(\""+name+"\",\""+adress+"\",\""+tel+"\")";



		try {
		  Class.forName("com.mysql.jdbc.Driver").newInstance();
		  conn = DriverManager.getConnection(url, user, password);
		  //自動でコミットさせないようにする処理
		  conn.setAutoCommit(false);

		  stmt = conn.createStatement();
		  int num = stmt.executeUpdate(sql);

	      stmt.close();
	      conn.commit();
	    // 読み込み成功　JSPに遷移
	      RequestDispatcher disp = request.getRequestDispatcher("jsp/SuccessAdd.jsp");
	      disp.forward(request, response);
		}catch(Exception e) {
			// 読み込み失敗　ロールバックしてJSPに遷移
		      RequestDispatcher disp = request.getRequestDispatcher("jsp/FalseAdd.jsp");
		      disp.forward(request, response);
		      e.printStackTrace();
		      try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		      System.out.print("エラー");
		}finally{
		      try{
		    	  if (conn != null){

		    		  conn.close();
		    	  }
		      }catch (SQLException e){
		    	  response.setContentType("text/html; charset=utf-8");
		    	  PrintWriter out = response.getWriter();

		    	  out.println("SQLException:" + e.getMessage());
		      }
		}

	}


}
