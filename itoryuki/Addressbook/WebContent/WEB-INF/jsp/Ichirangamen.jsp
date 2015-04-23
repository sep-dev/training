<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%!
public void jspInit() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
%>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>会員一覧</title>
		</head>
			<body>
				<h2>会員一覧</h2>
					<p>↓1つだけ選択可能</p>

<%
		//	データベースにアクセス
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db1","root","ROKOroko0321");

			stmt = conn.createStatement();
			String sql = "select name, address, tel from tbaddress";
			rs = stmt.executeQuery(sql);

			  while(rs.next()){
				  String NAME = rs.getString("name");
				  String ADDRESS = rs.getString("address");
				  String TEL= rs.getString("tel");
			  }
			}catch (SQLException e){
				e.printStackTrace();
			}finally{
				try { rs.close(); } catch (Exception e) {}
	            try { stmt.close(); } catch (Exception e) {}
	            try { conn.close(); } catch (Exception e) {}
		}
%>

				<p><input type="radio">氏名："NAME" 住所："ADDRESS" 電話番号 "TEL"</p><br>
				<input type="button" name="更新or削除" onclick="location.href='http://localhost:8080/Addressbook/Kousinsakujo.jsp'">><br>
				<br><br>
				<input type="button" value="新規登録" onclick="location.href='http://localhost:8080/Addressbook/Addressbook1.jsp'">
		</body>
</html>