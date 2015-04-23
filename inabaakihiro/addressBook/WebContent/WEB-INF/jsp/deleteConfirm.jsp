<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>住所録システム</title>
	</head>

	<body>
		<h2>本当に削除してもよろしいですか？</h2>
		<p>氏名:<%= name %> 住所:<%= address %>" 電話番号:<%= tel %></p>
		<p><input type="button" value="削除" onclick="/addressBook/ListMember"></p>
		<p><input type="button" value="一覧表示へ" onclick="/addressBook/ListMember"></p>
	</body>
</html>