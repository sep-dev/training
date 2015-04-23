<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="common.css" type="text/css">
		<title>住所録システム</title>
	</head>

	<body>
		<h1>会員情報の登録</h1>

		<form action="/addressBook/Register" method="post">
			<p>・氏名<br><input type="text" name="name"><br></p>
			<p>・住所<br><input type="text" name="address"></p>
			<p>・電話番号<br><input type="text" name="tel"><br></p>
			<p><input type="submit" value="登録">
			<input type="reset" value="リセット"></p>
		</form>

		<p><a href="/addressBook/SwitchMenu?action=list">
			<input type="button" value="一覧表示">
		</a></p>

	</body>
</html>