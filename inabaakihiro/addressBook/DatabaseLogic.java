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

	// データベースに接続するメソッド
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

	// データベース接続を切断するメソッド
	public void disconnect() {

		if(conn != null) {		// データベースに接続されていたら
			try {
				conn.close();	// データベース接続を切断

			} catch(SQLException e) {
				System.err.println("データベース切断の際に、エラーが起こりました");
				e.printStackTrace();
			}
		}
	}

	// 「(引数として渡された)SQL文」を実行し、参照が目的の場合は結果を返すメソッド
	public String[][] executeSQL(String sql) {

		try {
			// SQL文を、データベースに送る準備
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// ↓ SQL文が、 SELECT文だったら ↓
			if(sql.charAt(0) == 'S' || sql.charAt(0) == 's')
			{
				// SQL文実行 → [結果表(ResultSet)]と, その[メタデータ]を取得
				ResultSet rs = pStmt.executeQuery();
				ResultSetMetaData rsmt = rs.getMetaData();

				// 結果表と同じ形の２次元配列を作成
				int records = this.getRecords(rs);
				int columns = rsmt.getColumnCount();
				String[][] result = new String[records + 1][columns + 1];	// nullを付加する分(+1)も確保する

				// ２次元配列に結果表のデータを格納し、呼び出し元に返す
				rs.beforeFirst();
				for(int g = 0; rs.next(); g++) {
					for(int r = 0; r < columns; r++) {
						result[g][r] = rs.getString(r + 1);		// 「rsの１列目」を、「２次元配列の０列目」に格納
					}
					result[g][columns] = null;
				}
				result[records] = null;		// 列と行の最後に、呼び出し元でデータの終わりを判断するための、nullを付加
				return(result);
			}

			// ↓ SQL文が、SELECT文ではなかったら(UPDATE、INSERT、DELETEだったら) ↓
			else {
				pStmt.executeUpdate();	// SQL文実行
				return(null);			// 参照ではないので、結果は返さない
			}

		} catch(SQLException e) {
			System.err.println("SQL文実行の際に、エラーが起こりました");
			e.printStackTrace();
			return(null);
		}
	}

	// 「(引数として渡された)テーブル名」のテーブルの、レコード件数を取得するメソッド
	// ～ クラス外部からの呼び出し用 ～
	public int getRecords(String tableName) {

		try {
			// 全レコードの、「ID」列だけの結果表(ResultSet)を取得
			PreparedStatement pStmt = conn.prepareStatement("SELECT ID FROM " + tableName);
			ResultSet rs = pStmt.executeQuery();

			// 同クラス内の「レコード件数取得メソッド」の戻り値を、そのまま呼び出し元に返す
			return(this.getRecords(rs));

		} catch(SQLException e) {
			System.err.println("データ件数取得の際に、エラーが起こりました");
			e.printStackTrace();
			return 0;
		}
	}

	// 「(引数として渡された)結果表」の、レコード件数を取得するメソッド
	// ～ クラス内部での呼び出し用 ～
	private int getRecords(ResultSet rs) {

		try {
			int records = 0;
			rs.beforeFirst();
			while(rs.next())
			{
				records++;		// リザルトセットを走査し、レコード件数をカウント
			}

			return(records);	// カウントしたレコード件数を、呼び出し元に返す

		}catch(SQLException e) {
				System.err.println("データ件数カウントの際に、エラーが起こりました");
				e.printStackTrace();
				return 0;
		}
	}

}