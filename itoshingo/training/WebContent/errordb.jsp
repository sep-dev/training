<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
		<title>DBエラー</title>
	</head>
	<body>
		<div align="center">
			<h3>
				操作失敗
				<br>
				データベースの動作が正常に行われませんでした。
			</h3>
			<br><br>
			<a href="addressbook.jsp"><input type="button" value="新規登録"></a>
			<br><br>
			<form action="ShowAll" method="get">
				<input type="submit" value="一覧表示">
			</form>
		</div>
	</body>
</html>