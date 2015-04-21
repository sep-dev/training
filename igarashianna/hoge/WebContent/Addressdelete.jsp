<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>削除画面</title>
</head>
<body>
<h1>本当に削除してもいいですか？</h1>


<form action="post" action="Addresslist.jsp" name="from1" onsubmit="return check()">
<input type="submit" value="削除" /><br><br>
<input type="button" value="一覧表示"onclick="location.href='Addresslist.jsp'" />
</form>
</body>
</html>