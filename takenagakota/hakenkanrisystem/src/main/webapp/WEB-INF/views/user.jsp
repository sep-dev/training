<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.*"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ログインユーザー</title>
	</head>
	<body>
			<a href="./" target="_top" name="home">ログアウト</a>
		<h1>ログインユーザー情報</h1>
			<p>${dame}</p>
		<form:form action="./usersagasu" modelAttribute="FormModel4" method="post">
			検索:<input type="text" name="name">
			<input type="submit" value="検索"><br>
		</form:form>
		<form action="./usersinki" method="GET">
			<input type="submit" value="新規登録">
		</form>
		<table border="1">
			<tr>
				<td>ログインユーザー名称</td>
				<th>ログインユーザー</th>
				<th>パスワード</th>
				<th>編集</th>
			</tr>
		<c:forEach var="obj" items="${data}" varStatus="status">
			<tr>
				<td><c:out value="${obj.loginUserName}"></c:out></td>
				<th><c:out value="${obj.loginUser}"></c:out> </th>
				<th><c:out value="${obj.loginUserPass}"></c:out></th>
					<th>
				<form action="./userhen" method="POST">
					<input type="hidden" value="${obj.LoginUserID}" name="id">
					<input type="submit" value="編集" >
				</form>
				<form action="./usersakuzyo" method="POST">
					<input type="submit" value="削除" onClick="return  window.confirm('削除しますか？');">
					<input type="hidden" value="${obj.LoginUserID} " name="kesu">
				</form>
				</th>
				</tr>
		</c:forEach>
		</table>
	</body>
</html>