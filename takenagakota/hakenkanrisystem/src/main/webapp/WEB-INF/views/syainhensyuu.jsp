<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page session = "false" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派遣社員情報</title>
</head>
<body>
<p>${message}</p>
	<h1>派遣者社員情報</h1>
		<form:form action="./syainsagasu" modelAttribute="FormModel" method="post">
		検索:<input type="text" name="name">
		<input type="submit" value="検索"><br>
		</form:form>
		<form action="./syainsinki" method="GET">
	<input type="submit" value="新規登録">
</form>
<table border="1">
	<tr>
		<td>社員名</td>
			<th>メールアドレス</th>
			<th>郵便番号</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>携帯番号</th>
			<th>最寄駅</th>
			<th>所属企業名</th>
			<th>備考</th>
			<th>編集</th>
	</tr>

<c:forEach var="obj" items="${data}" varStatus="status">
	<tr>
		<td><c:out value="${obj.staffName}"></c:out></td>
			<th><c:out value="${obj.staffMail}"></c:out> </th>
			<th><c:out value="${obj.staffPostCode}"></c:out></th>
			<th><c:out value="${obj.staffAdd}"></c:out></th>
			<th><c:out value="${obj.staffTel}"></c:out></th>
			<th><c:out value="${obj.staffMobileTel}"></c:out></th>
			<th><c:out value="${obj.staffNearestStation}"></c:out></th>
			<th><c:out value="${obj.affiliationName}"></c:out></th>
			<th><c:out value="${obj.staffRemarks}"></c:out></th>

			<th>
				<form action="./syainhen" method="GET">
					<input type="hidden" value="${obj.staffID}" name="hensyuu">
					<input type="submit" value="編集" >
				</form>
				<form action="./syainsakuzyo" method="POST">
				<input type="submit" value="削除" onClick="return  window.confirm('削除しますか？');">
				<input type="hidden" value="${obj.staffID} " name="kesu">
				</form>
			</th>
	</tr>

	</c:forEach>
</table>

</body>
</html>