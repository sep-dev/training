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

@WebServlet("/Addressdelete")
public class Addressdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //リクエストパラメーラを取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("question");

		System.out.println("test"+id);


		Connection conn = null;

		try{
			//JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");

			//データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Addresslist","root","03294163aA");


			//ID数発行用のDELETE文を準備
			String sqldelete = "delete from tbAddress where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sqldelete);
			pStmt.setString(1,id);

			//DELETEを実行し、結果表(ResultSet)を取得
			int rs1 =pStmt.executeUpdate();




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


	//HTMLを入力
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out =response.getWriter();
	out.println("<!DOCTYPE html>");
	out.println("<html>");
	out.println("<head>");
	out.println("<meta charset=\"UTF-8\">");
	out.println("<title>削除完了画面</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("削除しました！！");
	out.println("<input type=button value=一覧表示 onclick=location.href='http://localhost:8080/hoge/Addresslist'>");
	out.println("</body>");
	out.println("</html>");



	}
	}

