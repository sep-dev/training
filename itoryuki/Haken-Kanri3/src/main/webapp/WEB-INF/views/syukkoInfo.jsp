<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 出向元登録/編集のページ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8)">
		<script src="<c:url value="/resources/js/sakujo.js" />" type="text/javascript"></script>
		<title>出向先情報</title>
	</head>
	<body>
	<div align="center">
		<h2>出向先情報一覧</h2>
		<font size="5" color="red">${message}</font>
	</div>
	<br>
	<!-- 検索-->
		<form action="syukkoSearch" method="post">
			検索：<input type="text" maxlength="20" name="csearch">　<input type="submit" value="検索"/>
		</form>
	<!-- 新規登録 -->
		<form action="syukkoInGamen">
			<p><input type="submit" value="新規登録" /></p>
		</form>
		<table border=1 width="100%">
			<tr><th>企業名</th>
			<th>郵便番号</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>最寄駅</th>
			<th>備考</th>
			<th>編集</th></tr>
		<c:forEach var="obj" items="${data}" >
			<tr><td rowspan=2>
						<!-- 出向先情報編集へ -->
						<a href="syukkoUpLink ?value='${obj.clientId}'" name="clientid" target="contents"><c:out value="${obj.clientName}"/></a></td>
			<td rowspan=2><c:out value="${obj.clientPostCode}"/></td>
			<td rowspan=2><c:out value="${obj.clientAdd}" /></td>
			<td rowspan=2><c:out value="${obj.clientTel}"/></td>
			<td rowspan=2><c:out value="${obj.clientNearestStation}"/></td>
			<td rowspan=2><c:out value="${obj.clientRemarks}"/></td>
			<td width="40px">
				<form action="syukkoUpButton">
					<input type="hidden" value="${obj.clientId}" name="clientid">
					<input type="submit" value="編集" />
				</form></td></tr>
			<tr><td width="40px">
				<form action="syukkoDelete" method="post">
					<input type="hidden" value="${obj.clientId}" name="clientid">
					<input type="submit" value="削除" onClick="return kakunin();">
				</form></td></tr>
		</c:forEach>
		</table>
	</body>
</html>