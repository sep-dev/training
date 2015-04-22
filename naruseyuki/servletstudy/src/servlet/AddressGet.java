package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Addressbook")
public class Addressget extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//リクエストパラメータのフォーマットを設定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの取得


		String shimei = request.getParameter("shimei");
		String address = request.getParameter("address");
		String tel = request.getParameter("tell");
		int maxID = 0;

		//System.out.println("名前は入っているか＝"+shimei);

		System.out.println(tel);

		String msg ="<h1>登録成功！！！！！！！</h1>";
		String errorMsg = "<h1>登録失敗！！！！！！！<br>空欄を埋めてください！</h1>";

		int errorcCount  = 0;
			if(shimei==null || shimei.length() == 0){
				errorcCount=1;
			}
			if(address==null || address.length() == 0){
				errorcCount=1;
			}
			if(tel==null || tel.length() == 0){
				errorcCount=1;
			}
			//表示するメッセージを設定
			if(errorcCount==1){
				msg = errorMsg;
			}

			if(errorcCount==0){

			//受け取ったデータの表示
			System.out.println(shimei+address+tel);

			//データベース接続準備
			Connection conn = null;

			try{
				//JDBCドライバの読み込み
				Class.forName("com.mysql.jdbc.Driver");
				//Class.forName("mysql-connector-java-5.1.35-bin.jar");


				//データベースへ接続
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaiinlist","naruse","123a");


				//ID数発行用SQL文準備
				//String sqlselect = "SELECT COUNT(*) cnt FROM tbaddress";
				String sqlselect = "SELECT Max(id) cnt FROM tbaddress";
				PreparedStatement pStmtS = conn.prepareStatement(sqlselect);

				//SQLを実行、ID総数を取得
				ResultSet rsS = pStmtS.executeQuery();
				if(rsS.next()){
					maxID = rsS.getInt("cnt");
				}


				//System.out.println("\nぴゃー"+maxID);

				maxID = maxID+1;

				//System.out.println("\nピャー"+maxID);


				//DB登録用SQL文準備
				String sqlinsert = "INSERT INTO tbaddress(id,name,address,tel) VALUES(?,?,?,?)";
				PreparedStatement pStmtI = conn.prepareStatement(sqlinsert);

				//INSERT用データの設定
				pStmtI.setInt(1, maxID);
				pStmtI.setString(2, shimei);
				pStmtI.setString(3, address);
				pStmtI.setString(4, tel);


				//SQLを実行、データベースに入力データを追加
				int rsI = pStmtI.executeUpdate();



			}


			catch(SQLException e){
				System.out.println("データベースが読み込めません！"+e);
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				System.out.print("ドライバが読み込めません！"+e);
				e.printStackTrace();
			}
			finally{
				//データベース切断
				if(conn!=null){
					try{
						conn.close();
					}
					catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}



			//HTMLを表示
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel=stylesheet href=style.css type=text/css />");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>登録画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<center>");
			out.println("<p>" + msg + "</p>");
			out.println("<br><br />");
			if(errorcCount==0)
				out.println("<INPUT type=button value=一覧表示 onclick=location.href='http://localhost:8080/servletstudy/Addresslist'>");
			else
				out.println("<INPUT type=button value=新規登録 onclick=location.href='Addressbook.jsp'>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");


	}

}
