<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.DatabaseLogic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="common.css" type="text/css">
		<title>住所録システム</title>
	</head>

	<body>
		<p>会員一覧</p>

		<form action="/addressBook/Select" method="get">
			<%
			// データベース接続
			DatabaseLogic dbLogic = new DatabaseLogic();
			dbLogic.connect();

			// データベースから、全会員情報を取得
			String[][] members = dbLogic.executeSQL("SELECT * FROM ADDRESS_TBL");

			// ↓ 会員情報を、１人分ずつ出力する ↓
			for(int i = 0; members[i] != null; i++) {

				String id = members[i][0];
				String name = members[i][1];
				String address = members[i][2];
				String tel = members[i][3];
			%>
				<input type="radio" name="id" value="<%= id %>">
				氏名:<%= name %> 住所:<%= address %> 電話番号:<%= tel %><br>
			<%
			}

			// データベース切断
			dbLogic.disconnect();
			%>
			<p><input type="submit" value="更新or削除"></p>
		</form>

		<p><a href="/addressBook/SwitchMenu">
			<input type="button" value="新規登録">
		</a></p>

	</body>
</html>