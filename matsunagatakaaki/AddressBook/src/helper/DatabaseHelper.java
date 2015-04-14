package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseHelper {
	public static final String URL = "jdbc:mysql://localhost/";
	public static final String DB_NAME = "addressbook";
	public static final String TABLE_NAME = "user_mst";
	public static final String USER = "root";
	public static final String PASSWORD = "T@kao521";

	public static Connection getConnectionInstance(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL + DB_NAME , USER , PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
