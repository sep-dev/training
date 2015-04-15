package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterMember")
public class RegisterMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");

			// データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost/training","root","asdfgh");

			request.setCharacterEncoding("UTF-8");

			// INSERT文を準備
			String id =  String.valueOF(cnt);	// id付加用のcntは補助記憶装置に記録し、そこから読み込む
			String sql =
					"INSERT INTO ADDRESS_TBL(ID, NAME, ADDRESS, TEL) VALUES("
					+ id + ", " + name + ", " + address + ", " + tel + ")";



			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();

		}
	}
}