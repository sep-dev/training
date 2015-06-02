<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派遣先編集</title>
</head>
<body>
	<p>
		<iframe src="${pageContext.request.contextPath}/MainMenu" width="200"
			height="550" align="left" frameBorder="0"></iframe>
	</p>
	<!-- (1)派遣先情報編集画面 -->
	<div align="center">
		<p>${message}</p>

		<form:form modelAttribute="staffUpdateModel">
			<table border="1">
				<input type="hidden" name="staffManId" value="${staffManId}" />
				<tr>
					<td><form:label path="staffId">
							<center>派遣社員名</center>
						</form:label></td>
					<td><form:select path="staffId" size="1" value="${staffId}">
							<c:forEach var="obj" items="${data1}" varStatus="status">
								<form:option value="${obj.staffId}" label="${obj.staffName}"
									selected="${obj.staffName}" />
								<c:out value="${obj.staffId}" />
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="clientId">
							<center>派遣先企業名</center>
						</form:label></td>
					<td><form:select path="clientId" size="1" value="${clientId}">
							<c:forEach var="obj" items="${data2}" varStatus="status">
								<form:option value="${obj.clientId}" label="${obj.clientName}"
									selected="${obj.clientId}" />
								<c:out value="${obj.clientId}" />
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="amountMonth">
							<center>単金</center>
						</form:label></td>
					<td><form:input path="amountMonth" size="20"
							value="${amountMonth}" /></td>
					<td><form:label path="conditions">
							<center>条件</center>
						</form:label></td>
					<td><form:input path="conditions" size="20"
							value="${conditions}" /></td>
				</tr>
				<tr>
					<td><form:label path="deductionUnitPrice">
							<center>控除単価</center>
						</form:label></td>
					<td><form:input path="deductionUnitPrice" size="20"
							value="${deductionUnitPrice}" /></td>
					<td><form:label path="overtimeRate">
							<center>超過単価</center>
						</form:label></td>
					<td><form:input path="overtimeRate" size="20"
							value="${overtimeRate}" /></td>
				</tr>
				<tr>
					<td><form:label path="site">
							<center>サイト</center>
						</form:label></td>
					<td><form:input path="site" size="20" value="${site}" /></td>
				</tr>
				<tr>
					<td><form:label path="startDate">
							<center>開始日</center>
						</form:label></td>
					<td><form:input path="startDate" size="3" value="${startDate}" />年
						<form:input path="startDate" size="3" value="${startDate}" />月 <form:input
							path="startDate" size="3" value="${startDate}" />日</td>
					<td><form:label path="endDate">
							<center>終了日</center>
						</form:label></td>
					<td><form:input path="endDate" size="3" value="${endDate}" />年
						<form:input path="endDate" size="3" value="${endDate}" />月 <form:input
							path="endDate" size="3" value="${endDate}" />日</td>
				</tr>
				<tr>
					<td><form:label path="staffManRemarks">
							<center>備考</center>
						</form:label></td>
					<td><form:textarea path="staffManRemarks" rows="5"
							value="${staffManRemarks}" /></td>
				</tr>
			</table>
			<input type="submit" name="update" value="確定">
		</form:form>


		<form action="${pageContext.request.contextPath}/LoginList"
			method="get">
			<input type="submit" value="戻る">
		</form>
	</div>
</body>
</html>