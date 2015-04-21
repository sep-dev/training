<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>登録画面</title>
</head>
<body>
<h1>会員情報の登録</h1>
	<form method="POST" action="http://localhost:8080/hoge/Addressbook" name="from1">
		氏名<input type="text" name="name" size="50" value="ここに氏名を入力" /><br>
		住所<input type="text" name="address" size="50" value="ここに住所を入力" /><br>
		電話番号<input type="text" name="tel" size="50" value="ここに電話番号を入力" /><br>
		<input type="submit" value="登録" />
		<input type="reset" value="リセット" />
		<input type="button" value="一覧表示" onclick="location.href='http://localhost:8080/hoge/Addresslist'" />
	</form>
</body>
</html>