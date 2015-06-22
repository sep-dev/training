<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page session = "false" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出向先情報</title>
</head>
<body>
<a href="./" target="_top" name="home">ログアウト</a>
	<h1>出向先情報</h1>
		<form:form action="./syukkousagasu" modelAttribute="FormModel3" method="post">
		検索:<input type="text" name="name">
		<input type="submit" value="検索"><br>
		</form:form>
		<form action="./syukkousinki" method="GET">
	<input type="submit" value="新規登録">
</form>
<table border="1">
	<tr>
		<td>企業名</td>
			<th>郵便番号</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>最寄駅</th>
			<th>備考</th>
			<th>編集</th>

	</tr>

<c:forEach var="obj" items="${data}" varStatus="status">
	<tr>
		<td><c:out value="${obj.clientName}"></c:out></td>
			<th><c:out value="${obj.clientPostCode}"></c:out> </th>
			<th><c:out value="${obj.clientAdd}"></c:out></th>
			<th><c:out value="${obj.clientTel}"></c:out></th>
			<th><c:out value="${obj.clientNearestStation}"></c:out></th>
			<th><c:out value="${obj.clientRemarks}"></c:out></th>

			<th>
				<form action="./syukkouhen" method="GET">
					<input type="hidden" value="${obj.clientId}" name="id">
					<input type="submit" value="編集" >
				</form>
				<form action="./syukkousakuzyo" method="POST">
				<input type="submit" value="削除" onClick="return  window.confirm('削除しますか？');">
				<input type="hidden" value="${obj.clientId} " name="kesu">
				</form>
			</th>
	</tr>
	</c:forEach>
</table>
</body>
</html>