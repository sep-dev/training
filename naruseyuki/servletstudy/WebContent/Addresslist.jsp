<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	会員一覧<br>
	↓一つだけ選択可能<br>



	<FORM method="POST" action="Addresslist.jsp" name="scriptform" onsubmit="return check()">

		<INPUT type="button" value="更新or削除" onclick="location.href='Addressup.jsp'">
		<br>
		<br>
		<INPUT type="button" value="新規登録" onclick="location.href='Addressbook.jsp'">
	</FORM>

</body>
</html>