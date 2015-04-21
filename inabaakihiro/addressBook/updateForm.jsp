<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.DatabaseLogic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="common.css" type="text/css">
		<title>住所録システム</title>
	</head>

	<body>
		<h1>会員情報の更新</h1>

		<%
		// セッションスコープから, 選択された会員を取得
		String id = (String) session.getAttribute("id");

		DatabaseLogic dbLogic = new DatabaseLogic();
		dbLogic.connect();

		String[][] member =
				dbLogic.executeSQL("SELECT NAME,ADDRESS,TEL FROM ADDRESS_TBL WHERE ID = " + id);

		String name = member[0][0];
		String address = member[0][1];
		String tel = member[0][2];
		%>
		<p>氏名:<%= name %> 住所:<%= address %> 電話番号:<%= tel %></p>
		<%
		dbLogic.disconnect();
		%>

		<form action="/addressBook/Update" method="post">
			<p>・氏名<br><input type="text" name="name"><br></p>
			<p>・住所<br><input type="text" name="address"></p>
			<p>・電話番号<br><input type="text" name="tel"><br></p>
			<input type="submit" value="更新">
			<input type="reset" value="リセット">
		</form>

		<p><a href="/addressBook/SwitchMenu?action=list">
			<input type="button" value="一覧表示">
		</a></p>

		<p><a href="/addressBook/Delete">
			<input type="button" value="削除">
		</a></p>

	</body>
</html>