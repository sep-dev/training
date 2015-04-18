package jp.co.Address;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Addressupdate")
public class Addressupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   //リクエストパラメーラを取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String id = request.getParameter("question");

		System.out.println("test"+id+name+address+tel);


		//リクエストパラメータをチェック
		String msg ="更新成功！！！！！！！";
		String errorMsg ="空欄です！入力してください！！！！！！！！";


		int flag =0;

		if(name== null || name.length() == 0) {
			flag = +1;

		}
		if (address== null || address.length() == 0) {
			flag = +1;

		}
		if (tel== null || tel.length() == 0) {
			flag =+ 1;
		}
			//表示するメッセージを設定
			if(flag == 3){
				msg = errorMsg;
			}

			if(flag !=3){

		Connection conn = null;

		try{
			//JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");

			//データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Addresslist","root","03294163aA");


			//ID数発行用のUPDATE文を準備
			String sqlupdate = "update from tbAddress set name=?,address=?,tel=? where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sqlupdate);
			pStmt.setString(1,name);
			pStmt.setString(2,address);
			pStmt.setString(3,tel);
			pStmt.setString(4,id);


			//UPDATEを実行し、結果表(ResultSet)を取得
			int rs = pStmt.executeUpdate();

			System.out.println("id="+id);
			System.out.println("name"+name);
			System.out.println("address"+address);
			System.out.println("tel"+tel);



		} catch (ClassNotFoundException e) {
			System.out.println("ドライバーのロードに失敗しました" + e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("データベースに接続できません" + e);
			e.printStackTrace();
		}finally{
			//データベース切断
			if(conn != null){
				try{
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();

				}
			}

		}
			}


		//HTMLを入力
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>更新画面</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>" + msg +"</p>");
		out.println("<input type=button value=一覧表示 onclick=location.href='http://localhost:8080/hoge/Addresslist'>");
		out.println("</body>");
		out.println("</html>");

	}


	}
