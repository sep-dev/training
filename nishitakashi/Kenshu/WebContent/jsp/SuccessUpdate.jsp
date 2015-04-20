<%--更新成功を表示する画面 --%>
<%--DataUpdate.javaから遷移--%>
<%--一覧表示ボタンを押下時、DataShow.jspに遷移--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新成功画面</title>
<link rel="stylesheet" href="css/c_list.css" type="text/css" />
</head>
<body>

	<div id="head">更新成功！！</div>
	<br>
	<div id="body">
	<form action="jsp/DataShow.jsp">
	<button type="submit" name="return" value="">一覧表示</button>
	</form>
	</div>
</body>
</html>