<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">

	<!-- 問題選択 -->

	<title>問題選択</title>
</head>
<body>

<h1>問題選択</h1>
${message}
<form:form modelAttribute="formModel" >

<input type="submit" name="${name1}" value="${btn1}"><br>
<input type="submit" name="${name2}" value="${btn2}"><br>
<input type="submit" name="${name3}" value="${btn3}"><br>
<input type="hidden" name="caid" value="${caid}">

</form:form>


</body>

</html>
