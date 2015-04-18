package com.example.kenshu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatabaseManager {
	public String [] escapeParameter;
	private String [] col;
	public DatabaseManager(){
		escapeParameter=new String[DatabaseHelper.COL_NUM];
		col=new String[DatabaseHelper.COL_NUM];
	}
	public ResultSet datalink(int command,HttpServletRequest request, HttpServletResponse response){

	    Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql=null;
		col[0]=request.getParameter(DatabaseHelper.COL_NAME[0]);
		for(int i=1;i<DatabaseHelper.COL_NUM;i++){
			 //SQLインジェクション対策として特殊文字をエスケープ
			col[i]=SQLEscape.sqlEscape(request.getParameter(DatabaseHelper.COL_NAME[i]));
		}

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DatabaseHelper.URL, DatabaseHelper.USER, DatabaseHelper.PASSWORD);
			 //自動でコミットさせないようにする処理
			conn.setAutoCommit(false);

			stmt = conn.createStatement();

			switch(command){
			case DatabaseHelper.INSERT:
				sql=insertData();
				int num = stmt.executeUpdate(sql);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/SuccessAdd.jsp");
			    disp.forward(request, response);
				break;
			case DatabaseHelper.UPDATE:
				sql=updateData();
				int num2 = stmt.executeUpdate(sql);
				RequestDispatcher disp2 = request.getRequestDispatcher("jsp/SuccessUpdate.jsp");
			    disp2.forward(request, response);
				break;
			case DatabaseHelper.DELETE:
				rs=deleteData();
				break;
			case DatabaseHelper.SELECT:
				rs=selectData(col);
				break;
			}
			stmt.close();
			conn.commit();

		} catch (InstantiationException e) {
			try {
				conn.rollback();

			} catch (SQLException e1) {

				e1.printStackTrace();

			}
			e.printStackTrace();
			RequestDispatcher disp = request.getRequestDispatcher("jsp/FalseAdd.jsp");
		      try {
				disp.forward(request, response);
			} catch (ServletException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		} catch (IllegalAccessException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
		      try{
		    	  if (conn != null){

		    		  conn.close();
		    	  }
		      }catch (SQLException e){
		    	 e.printStackTrace();
		    	 try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
		      }
		}

		return rs;

	}
	private String insertData(){
		String sql=null;
		sql="insert into "+ DatabaseHelper.TABLE_NAME+"("+DatabaseHelper.COL_NAME[DatabaseHelper.NAME]
				+","+DatabaseHelper.COL_NAME[DatabaseHelper.ADDRESS]
				+","+DatabaseHelper.COL_NAME[DatabaseHelper.TEL]+")"
	    		+ "values(\""+col[DatabaseHelper.NAME]+"\",\""+col[DatabaseHelper.ADDRESS]+"\",\""+col[DatabaseHelper.TEL]+"\")";


		return sql;

	}
	private String updateData(){
		ResultSet rs=null;
		return null;

	}
	private ResultSet deleteData(){
		ResultSet rs=null;
		return null;

	}
	private ResultSet selectData(String[] col){
		ResultSet rs=null;
		return null;

	}

}
