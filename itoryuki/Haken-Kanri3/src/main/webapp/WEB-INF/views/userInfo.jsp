<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ログインユーザー一覧のページ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8)">
		<script src="<c:url value="/resources/js/sakujo.js" />" type="text/javascript"></script>
		<title>ログインユーザー一覧</title>
	</head>
	<body>
	<div align="center">
		<h2>ログインユーザー一覧</h2>
		<font size="5" color="red">${message}</font>
	</div>
	<br>
	<!-- 検索-->
		<form action="userSearch" method="post">
			検索：<input type="text" maxlength="20" name="usearch">　<input type="submit" value="検索" />
		</form>
	<!-- 新規登録 -->
		<form action="userInsert1">
			<p><input type="submit" value="新規登録" /></p>
		</form>
		<table border=1 width="99%" >
			<tr><th>ログインユーザー名称</th>
			<th>ログインユーザー</th>
			<th>パスワード</th>
			<th>編集</th></tr>
		<c:forEach var="obj" items="${data}" >
			<tr><td rowspan=2><c:out value="${obj.loginUserName}"/></td>
			<td rowspan=2><c:out value="${obj.loginUser}"/></td>
			<td rowspan=2><c:out value="${obj.loginUserPass}" /></td>
			<td width="40px">
				<form action="userUpdate1">
					<input type="hidden" value="${obj.loginUserId}" name="loginuserid">
					<input type="submit" value="編集" />
				</form></td></tr>
			<tr><td width="40px">
				<form action="userDelete" method="post">
					<input type="hidden" value="${obj.loginUserId}" name="loginuserid">
					<input type="submit" value="削除" onClick="return kakunin();">
				</form></td></tr>
		</c:forEach>
		</table>
	</body>
</html>