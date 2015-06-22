<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@page import="java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="./" target="_top" name="home">ログアウト</a>
	<div align="center">
<p>${message}</p>
<form:form modelAttribute="FormModel4" action="./userhensyu" >
		<table border=1 style="width:500px">
		<caption>管理者編集</caption>
<tr>
	<td colspan="2">管理者編集</td>
				<td colspan="2"><form:input type="text" path="name" value="${loginUserName}" maxlength="255" size="30"/></td>
</tr>

<tr>
	<td colspan="2" width="30">ユーザーID</td>
		<th  colspan="2"><form:input type="text" path="loginID" value="${loginUser}" maxlength="20" size="30" /></th>
</tr>
<tr>
	<td colspan="2">パスワード</td>
		<th colspan="2"><form:input type="pass" path="pass" maxlength="8" size="30"/></th>
</tr>
<tr>
	<td colspan="2">パスワード確認</td>
		<th colspan="2"><form:input type="pass" path="kakuninn" maxlength="255" size="30"/></th>
</tr>
</table>
	<input type="hidden" value="${id}" name="id">
		<input type="submit" value="登録"/>
			</form:form>
			<form action="./user" method="GET">
			<input type="submit" value="戻る">
			</form>
</div>

</body>
</html>