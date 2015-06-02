<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page session = "false" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>派遣先詳細一覧</title>
</head>
<body>
	<h1>派遣先詳細一覧</h1>
		表示時間:
<form:form action="./kensaku" modelAttribute="FormModel" method="post">
	<select name="nen">
<option value="2013">2013</option>
<option value="2014">2014</option>

	</select>
		年
	<select name="gatu">
		<option value="01">1</option>
		<option value="02">2</option>
		<option value="03">3</option>
		<option value="04">4</option>
		<option value="05">5</option>
		<option value="06">6</option>
		<option value="07">7</option>
		<option value="08">8</option>
		<option value="09">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
	</select>
<br>
		検索<input type="text" name="name">
		<input type="submit" value="検索">
</form:form>

<form action="./sinki" method="GET">
	<input type="submit" value="新規登録">
</form>
<table border="1">
	<tr>
		<td>派遣社員名</td>
			<th>派遣先企業名</th>
			<th>単金</th>
			<th>条件</th>
			<th>控除単価</th>
			<th>超過単価</th>
			<th>サイト</th>
			<th>開始日</th>
			<th>終了日</th>
			<th>備考</th>
			<th>編集</th>
	</tr>

<c:forEach var="obj" items="${data}" varStatus="status">
	<tr>
		<td><c:out value="${obj.staffName}"></c:out></td>
			<th><c:out value="${obj.clientName}"></c:out> </th>
			<th><c:out value="${obj.amountMonth}"></c:out></th>
			<th><c:out value="${obj.conditions}"></c:out></th>
			<th><c:out value="${obj.deductions}"></c:out></th>
			<th><c:out value="${obj.overtimeRate}"></c:out></th>
			<th><c:out value="${obj.site}"></c:out></th>
			<th><c:out value="${obj.startDate}"></c:out></th>
			<th><c:out value="${obj.endDate}" ></c:out></th>
			<th><c:out value="${obj.staffManRemarks}"></c:out></th>
			<th>
				<form action="./hen" method="POST">
					<input type="hidden" value="${obj.staffID}" name="hensyuu">
					<input type="submit" value="編集" >
				</form>
				<form action="./sakuzyo" method="GET">
				<input type="submit" value="削除">
				<input type="hidden" value="${staffID} " name="kesu">
				</form>
			</th>
	</tr>

	</c:forEach>
</table>
</body>
</html>