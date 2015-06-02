<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出向先登録</title>
</head>
<body>
	<p>
		<iframe src="${pageContext.request.contextPath}/MainMenu" width="200"
			height="550" align="left" frameBorder="0"></iframe>
	</p>
	<!-- ➍出向先情報登録画面 -->
	<div align="center">
		<h1>${title}</h1>
		<p>${message}</p>

		<form:form modelAttribute="clientModel">
			<table border="1">
				<tr>
					<td><form:label path="clientName">
							<center>出向先名称</center>
						</form:label></td>
					<td><form:input path="clientName" size="20" /></td>
				</tr>
				<tr>
					<td><form:label path="clientTel">
							<center>電話番号</center>
						</form:label></td>
					<td><form:input path="clientTel" size="20" /></td>
				</tr>
				<tr>
					<td><form:label path="clientPostCode">
							<center>郵便番号</center>
						</form:label></td>
					<td><form:input path="clientPostCode" size="20" /></td>
				<tr>
					<td><form:label path="clientAdd">
							<center>住所</center>
						</form:label></td>
					<td><form:input path="clientAdd" size="20" /></td>
				</tr>
				<tr>
					<td><form:label path="clientNearestStation">
							<center>最寄駅</center>
						</form:label></td>
					<td><form:input path="clientNearestStation" size="20" /></td>
				<tr>
					<td><form:label path="clientRemarks">
							<center>備考</center>
						</form:label></td>
					<td><form:input path="clientRemarks" size="20" /></td>
				</tr>
			</table>
			<input type="submit" value="登録">
		</form:form>

		<form action="${pageContext.request.contextPath}/Client" method="get">
			<input type="submit" value="戻る">
		</form>
	</div>
</body>
</html>