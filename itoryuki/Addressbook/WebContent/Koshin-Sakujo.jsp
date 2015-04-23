<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<% //一覧で選択したIDを取得
	String sentakuID;
	request.setCharacterEncoding("UTF-8");
	sentakuID = request.getParameter("add");
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
			<title>会員情報の更新</title>
			<link href="css/Koshin-Sakujo.css" rel="stylesheet" type="text/css" media="screen" />
		</head>
			<body>
				<h1>会員情報の更新</h1>

<%
	//データベースにアクセス
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db1","root","ROKOroko0321");

			stmt = conn.createStatement();
			String sql = "select * from addresstable where id="+sentakuID+";";
			rs = stmt.executeQuery(sql);

	//テーブルの中身を表示
			while(rs.next()){
%>

				<form action="KoshinSyori" method="post">
					<p>氏名：<%= rs.getString("name")%>　住所：<%= rs.getString("address")%>　電話番号<%= rs.getString("tel")%></p>
					<label>・氏名　　　<input type="text" name="name" size="19" value="" placeholder="名前を入力してください"></label><br>
					<label>・住所　　　<input type="text"name="address" size="50" value="" placeholder="住所を入力してください"></label><br>
					<label>・電話番号　<input type="text" onKeyup="this.value=this.value.replace(/[^0-9a-z]+/i,'')"name="tel" size="19" value="" placeholder="番号を入力してください。"></label><br>
					<br>
						<input type="hidden" name="ID" value=<%= rs.getString("id")%> >
						<div><input type="submit" class="button" value="更新">　
						<input type="reset" class="button" value="リセット">　
						<input type="button" class="button" value="一覧表示"  onclick="location.href='Toroku-Ichiran.jsp'"></div><br>
				</form>
				<form action="Sakujo-Kakunin.jsp" method="post">
						<input type="hidden" name="ID" value=<%= rs.getString("id")%> >
						<div><input type="submit" name="sakujo" class="button" value="削除"></div>
				</form>

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

			</body>
	</html>