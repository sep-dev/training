<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
</head>
<body>

<h1>画像アップロード</h1>

<form:form modelAttribute="formModel" >
${message}
${message2}<br><br>
${message3}

<input type="submit" name="upbtn2" value="りろーど">
<input type="submit" name="upbtn2" value="upbtn2">
        </form:form>


</body>

</html>
