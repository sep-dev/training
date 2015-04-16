
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>会員情報の登録</title>
</head>

<body>
	<h1>会員情報の登録</h1>
	<br>
	<FORM method="POST" action="http://localhost:8080/servletstudy/Addressbook" name="insertform">
		氏名 <INPUT type="text" name="shimei" size="100" maxlength="100"value="成瀬" style="color:#999"><br>
		住所 <INPUT type="text"name="address" size="100" maxlength="100" value="長崎" style="color:#999"><br>
		電話番号 <INPUT type="text" name="tell" size="100" maxlength="100"value="08042877369" style="color:#999"><br>

		<INPUT type="submit" value="登録">
		<INPUT type="reset" value="リセット">
		<INPUT type="button"value="一覧表示" onclick="location.href='Addresslist.jsp'">


	</FORM>
	<br>
</body>
</html>

