<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<% //ドライバのロード
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (Exception e) {
		e.printStackTrace();
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
					<p>↓一つだけ選択可能</p>
	<form action="KousinSakujo.jsp" method="post">
<%
		//	データベースにアクセス
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db1","root","ROKOroko0321");

			stmt = conn.createStatement();
			String sql = "select id, name, address, tel from addresstable";
			rs = stmt.executeQuery(sql);

			//テーブルの中身を表示
			  while(rs.next()){
%>

	            	<label><p><input type ="radio" name="add" value=<%= rs.getString("id")%> required>
	            	氏名：<%= rs.getString("name")%>　住所：<%= rs.getString("address")%>　電話番号<%= rs.getString("tel")%></label></p>
<%
			//データベースから切断
			  }
			}catch (SQLException e){
				e.printStackTrace();
			}finally{
				try { rs.close(); } catch (Exception e) {}
	            try { stmt.close(); } catch (Exception e) {}
	            try { conn.close(); } catch (Exception e) {}
			}
%>
				<input type="submit" name="del" value="更新or削除"><br>
				</form>
				<br><br>
				<input type="button" value="新規登録" onclick="location.href='http://localhost:8080/Addressbook/Addressbook1.jsp'">
		</body>
</html>