package jp.co.Address;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

@WebServlet("/Addresslist")
public class Addresslist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		response.setContentType("text/html; charset=UTF-8");

		try {

			// JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");

			// データベースへ接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Addresslist", "root","03294163aA");

			// ID数発行用のSELECT文を準備
			String sqlStr = ("select * from tbAddress");
			Statement stmt = (Statement) con.createStatement();

			// SELECTを実行し、結果表(ResultSet)を取得
			ResultSet rs = stmt.executeQuery(sqlStr);

			PrintWriter out = response.getWriter();

				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<link rel='stylesheet' href='style.css' type='text/css'/>");
				out.println("</head>");
				out.println("<body>");
				out.println("<center>");
				out.println("<h1>会員一覧</h1>");
				out.println("</center>");
				out.println("<br />");
				out.println("<p class=neko>");
				out.println("　　　↓一つだけ選択可能");
				out.println("<br />");
				out.println("<form method=POST action='http://localhost:8080/hoge/Addressup' name=from1>");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");

				System.out.println("name" + name);
				System.out.println("address" + address);
				System.out.println("tel" + tel);

				out.println("<input type=radio name=question value=" + id + ">");

				out.println("氏名:" + name);
				out.println("住所:" + address);
				out.println("電話番号 " + tel);
				out.println("<br />");
				out.println("</p>");
			}
				out.println("<input type=submit value=更新or削除>");
				out.println("<br />");
				out.println("<input type=button value=新規登録 onclick=location.href='Addressbook.jsp'>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");

			rs.close();
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("ドライバーのロードに失敗しました" + e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("データベースに接続できません" + e);
			e.printStackTrace();
		} finally {
			// データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
