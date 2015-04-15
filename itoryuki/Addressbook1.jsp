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
				<form action="Addressbook2" method="post">
					氏名<input type="text" name="name" value=""><br>
					住所<input type="text" name="address" value=""><br>
					電話番号<input type="text" name="tel" value=""><br>
					<input type="submit" value="登録">
					<input type="reset" value="リセット">
					<input type="button" value="一覧表示">
				</form>
		</body>
</html>