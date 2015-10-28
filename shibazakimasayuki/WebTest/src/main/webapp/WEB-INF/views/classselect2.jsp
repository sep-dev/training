<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
</head>
<body>
<form:form modelAttribute="formModel" >
<center>
<h2>${msg1}</h2>
クラスを選択。


	<br><br>
	${msg}年
	<select name="className">
	${message}</select>

	<input type="hidden" name=schoolId value="${message2}">
	<input type="hidden" name=yearId value="${message1}">

	<input type="submit" name="e"value="決定" />
	<input type="submit" name="d"value="戻る" />
	</center>
</form:form>
</body>
</html>
