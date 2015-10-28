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
<center>${message}・${message1}年・${msg}<br>
<h1>教科選択</h1>

	<input type="submit" name="f" value="国語" /><br><br>
	<input type="submit" name="g" value="算数" /><br><br>
	<input type="submit" name="h" value="理科" /><br><br>
	<input type="submit" name="i" value="社会" /><br><br>
	<input type="submit" name="j" value="戻る" />
	<input type="submit" name="c" value="トップ" />
	</center>

</form:form>
</body>
</html>