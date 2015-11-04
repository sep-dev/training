<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>教科選択画面</title>
</head>
<body>

<h1>教科選択画面</h1>

<form:form modelAttribute="formModel" >
<table><tr>
<td><input type="submit" name="Japanese" value="国語"></td>
<td><input type="submit" name="Arithmetic" value="算数"></td></tr>
<tr><td><input type="submit" name="Science" value="理科"></td>
<td><input type="submit" name="Society" value="社会"></td></tr>
</table>
</form:form>


</body>

</html>
