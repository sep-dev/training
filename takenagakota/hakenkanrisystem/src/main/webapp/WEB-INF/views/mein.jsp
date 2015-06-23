 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page session = "false" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>メインメニュー</title>
	</head>
	<body>
		<h1>メインメニュー</h1>
		<iframe src="./zenn" name="in" style="float:right" frameborder=0 height=800px width=85%  >
		</iframe>
		<a href="./zenn" target="in" name="hakensaki">派遣先情報</a><br>
		<a href="./syain" target="in" name="syain">派遣社員情報</a><br>
		<a href="./syozoku" target="in" name="syozoku">所属元情報</a><br>
		<a href="./syukkou" target="in" name="syukkou">出向先情報</a><br>
		<a href="./user" target="in" name="login">ユーザー管理</a><br>
		<a href="./" target="_top" name="home">ログアウト</a>
	</body>
</html>