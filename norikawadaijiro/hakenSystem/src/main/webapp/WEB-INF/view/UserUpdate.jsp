<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者編集</title>
</head>
<body>
	<h1>管理者編集</h1>
	<form:form modelAttribute="formModel" action="Userupdate">
		<table frame="border" border="1" width="640">
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">ユーザー名称</td>
				<td width="400">
					<form:input path="username" value="${obj.loginUserName}"/>
				</td>
			</c:forEach>
			</tr>
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">ユーザーID</td>
				<td width="400">
					<form:input path="user" value="${obj.loginUser}"/>
				</td>
			</c:forEach>
			</tr>
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">パスワード</td>
				<td width="400">
					<form:input path="password" value="${obj.loginUserPass}"/>※半角英数字記号を使ってください
				</td>
			</c:forEach>
			</tr>
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">パスワード確認</td>
				<td width="400">
					<form:input path="passwordcheck" value="${obj.loginUserPass}"/>
				</td>
			</c:forEach>
			</tr>
		</table>
		<td>
			<input type="hidden" value="${updateloginUserId}" name="updateloginUserId">
			<input type="submit" value="確定">
			</td>
		</form:form>
		<form:form modelAttribute="dataset" action="UserItiran" method="get">
			<INPUT type="submit" value="戻る">
		</form:form>
</body>
</html>