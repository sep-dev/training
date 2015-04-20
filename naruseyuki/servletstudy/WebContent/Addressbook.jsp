<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="hyakushiki.css" type="text/css" />
</head>
<title>会員情報の登録</title>

<body>

	<h1>会員情報の登録</h1>
	<br>
	<FORM method="POST" action="http://localhost:8080/servletstudy/Addressbook" name="insertform">
		<p>氏名</p> <INPUT type="text" name="shimei" size="100" maxlength="8"value="入力して"><br>
		<p>住所</p> <INPUT type="text"name="address" size="100" maxlength="4" value="入力して"><br>
		<p>電話番号</p> <INPUT type="text" name="tell" size="100" maxlength="11"value="入力して"><br>

		<INPUT type="submit" value="登録">
		<INPUT type="reset" value="リセット">
		<INPUT type="button"value="一覧表示" onclick="location.href='http://localhost:8080/servletstudy/Addresslist'">
	</FORM>
	<br>
</body>
</html>

