<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>住所録システム</title>
	</head>

	<body>
		<h1>会員情報の更新</h1>
		<p>氏名:<%= name %> 住所:<%= address %>" 電話番号:<%= tel %></p>

		<form action="/addressBook/RegisterMember" method="post">
			  氏名  <input type="text" name="name">
			  住所  <input type="text" name="address">
			電話番号<input type="text" name="tel">
			<input type="submit" value="更新">
			<input type="reset" value="リセット">
		</form>
		<input type="button" value="一覧表示" onclick="/addressBook/ListMember">

		<p><input type="button" value="削除" onclick="/addressBook/ListMember"></p>

	</body>
</html>