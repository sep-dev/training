package servlet;


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

@WebServlet("/Addressup")
public class Addressup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータのフォーマットを設定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの取得

		String id = request.getParameter("selectedid");
		String shimei = request.getParameter("shimei");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		System.out.println("テスト＝"+id+shimei+address+tel);

		String msg ="<h1>更新成功！！！！！！！</h1>";
		String errorMsg = "<h1>空欄です！入力してください！！！！！！！</h1>";

		int errorcCount  = 0;
			if(shimei==null || shimei.length() == 0){
				errorcCount=+1;
			}
			if(address==null || address.length() == 0){
				errorcCount=+1;
			}
			if(tel==null || tel.length() == 0){
				errorcCount=+1;
			}
			//表示するメッセージを設定
			if(errorcCount==3){
				msg=errorMsg;
			}

			//データベース接続準備
			Connection conn = null;

		if(errorcCount!=3){

			try{
				//JDBCドライバの読み込み
				Class.forName("com.mysql.jdbc.Driver");

				//データベースへ接続
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaiinlist","naruse","123a");

				//DB更新用SQL文準備
				String sqlupdate = "UPDATE tbaddress SET name=?, address=?, tel=? WHERE id=?";
				PreparedStatement pStmtU = conn.prepareStatement(sqlupdate);

				//UPDATE用データの設定
				pStmtU.setString(1, shimei);
				pStmtU.setString(2, address);
				pStmtU.setString(3, tel);
				pStmtU.setString(4, id);

				//SQLを実行、データベースに入力データを追加
				int rsU = pStmtU.executeUpdate();

			}

					//例外処理
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
					out.println("<INPUT type=button value=一覧表示 onclick=location.href='http://localhost:8080/servletstudy/Addresslist'>");
					out.println("</center>");
					out.println("</body>");
					out.println("</html>");

	}
}
