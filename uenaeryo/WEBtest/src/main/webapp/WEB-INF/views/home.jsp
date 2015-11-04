<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title>
</head>
<body>

<h1>ログイン画面</h1>


<form:form modelAttribute="formModel" >
${message}
<br>
ユーザーＩＤ：<form:input path="user" /><br>
パスワード：<form:input type="password" path="pass" /><br>

<input type=submit name=upbtn value=アップロード>

<input type="submit" value="ログイン">

</form:form>


</body>

</html>
