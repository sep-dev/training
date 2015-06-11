<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派遣先情報一覧</title>
</head>
<body>
	<h1>派遣先情報一覧</h1>
	表示期間：
	<form:form modelAttribute="dataSet" action="HakensakiKensaku">
	<form:select path="year">
		<%
			for(int i=2010;i<=2016;i++){
		%>
				<form:option value='<%= String.format("%02d",i) %>'><%= i %></form:option>
		<%
			}
		%>
	</form:select>
 	年
	<form:select path="month">
		<%
			for(int i=1;i<=12;i++){
		%>
				<form:option value='<%= String.format("%02d",i) %>'><%= i %></form:option>
		<%
			}
		%>
	</form:select>
	月
	<br />

	<table>
		<tr>
			<td><form:label path="kensakuword">検索:</form:label></td>
			<td><form:input path="kensakuword" size="20" maxlength="10" /></td>
			<td><input type="submit" value="検索"></td>
		</tr>
	</table>
	</form:form>
	<form:form modelAttribute="dataSet" action="HakensakiTouroku">
		<input type="submit" value="新規登録">
	</form:form>

	<br />
		<table frame="border" border="3">
			<tr>
				<td>派遣社員名</td>
				<td>派遣先企業名</td>
				<td>単金</td>
				<td>条件</td>
				<td>控除単価</td>
				<td>超過単価</td>
				<td>サイト</td>
				<td>開始日</td>
				<td>終了日</td>
				<td>備考</td>
				<td>編集</td>
			</tr>
			<tr>
				<c:forEach var="obj" items="${list}">
					<tr height="50">

							<td><c:out value="${obj.staffName}" /></td>
							<td><c:out value="${obj.clientName}" /></td>
							<td><c:out value="${obj.amountMonth}" /></td>
							<td><c:out value="${obj.conditions}" /></td>
							<td><c:out value="${obj.deductionUnitPrice}" /></td>
							<td><c:out value="${obj.overtimeRate}" /></td>
							<td><c:out value="${obj.site}" /></td>
							<td><c:out value="${obj.startDate}" /></td>
							<td><c:out value="${obj.endDate}" /></td>
							<td><c:out value="${obj.staffManRemarks}" /></td>

							<form:form action="HakensakiHensyu" modelAttribute="formModel">
								<td><input type="hidden" value="${obj.staffManId}" name="updatestaffManId">
									<input type="submit" value="編集"></td>
							</form:form>
							<form:form action="Delete" modelAttribute="formModel">
								<td><input type="hidden" value="${obj.staffManId}" name="deleteId">
									<input type="hidden" value="tblStaffManagement" name="tablename">
									<input type="hidden" value="staffManId" name="primary">
									<input type="hidden" value="redirect:HakensakiItiran" name="Itiran">
									<input type="submit" value="削除"></td>
						</form:form>
					</tr>
				</c:forEach>
			</tr>
		</table>
</body>
</html>