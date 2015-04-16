package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * データベースに関する補助処理等を記述
 */

public class DatabaseHelper {
	private static final String URL = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "addressbook";
	public static final String TABLE_NAME = "user_mst";
	private static final String USER = "root";
	private static final String PASSWORD = "T@kao521";

	//作業するテーブルの項目名を格納
	public class ColumnNames{
	    public static final String ID = "id";
	    public static final String NAME = "name";
	    public static final String ADDRESS = "address";
	    public static final String TEL = "tel";
	}

	//DBに接続したConnectionインスタンスを返す
	public static Connection getConnectionInstance(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL + DB_NAME , USER , PASSWORD);
			conn.setAutoCommit(false); //自動コミットOFF
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//Connection と Statement インスタンスを .close()
	//Connection と Statementの .close() 処理
	public static void commonClose(Connection connection,Statement stmt){
	    if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	    if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	}

	//Connection と PrepaerdStatement インスタンスを .close()
	public static void commonClose(Connection connection,PreparedStatement preStmt){
	    if(preStmt != null){
            try {
                preStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	    if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
}
