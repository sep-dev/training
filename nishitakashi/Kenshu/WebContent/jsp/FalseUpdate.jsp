<%--更新失敗を表示する画面 --%>
<%--DataUpdate.javaから遷移。更新に失敗した場合--%>
<%--一覧表示ボタンを押下時、DataShow.jspに遷移--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新失敗画面</title>
</head>
<body>
	<h1>更新できませんでした</h1>
	<div id="body">特殊な文字が使用されています</div>
	<form action="jsp/DataShow.jsp">
	<button type="submit" name="list_show" value="">一覧表示</button>
	</form>
</body>
</html>