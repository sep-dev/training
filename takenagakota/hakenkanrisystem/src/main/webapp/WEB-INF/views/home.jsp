<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.* " import="java.lang.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>派遣人員管理システム</title>
</head>
<body>
<div align="center">

<h1>派遣人員管理システム</h1>
${dame}
<form action="./login" method="post">
ユーザID：<input type="text" name="ID"><br>
パスワード：<input type="password" name="Pass"><br>
<input type="submit" value="ログイン">
</form>
</div>
</body>
</html>
