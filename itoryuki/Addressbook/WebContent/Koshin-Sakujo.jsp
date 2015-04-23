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
					<form action="Koshin-Syori" method="post">
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
					<label>氏名<input type="text" name="name"size="15" maxlength="15" value=""></label><br>
					<label>住所<input type="text" name="address" size="60" maxlength="60" value=""></label><br>
					<label>電話番号<input type="text" name="tel" size="15" maxlength="15"value=""></label><br>
						<input type="submit" value="更新">
						<input type="reset" value="リセット">
						<input type="button" value="一覧表示"  onclick="location.href='Toroku-Ichiran.jsp'"><br>
					</form>
						<br><br>

					<form action="Sakujo-Kakunin.jsp" method="post">
						<input type="submit" name="Sakujo" value="削除" onclick="location.href='Sakujo-Kakunin.jsp'">
					</form>
			</body>
	</html>