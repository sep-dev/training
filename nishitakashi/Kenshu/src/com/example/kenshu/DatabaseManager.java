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

	//
	public Connection datalink(int command,HttpServletRequest request, HttpServletResponse response){

	    Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql=null;



		try {
			request.setCharacterEncoding("utf-8");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DatabaseHelper.URL, DatabaseHelper.USER, DatabaseHelper.PASSWORD);
			 //自動でコミットさせないようにする処理
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			rs = stmt.executeQuery(
		              "select  * from "+DatabaseHelper.TABLE_NAME);



			switch(command){
			//データの追加処理
			case DatabaseHelper.INSERT:
				col[DatabaseHelper.ID]=request.getParameter(DatabaseHelper.COL_NAME[DatabaseHelper.ID]);
				for(int i=DatabaseHelper.INNIT_COL_NO;i<DatabaseHelper.COL_NUM;i++){
					 //SQLインジェクション対策として特殊文字をエスケープ
					col[i]=SQLEscape.sqlEscape(request.getParameter(DatabaseHelper.COL_NAME[i]));
				}
				sql=insertSQL();
				int num1 = stmt.executeUpdate(sql);

				RequestDispatcher disp = request.getRequestDispatcher("jsp/SuccessAdd.jsp");
			    disp.forward(request, response);
				break;
			//データの更新処理
			case DatabaseHelper.UPDATE:
				col[DatabaseHelper.ID]=request.getParameter(DatabaseHelper.COL_NAME[DatabaseHelper.ID]);
				for(int i=DatabaseHelper.INNIT_COL_NO;i<DatabaseHelper.COL_NUM;i++){
					 //SQLインジェクション対策として特殊文字をエスケープ
					col[i]=SQLEscape.sqlEscape(request.getParameter(DatabaseHelper.COL_NAME[i]));
				}
				for(int current_col_No=DatabaseHelper.INNIT_COL_NO;current_col_No<DatabaseHelper.COL_NUM;current_col_No++){

					sql=updateSQL(request,current_col_No);
					int num2 = stmt.executeUpdate(sql);
				}

				RequestDispatcher disp2 = request.getRequestDispatcher("jsp/SuccessUpdate.jsp");
			    disp2.forward(request, response);
				break;
			//データの削除
			case DatabaseHelper.DELETE:
				col[DatabaseHelper.ID]=request.getParameter("del");
				sql=deleteSQL(col[DatabaseHelper.ID]);
				int num3 = stmt.executeUpdate(sql);

				RequestDispatcher disp3 = request.getRequestDispatcher("jsp/SuccessDel.jsp");
			    disp3.forward(request, response);
				break;
			}

			stmt.close();
			conn.commit();


		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			switch(command){
			//追加 例外発生時
			case DatabaseHelper.INSERT:
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
				break;
			//更新 例外発生時
			case DatabaseHelper.UPDATE:
				RequestDispatcher disp2 = request.getRequestDispatcher("jsp/FalseUpdate.jsp");
			    try {
					disp2.forward(request, response);
				} catch (ServletException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				break;
			//削除 例外発生時
			case DatabaseHelper.DELETE:
				RequestDispatcher disp3 = request.getRequestDispatcher("jsp/FalseDelete.jsp");
			    try {
					disp3.forward(request, response);
				} catch (ServletException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				break;

			}
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

		return conn;

	}


	//追加 SQL文生成
	private String insertSQL(){
		String sql=null;
		sql="insert into "+ DatabaseHelper.TABLE_NAME+"("+DatabaseHelper.COL_NAME[DatabaseHelper.NAME]
				+","+DatabaseHelper.COL_NAME[DatabaseHelper.ADDRESS]
				+","+DatabaseHelper.COL_NAME[DatabaseHelper.TEL]+")"
	    		+ "values(\""+col[DatabaseHelper.NAME]+"\",\""+col[DatabaseHelper.ADDRESS]+"\",\""+col[DatabaseHelper.TEL]+"\")";
		return sql;

	}

	//更新 SQL文生成
	private String updateSQL(HttpServletRequest request,int col_num){
		String sql=null;
		sql="update "+ DatabaseHelper.TABLE_NAME+" set "+ DatabaseHelper.COL_NAME[col_num]+"= \""+col[col_num]+"\" where id="+request.getParameter("upd")+";";
		System.out.println(sql);
		return sql;
	}

	//削除 SQL文生成
	private String deleteSQL(String id){
		String sql="delete  from tbAddress where id ="+id+";";
		System.out.println(sql);
		return sql;

	}

}
