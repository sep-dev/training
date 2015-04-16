package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseLogic {

	Connection conn;

	public DatabaseLogic() {
		conn = null;
	}

	public void connect() {

		try {
			// JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");

			// データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost/training","root","asdfgh");

		} catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* --作成中-- */

	public String[][] executeSQL(String sql) {

		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if(sql.charAt(0) == 'S' || sql.charAt(0) == 's')	// 参照
			{
				// 結果表(ResultSet)と, そのメタデータを取得
				ResultSet rs = pStmt.executeQuery();
				ResultSetMetaData rsmt = rs.getMetaData();

				// 結果表と同じ形の２次元配列を作成
				int records = 0;
				while(rs.next())
				{
					records++;
				}
				int columns = rsmt.getColumnCount();
				String[][] result = new String[records + 1][columns + 1];	// nullを付加する分も確保する

				// ２次元配列に結果表のデータを格納し、呼び出し元に返す
				rs.beforeFirst();
				for(int g = 0; rs.next(); g++) {
					for(int r = 0; r < columns; r++) {
						result[g][r] = rs.getString(r + 1);
					}
					result[g][columns] = null;
				}
				result[records] = null;
				return(result);

			} else {

				// 更新, 挿入, 削除
				pStmt.executeUpdate();
				return(null);

			}

		} catch(SQLException e) {
			e.printStackTrace();
			return(null);
		}
	}

	public void disconnect() {

		// データベースに接続されていたら
		if(conn != null) {
			try {
				// データベース切断
				conn.close();

			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}