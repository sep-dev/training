<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所属元編集</title>
</head>
<body>
	<p>
		<iframe src="${pageContext.request.contextPath}/MainMenu" width="200"
			height="550" align="left" frameBorder="0"></iframe>
	</p>
	<!-- (3)所属元情報編集画面 -->
	<div align="center">
		<p>${message}</p>

			<form:form modelAttribute="affliationUpdateModel">
			<table border="1">
				<form:hidden name="affliationId" path="affliationId" value="${Id}" />
				<tr>
					<td><form:label path="affliationName">
							<center>所属元名称</center>
						</form:label></td>
					<td><form:input path="affliationName" size="20"
							value="${Name}" /></td>
				<tr>
					<td><form:label path="affliationTel">
							<center>電話番号</center>
						</form:label></td>
					<td><form:input path="affliationTel" size="20" value="${Tel}" /></td>
				</tr>
				<tr>
					<td><form:label path="affliationPostCode">
							<center>郵便番号</center>
						</form:label></td>
					<td><form:input path="affliationPostCode" size="20"
							value="${PostCode}" /></td>
				</tr>
				<tr>
					<td><form:label path="affliationAdd">
							<center>住所</center>
						</form:label></td>
					<td><form:input path="affliationAdd" size="20" /></td>
				</tr>
				<tr>
					<td><form:label path="affliationNearestStation">
							<center>最寄駅</center>
						</form:label>
					<td><form:input path="affliationNearestStation" size="20"
							value="${Station}" /></td>
				</tr>
				<tr>
					<td><form:label path="affliationRemarks">
							<center>備考</center>
						</form:label></td>
					<td><form:input path="affliationRemarks" size="20"
							value="${Remarks}" /></td>
				</tr></table>
				<input type="submit" name="update" value="確定">
			</form:form>

		<form action="${pageContext.request.contextPath}/Affliation"
			method="get">
			<input type="submit" value="戻る">
		</form>
	</div>
</body>
</html>