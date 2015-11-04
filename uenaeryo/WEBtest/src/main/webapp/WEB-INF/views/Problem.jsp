<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">

	<!-- 四字熟語問題 -->

	<title>四字熟語問題</title>
</head>
<body>

<center><h1>夏休み宿題</h1>

${pic}
<form:form modelAttribute="formModel" >

<table border=1>
<tr>
${title}
</tr>
<tr>
${problem}
</tr>
<tr>
${qst}
</tr>
</table>


<input type="submit" name="Retrun" value="戻る">
<input type="submit" name="kakunin" value="確認">


</form:form>
</center>

</body>

</html>
