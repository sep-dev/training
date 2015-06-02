<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>出向先編集</h1>
	<form:form modelAttribute="formModel" action="Syukkousakiupdate">
		<table frame="border" border="1" width="640">
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">出向先名称</td>
				<td width="400">
					<form:input path="clientname" value="${obj.clientName}"/>
				</td>
			</c:forEach>
			</tr>
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">電話番号</td>
				<td width="400">
					<form:input path="clienttel" value="${obj.clientTel}"/>
				</td>
			</c:forEach>
			</tr>
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">郵便番号</td>
				<td width="400">
					<form:input path="clientpostcode" value="${obj.clientPostCode}"/>
				</td>
			</c:forEach>
			</tr>
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">住所</td>
				<td width="400">
					<form:input path="clientadd" value="${obj.clientAdd}"/>
				</td>
			</c:forEach>
			</tr>
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">最寄駅</td>
				<td width="400">
					<form:input path="clientneareststation" value="${obj.clientNearestStation}"/>
				</td>
			</c:forEach>
			</tr>
			<tr>
			<c:forEach var="obj" items="${updatelist}">
				<td width="240">備考</td>
				<td width="400">
					<form:input path="clientremarks" value="${obj.clientRemarks}"/>
				</td>
			</c:forEach>
			</tr>
		</table>
		<td>
			<input type="hidden" value="${updateclientId}" name="updateclientId">
			<input type="submit" value="確定">
			</td>
		</form:form>
		<form:form modelAttribute="dataset" action="SyukkousakiItiran" method="get">
			<INPUT type="submit" value="戻る">
		</form:form>
</body>
</html>