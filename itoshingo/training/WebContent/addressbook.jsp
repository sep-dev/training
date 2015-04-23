<%@ page language="java" contentType="text/html; charset=Windows-31J"%>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
		<title>登録画面</title>
	</head>
	<body>
		<h1>会員情報の登録</h1>

		<form action="Insert" method="post">
			<table>
				<tr>
					<th>氏名</th>
					<td><input type="text" name="name" value="" placeholder="ここに氏名を入力" style="width:250px;"></td>
				</tr>
				<tr>
					<th>住所</th>
					<td><input type="text" name="address" value="" placeholder="ここに住所を入力" style="width:250px;"></tr>
				<tr>
					<th>電話番号</th>
					<td><input type="text" name="tel" value="" placeholder="ここに電話番号を入力" maxlength="11" style="width:250px;"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="登録">
			<input type="reset" value="リセット">
		</form>

		<form action="ShowAll" method="get">
			<input type="submit" value="一覧表示">
		</form>
	</body>
</html>