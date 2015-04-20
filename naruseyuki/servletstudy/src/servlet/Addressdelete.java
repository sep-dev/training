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

@WebServlet("/Addressdelete")
public class Addressdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータのフォーマットを設定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの取得
		String id=request.getParameter("selectedid");

			//データベース接続準備
			Connection conn = null;



			try{
				//JDBCドライバの読み込み
				Class.forName("com.mysql.jdbc.Driver");
				//Class.forName("mysql-connector-java-5.1.35-bin.jar");

				//データベースへ接続
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaiinlist","naruse","123a");

				//要素削除用SQL文準備
				String sqldelete = "DELETE FROM tbaddress WHERE id =?";
				PreparedStatement pStmtI = conn.prepareStatement(sqldelete);

				pStmtI.setString(1, id);

				//SQLを実行、データベースに入力データを追加
				int rsD = pStmtI.executeUpdate();

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

		//HTMLを表示
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<link rel=stylesheet href=style.css type=text/css />");
		out.println("<title>削除完了</title>");
		out.println("</head>");
		out.println("<center>");
		out.println("<h1>削除しました！</h1>");
		out.println("<body>");
		out.println("<br>");
		out.println("<INPUT type=button value=一覧表示 onclick=location.href='http://localhost:8080/servletstudy/Addresslist'>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

}
