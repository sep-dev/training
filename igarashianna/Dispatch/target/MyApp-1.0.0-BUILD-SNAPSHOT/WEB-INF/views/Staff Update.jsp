<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派遣社員編集</title>
</head>
<body>
	<p>
		<iframe src="${pageContext.request.contextPath}/MainMenu" width="200"
			height="550" align="left" frameBorder="0"></iframe>
	</p>
	<!-- (2)派遣社員情報編集画面 -->
	<div align="center">
		<p>${Message}</p>

		<form:form modelAttribute="staffManUpdateModel">
			<table border="1">
				<form:hidden name="staffId" path="staffId" value="${Id}" />
				<tr>
					<td><form:label path="staffName">
							<center>社員名称</center>
						</form:label></td>
					<td><form:input path="staffName" size="20" value="${Name}" /></td>
				</tr>
				<tr>
					<td><form:label path="staffMail">
							<center>メールアドレス</center>
						</form:label></td>
					<td><form:input path="staffMail" size="20" value="${Mail}" /></td>
				</tr>
				<tr>
					<td><form:label path="staffTel">
							<center>電話番号</center>
						</form:label></td>
					<td><form:input path="staffTel" size="20" value="${Tel}" /></td>
					<td><form:label path="staffMobileTel">
							<center>携帯電話番号</center>
						</form:label></td>
					<td><form:input path="staffMobileTel" size="20"
							value="${MobileTel}" /></td>
				</tr>
				<tr>
					<td><form:label path="staffPostCode">
							<center>郵便番号</center>
						</form:label></td>
					<td><form:input path="staffPostCode" size="20"
							value="${PostCode}" /></td>
				</tr>
				<tr>
					<td><form:label path="staffAdd">
							<center>住所</center>
						</form:label></td>
					<td><form:input path="staffAdd" size="20" /></td>
				</tr>
				<tr>
					<td><form:label path="staffNearestStation">
							<center>最寄駅</center>
						</form:label></td>
					<td><form:input path="staffNearestStation" size="20"
							value="${Station}" /></td>
					<td><form:label path="affliationId">
							<center>所属企業</center>
						</form:label> <form:select path="affliationId" size="1" value="${affliationId}">
							<c:forEach var="obj" items="${data1}" varStatus="status">
								<form:option value="${obj.affliationId}"
									label="${obj.affliationName}" selected="" />
								<c:out value="${obj.affliationId}" />
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="staffRemarks">
							<center>備考</center>
						</form:label></td>
					<td><form:input path="staffRemarks" size="20" /></td>
				</tr>
			</table>
			<input type="submit" name="update" value="確定">
		</form:form>

		<form action="${pageContext.request.contextPath}/Staff" method="get">
			<input type="submit" value="戻る">
		</form>
	</div>
</body>
</html>