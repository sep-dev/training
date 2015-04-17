<%--削除失敗を表示する画面 --%>
<%--DataDelete.javaから遷移。削除に失敗した場合--%>
<%--一覧表示ボタンを押下時、DataShow.jspに遷移--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>削除できませんでした</h1>
	<form action="jsp/DataShow.jsp">
	<button type="submit" name="list_show" value="">一覧表示</button>
	</form>
</body>
</html>