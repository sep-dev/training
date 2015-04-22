<%--登録失敗を表示する画面 --%>
<%--DataAdd.javaから遷移。新規登録に失敗した場合--%>
<%--新規登録ボタンを押下時、MainScreen.jspに遷移--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>読み込み失敗</title>
</head>
<body>
    <h1>登録できませんでした</h1>
    <div id="body">特殊な文字が使用されています</div>
    <form action="jsp/MainScreen.jsp">
    <button type="submit" name="list_show" value="">新規登録</button>
    </form>
</body>
</html>
