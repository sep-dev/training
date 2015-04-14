<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<title>更新/削除</title>
	</head>
	<body>
		<h1>会員の更新</h1>

		<form action="">
			氏名<input type="text" name="name">
			<br>
			住所<input type="text" name="address">
			<br>
			電話番号<input type="text" name="tel">
			<br>
			<input type="submit" value="更新">
		</form>
		<a href="addresslist.jsp">
			<input type="button" value="一覧表示">
		</a>
		<br><br>
		<a href="delete1.jsp">
			<input type="submit" value="削除">
		</a>

	</body>
</html>