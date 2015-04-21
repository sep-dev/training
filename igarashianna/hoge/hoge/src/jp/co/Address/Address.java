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

@WebServlet("/Addressbook")
public class Address extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメーラを取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		int maxID = 0;

		// リクエストパラメータをチェック
		String msg = "登録成功！！！！！！！";
		String errorMsg = "登録失敗！！！！！！！<br><br>空欄を埋めてください！";

		int flag = 0;

		if (name == null || name.length() == 0) {
			flag = 1;
		}
		if (address == null || address.length() == 0) {
			flag = 1;
		}
		if (tel == null || tel.length() == 0) {
			flag = 1;
		}

		// 表示するメッセージを設定
		if (flag == 1) {
			msg = errorMsg;
		}
		if (flag == 0) {

			Connection conn = null;

		try {
				// JDBCドライバを読み込み
				Class.forName("com.mysql.jdbc.Driver");

				// データベースへ接続
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Addresslist", "root","03294163aA");

				// ID数発行用のSELECT文を準備
				String sqlselect = "select max(id) cnt from tbAddress";
				PreparedStatement pStmt = conn.prepareStatement(sqlselect);

				// SELECTを実行し、結果表(ResultSet)を取得
				ResultSet rs = pStmt.executeQuery();
				if (rs.next()) {
					maxID = rs.getInt("cnt");
				}

				maxID = maxID + 1;

				String sql = "insert into tbAddress(id,name,address,tel) values(?,?,?,?)";
				PreparedStatement pStmt1 = conn.prepareStatement(sql);

				pStmt1.setInt(1, maxID);
				pStmt1.setString(2, name);
				pStmt1.setString(3, address);
				pStmt1.setString(4, tel);

				int rs1 = pStmt1.executeUpdate();

				System.out.println("maxID=" + maxID);
				System.out.println("name" + name);
				System.out.println("address" + address);
				System.out.println("tel" + tel);

		} catch (ClassNotFoundException e) {
			System.out.println("データベースに接続できません" + e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("ドライバーのロードに失敗しました" + e);
			e.printStackTrace();
		} finally {

			// データベース切断
				if (conn != null) {
		try {
			conn.close();
			} catch (SQLException e) {
						e.printStackTrace();

					}
				}

			}
		}

		// HTMLを入力
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<link rel='stylesheet' href='style.css' type='text/css'/>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("<title>登録画面</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<center>");
				out.println("<p class=neko>");
				out.println("<br>");
				out.println(msg);
				out.println("<br /><br>");
				out.println("</p>");
				out.println("<br />");
		if (flag == 0)
				out.println("<input type=button value=一覧表示 onclick=location.href='http://localhost:8080/hoge/Addresslist'>");
		if (flag == 1)
				out.println("<input type=button value=新規登録 onclick=location.href='Addressbook.jsp'>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");

	}

}
