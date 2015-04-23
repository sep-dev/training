<%--登録成功を表示する画面 --%>
<%--DataAdd.javaから遷移。新規登録に成功したことを表示--%>
<%--一覧表示ボタンを押下時、DataShow.jspに遷移--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/c_list.css" type="text/css" />
<title>読み込み成功</title>
</head>
<body>
    <h1>登録成功！</h1>
    <form action="jsp/DataShow.jsp">
    <div id="button"><button type="submit" name="list_show" value="">一覧表示</button></div>
    </form>
</body>
</html>
