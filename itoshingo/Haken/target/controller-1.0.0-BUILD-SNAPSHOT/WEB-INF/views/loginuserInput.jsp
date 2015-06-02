<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		<br>
		<div align="center">
			${message}
			<form:form modelAttribute="form">
				<form:errors path="*" element="div" />
				<table border="1">
					<caption>${title}</caption>
					<tr><th>ユーザー名称</th><td><form:input path="username" value="${loginUserName}"/></td></tr>
					<tr><th>ユーザーID</th><td><form:input path="user" value="${loginUser}" /></td></tr>
					<tr><th>パスワード</th><td><form:password path="pass1" showPassword="on"/></td></tr>
					<tr><th>パスワード確認</th><td><form:password path="pass2" showPassword="on"/></td></tr>
				</table>
				<br>
				<input type="submit" value="${submit}">
			</form:form>

			<a href="/controller/haken/loginuserInfo" target="right">
				<input type="button" name="id" value="戻る">
			</a>
		</div>
	</body>
</html>