<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<% //選択したIDを取得
	String sakujoID;
	request.setCharacterEncoding("UTF-8");
	sakujoID = request.getParameter("ID");
	System.out.println(sakujoID);
%>

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
			<title>削除確認</title>
			<link href="css/Sakujo-Kakunin.css" rel="stylesheet" type="text/css" media="screen" />
		</head>
			<body>
				<h1>本当に削除してもいいですか？</h1>
<%
	//データベースにアクセス
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;

	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db1","root","ROKOroko0321");

		stmt = conn.createStatement();
		String sql = "select * from addresstable where id="+sakujoID+";";
		rs = stmt.executeQuery(sql);

	//テーブルの中身を表示
		while(rs.next()){
%>

				<form action="SakujoSyori"  method="post">
					氏名：<%= rs.getString("name")%>　住所：<%= rs.getString("address")%>　電話番号<%= rs.getString("tel")%><br>

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

				<div><button type = "submit" id="A" name="ID" value=<%= sakujoID%>>削除</button>
				</form>
				<input type="button" class="button" value="一覧表示" onclick="location.href='Toroku-Ichiran.jsp'"></div>
			</body>
	</html>