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

@WebServlet("/Addresslist")
public class Addresslist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType ("text/html; charset=UTF-8");

		Connection conn = null;

		try{
			//JDBCドライバの読み込み
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("mysql-connector-java-5.1.35-bin.jar");


			//データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaiinlist","naruse","123a");


			//ID数発行用SQL文準備
			String sqlselect = "SELECT * FROM tbaddress";
			PreparedStatement pStmtS = conn.prepareStatement(sqlselect);

			//SQLを実行、テーブル内の情報をWeb画面に表示
			ResultSet rsS = pStmtS.executeQuery();

			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>一覧画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>"+"・会員一覧" +"<p>");
			out.println("↓一つだけ選択可能");
			out.println("<br>");

			out.println("<FORM method=GET action=http://localhost:8080/servletstudy/Addressup.jsp name=insertform>");

			while(rsS.next()){

				int id =rsS.getInt("id");
				String name =rsS.getString("name");
				String address =rsS.getString("address");
				String tel =rsS.getString("tel");

				out.println("<input type=radio name=selection value="+id+">");

				//out.println("ナンバー："+id);
				out.println("氏名："+name);
				out.println(" 住所："+address);
				out.println(" 電話番号 "+tel);
				out.println("<br>");

			}
			out.println("<INPUT type=submit value=更新or削除>");
			out.println("<br>");
			out.println("<br>");
			out.println("<INPUT type=button value=新規登録 onclick=location.href='Addressbook.jsp'>");
			out.println("</FORM>");
			out.println("<br>");
			out.println("</body>");
			out.println("</html>");
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
}
