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
		<h2>本当に削除してもよろしいですか？</h2>

		<%
		// セッションスコープから、選択中の会員のIDを取得
		String id = (String) session.getAttribute("id");

		// データベース接続
		DatabaseLogic dbLogic = new DatabaseLogic();
		dbLogic.connect();

		// 選択中の会員を、データベースからIDで検索する
		String[][] member =
				dbLogic.executeSQL("SELECT NAME,ADDRESS,TEL FROM ADDRESS_TBL WHERE ID = " + id);

		// 会員情報を取得
		String name = member[0][0];
		String address = member[0][1];
		String tel = member[0][2];
		%>
		<p>氏名:<%= name %> 住所:<%= address %> 電話番号:<%= tel %></p>
		<%
		// データベース切断
		dbLogic.disconnect();
		%>

		<form action="/addressBook/Delete" method="post">
			<input type="submit" value="削除">
		</form>

		<p><a href="/addressBook/SwitchMenu?action=list">
			<input type="button" value="一覧表示">
		</a></p>

	</body>
</html>