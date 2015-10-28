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
<center><h1>${msg}</h1>
   学年を選択。

  <br><br>${message}
	<br><br><input type="submit" name="b"value="決定" /><br>
<input type="hidden" name=yearId value="${message1}">
<input type="hidden" name=schoolId value="${message2}">

	 <input type="submit" name="c"value="戻る" />
	</center>
</form:form>
</body>
</html>
