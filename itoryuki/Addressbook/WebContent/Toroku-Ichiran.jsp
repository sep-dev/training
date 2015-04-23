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
			<link href="css/TorokuIchiran.css" rel="stylesheet" type="text/css" media="screen" />
		</head>
			<body>
				<h1>会員一覧</h1>
					<p>↓一つだけ選択可能</p>
	<form action="Koshin-Sakujo.jsp" method="post">


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

			<label><input type ="radio" name="add" value=<%= rs.getString("id")%> required>
				氏名：<%= rs.getString("name")%>　住所：<%= rs.getString("address")%>　電話番号：<%= rs.getString("tel")%></label><br>

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
				<br>
				<div><input type="submit" class="button" value="更新or削除"  >　　
				</form>
				<input type="button" class="button" value="新規登録" onclick="location.href='http://localhost:8080/Addressbook/Shinki-Toroku.jsp'"></div>
		</body>
</html>