<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<title>登録画面</title>
	</head>
	<body>
		<h1>会員情報の登録</h1>

		<form action="Insert" method="post">
			氏名 <input type="text" name="name">
			<br>
			住所 <input type="text" name="address">
			<br>
			電話番号 <input type="text" name="tel">
			<br>
			<input type="submit" value="登録">
			<input type="reset" value="リセット">
		</form>
		<a href="addresslist.jsp">
			<input type="button" value="一覧表示">
		</a>
	</body>
</html>