package jp.co.Address;

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

@WebServlet("/Addressup")
public class Addressup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		response.setContentType("text/html; charset=UTF-8");

		// リクエストパラメーラを取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("question");

		System.out.println("test" + id);

		try {

			// JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");

			// データベースへ接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Addresslist", "root","03294163aA");

			PrintWriter out = response.getWriter();

				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<link rel=stylesheet href=style.css type=text/css/>");
				out.println("</head>");
				out.println("<body>");
				out.println("<center>");
				out.println("<h1>会員情報の更新</h1>");
				out.println("<br>");
				out.println("<form method=POST action='http://localhost:8080/hoge/Addressupdate' name=from1>");
				out.println("<br />");

			// ID数発行用のSELECT文を準備
			PreparedStatement stmt = con
					.prepareStatement("select * from tbAddress Where id=?");
			stmt.setString(1, id);

			// SELECTを実行し、結果表(ResultSet)を取得
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");

				out.println("<p class=hako>");
				out.println("氏名:" + name);
				out.println("住所:" + address);
				out.println("電話番号" + tel);
				out.println("</p>");

				out.println("←選択されたデータ");
				out.println("<br>");
				out.println("氏名<input type=text name=name size=50 value="+ name + " /><br />");
				out.println("<br>");
				out.println("住所<input type=text name=address size=50 value="+ address + " /><br />");
				out.println("<br>");
				out.println("電話番号<input type=text name=tel size=50 value="+ tel + " /><br />");
				out.println("<br>");
				out.println("<input type=hidden name=question value=" + id+ "><br />");
				out.println("<br>");
				out.println("<input type=submit value='更新' />");
				out.println("<input type=reset value='リセット' />");
				out.println("<input type=button value=一覧表示 onclick=location.href='http://localhost:8080/hoge/Addresslist' /><br />");
				out.println("<br>");
				out.println("</form>");
				out.println("<form method=POST action='http://localhost:8080/hoge/Addressdelete.jsp' name=from1>");
				out.println("<input type=hidden name=question value=" + id+ ">");
				out.println("<input type=submit value=削除  /><br />");
				out.println("</from>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");

			}

		} catch (ClassNotFoundException e) {
			System.out.println("データベースに接続できません" + e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("ドライバーのロードに失敗しました" + e);
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
