<%@ page language="java" contentType="text/html; charset=Windows-31J"%>

<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
		<title>処理完了</title>
	</head>
	<body>
		<div align="center">
			<h2>
				更新成功
			</h2>
			<br>
			<form action="ShowAll" method="get">
				<input type="submit" value="一覧表示">
			</form>

		</div>
	</body>
</html>