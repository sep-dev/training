<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
		<title>入力エラー</title>
	</head>
	<body>
		<div align="center">
			<h3>
				更新失敗
				<br>
				入力内容に誤りがあります。入力しなおしてください。
			</h3>
			<br><br>
			<form action="ShowAll" method="get">
				<input type="submit" value="一覧表示">
			</form>
		</div>
	</body>
</html>