<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>登録画面</title>
	</head>
		<body>
			<h1>会員情報の登録</h1>
				<form action="Tourokusyori" method="post">
					<label>氏名<input type="text" name="name"size="15" maxlength="15" value=""></label><br>
					<label>住所<input type="text" name="address" size="60" maxlength="60" value=""></label><br>
					<label>電話番号<input type="text" name="tel" size="15" maxlength="15"value=""></label><br>
					<input type="submit" value="登録" >
					<input type="reset" value="リセット">
					<input type="button" value="一覧表示" onclick="location.href='Ichirangamen.jsp'">
				</form>
		</body>
</html>