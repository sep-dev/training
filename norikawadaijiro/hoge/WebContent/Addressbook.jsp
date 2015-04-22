<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登録画面</title>
		<link rel="stylesheet" type="text/css" href="http://localhost:8080/hoge/css/style.css">
	</head>
	<body>
		<h1>会員情報の登録</h1>
		<FORM ACTION="Insert"method="get">
			<p>
				　氏名　：<input type="text" name="name" size="50" maxlength="20"placeholder="氏名を入力してください">
			</p>
			<p>
				　住所　：<input type="text" name="address" size="50" maxlength="50"placeholder="住所を入力してください">
			</p>
			<p>
				電話番号：<input type="text" name="tel" size="48" maxlength="11"placeholder="電話番号を入力してください">
			</p>
			<input type="submit" value="登録">
			<br />
			<input type="reset" value="リセット">
		</FORM>

		<FORM ACTION="itiran.jsp">
			<input type="submit" value="一覧表示">
		</FORM>
	</body>
</html>