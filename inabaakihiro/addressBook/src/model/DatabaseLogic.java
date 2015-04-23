package model;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseLogic {

	Connection conn;

	DatabaseLogic() {

		// JDBCドライバを読み込み
		Class.forName("com.mysql.jdbc.Driver");

		// データベースへ接続
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sample","root","asdfgh");
		} catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void select(String sql) {

		try {
			// SQL文を準備
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECTを実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insert

	public void

	public void disconnect() {

		// データベース切断
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}