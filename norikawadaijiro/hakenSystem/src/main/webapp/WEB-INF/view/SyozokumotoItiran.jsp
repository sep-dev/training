<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate;"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>所属元情報</h1>
<form:form modelAttribute="formModel" action="Syozokumotokensaku">
<table>
	<tr>
		<td><form:label path="kensakuword">検索:</form:label></td>
		<td><form:input path="kensakuword" size="20" maxlength="10" /></td>
		<td><input type="submit" value="検索"></td>
	</tr>
</table>
</form:form>
<form:form modelAttribute="dataSet" action="Syozokumototouroku">
<input type="submit" value="新規登録">
</form:form>
<br />
	<table frame="border" border="1">
		<tr>
			<td>企業名</td>
			<td>郵便番号</td>
			<td>住所</td>
			<td>電話番号</td>
			<td>最寄駅</td>
			<td>備考</td>
			<td>編集</td>
		</tr>
		<tr>
			<c:forEach var="obj" items="${list}">
				<tr height="50">
				<form:form action="Syozokumotohensyu" modelAttribute="formModel">
					<td><c:out value="${obj.affiliationName}" /></td>
					<td><c:out value="${obj.affiliationPostCode}" /></td>
					<td><c:out value="${obj.affiliationAdd}" /></td>
					<td><c:out value="${obj.affiliationTel }" /></td>
					<td><c:out value="${obj.affiliationNearestStation}" /></td>
					<td><c:out value="${obj.affiliationRemarks}" /></td>
					<td><input type="hidden" value="${obj.affiliationId}" name="updateaffiliationId">
						<input type="submit" value="編集">
				</form:form>
				<form:form action="Delete" modelAttribute="formModel">
					<td><input type="hidden" value="${obj.affiliationId}" name="deleteId">
					<input type="hidden" value="tblAffiliation" name="tablename">
					<input type="hidden" value="affiliationId" name="primary">
					<input type="hidden" value="redirect:SyozokumotoItiran" name="Itiran">
					<input type="submit" value="削除"></td>
				</form:form>
				</tr>
			</c:forEach>
	</table>
</body>
</html>