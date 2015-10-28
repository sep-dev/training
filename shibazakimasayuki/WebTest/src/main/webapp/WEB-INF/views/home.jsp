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
<center><h1>Welcome Teacher</h1>


<select name="example4">
${msg}
</select><br><br>
	<input type="submit" name="a"value="決定" /></center>
</form:form>
</body>
</html>
