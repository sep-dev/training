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
			System.err.println("データベース接続の際に、エラーが起こりました");
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.err.println("ドライバが見つかりませんでした");
			e.printStackTrace();
		}
	}

	public void disconnect() {

		// データベースに接続されていたら
		if(conn != null) {
			try {
				// データベース切断
				conn.close();

			} catch(SQLException e) {
				System.err.println("データベース切断の際に、エラーが起こりました");
				e.printStackTrace();
			}
		}
	}

	public String[][] executeSQL(String sql) {

		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if(sql.charAt(0) == 'S' || sql.charAt(0) == 's')	// 参照
			{
				// 結果表(ResultSet)と, そのメタデータを取得
				ResultSet rs = pStmt.executeQuery();
				ResultSetMetaData rsmt = rs.getMetaData();

				// 結果表と同じ形の２次元配列を作成
				int records = this.getRecords(rs);
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
				return(null);			// 参照ではないので、結果は返さない

			}

		} catch(SQLException e) {
			System.err.println("SQL文実行の際に、エラーが起こりました");
			e.printStackTrace();
			return(null);
		}
	}

	public int getRecords(String tableName) {

		try {
			PreparedStatement pStmt = conn.prepareStatement("SELECT ID FROM " + tableName);
			ResultSet rs = pStmt.executeQuery();

			return(this.getRecords(rs));	// レコードの数を呼び出し元に返す

		} catch(SQLException e) {
			System.err.println("データ件数取得の際に、エラーが起こりました");
			e.printStackTrace();
			return 0;
		}
	}

	private int getRecords(ResultSet rs) {

		try {
			// リザルトセットを走査し、レコードの件数をカウント
			int cnt = 0;
			while(rs.next())
			{
				cnt++;
			}

			return(cnt);

		}catch(SQLException e) {
				System.err.println("データ件数カウントの際に、エラーが起こりました");
				e.printStackTrace();
				return 0;
		}
	}
}