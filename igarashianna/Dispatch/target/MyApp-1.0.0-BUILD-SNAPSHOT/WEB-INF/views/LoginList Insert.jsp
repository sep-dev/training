<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派遣先登録</title>
</head>
<body>
	<p>
		<iframe src="${pageContext.request.contextPath}/MainMenu" width="200"
			height="550" align="left" frameBorder="0"></iframe>
	</p>
	<!-- ➊派遣先情報登録画面 -->
	<br />
	<a href="${pageContext.request.contextPath}/" target="_top">ログアウト</a>
	<div align="center">
		<h1>${title}</h1>
		<p>${message}</p>

		<form:form modelAttribute="staffModel" item="${data}">
			<table border="1">
				<tr>
					<td><form:label path="staffId">
							<center>派遣社員名</center>
						</form:label></td>
					<td><form:select path="staffId" size="1">
							<form:option value="選択" label="選択" selected="" />
							<c:forEach var="obj" items="${data1}" varStatus="status">
								<form:option value="${obj.staffId}" label="${obj.staffName}" />
								<c:out value="${obj.staffId}" />
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="clientId">
							<center>派遣先企業</center>
						</form:label></td>
					<td><form:select path="clientId" size="1">
							<form:option value="選択" label="選択" selected="" />
							<c:forEach var="obj" items="${data2}" varStatus="status">
								<form:option value="${obj.clientId}" label="${obj.clientName}" />
								<c:out value="${obj.clientId}" />
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="amountMonth">
							<center>単金</center>
						</form:label></td>
					<td><form:input path="amountMonth" size="20" value="0" /></td>
					<td><form:label path="conditions">
							<center>条件</center>
						</form:label></td>
					<td><form:input path="conditions" size="20" /></td>
				</tr>
				<tr>
					<td><form:label path="deductionUnitPrice">
							<center>控除単価</center>
						</form:label></td>
					<td><form:input path="deductionUnitPrice" size="20" value="0" /></td>
					<td><form:label path="overtimeRate">
							<center>超過単価</center>
						</form:label></td>
					<td><form:input path="overtimeRate" size="20" value="0" /></td>
				</tr>
				<tr>
					<td><form:label path="site">
							<center>サイト</center>
						</form:label></td>
					<td><form:input path="site" size="20" /></td>
				</tr>
				<tr>
					<td><form:label path="startDate">
							<center>開始日</center>
						</form:label></td>
					<td><form:input path="startDate" size="3" />年 <form:input
							path="startDate" size="3" />月 <form:input path="startDate"
							size="3" />日</td>
					<td><form:label path="endDate">
							<center>終了日</center>
						</form:label></td>
					<td><form:input path="endDate" size="3" />年 <form:input
							path="endDate" size="3" />月 <form:input path="endDate" size="3" />日</td>
				</tr>
				<tr>
					<td><form:label path="staffManRemarks">
							<center>備考</center>
						</form:label></td>
					<td><form:textarea path="staffManRemarks" rows="5" /></td>
				</tr>
			</table>
			<input type="submit" value="登録">
		</form:form>
		<form action="${pageContext.request.contextPath}/LoginList"
			method="get">
			<input type="submit" value="戻る">
		</form>


	</div>
</body>
</html>