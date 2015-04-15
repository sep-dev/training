<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>住所録システム</title>
	</head>

	<body>
		<p>会員一覧</p>

		<form action="/addressBook/" method="get">
		<%
		while(rs.next()) {

			int id = rs.getInt("id");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String tel = rs.getString("tel");
		%>
			<input type="radio" name="member" value="<%= id %>">
			氏名:<%= name %> 住所:<%= address %>" 電話番号:<%= tel %><br>
		<%
		}
		%>
		<input type="submit" value="更新or削除">
		</form>

		<p><input type="button" value="新規登録" onclick="/addressBook/index"></p>

	</body>
</html>