<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		<br>
		<div align="center">
			${message}
			<form:form modelAttribute="form">
				<form:errors path="*" element="div" />
				<table border="1">
					<caption>${title}</caption>
					<tr><th>社員名称</th><td><form:input path="name" value="${staffName}"/></td></tr>
					<tr><th>メールアドレス</th><td><form:input path="email" value="${staffEmail}" /></td></tr>
					<tr><th>電話番号</th><td><form:input path="tel" value="${staffTel}"/></td></tr>
					<tr><th>携帯番号</th><td><form:input path="mobiletel" value="${staffMobiletel}" /></td></tr>
					<tr><th>郵便番号</th><td><form:input path="postcode" value="${staffPostcode}" /></td></tr>
					<tr><th>住所</th><td><textarea name="add">${staffAdd}</textarea></td></tr>
					<tr><th>最寄駅</th><td><form:input path="neareststation" value="${staffNeareststation}" /></td></tr>
					<tr><th>所属企業</th><td>
						<select name="affiliationid">
							<option value="-1">選択</option>
							<c:forEach var="obj" items="${List}" varStatus="status">
								<c:if test="${affid ==obj.affiliationId}">
									<option value="${obj.affiliationId}" selected><c:out value="${obj.affiliationName}"/></option>
								</c:if>
								<c:if test="${affid !=obj.affiliationId}">
									<option value="${obj.affiliationId}"><c:out value="${obj.affiliationName}"/></option>
								</c:if>
							</c:forEach>
						</select>
					</td></tr>
					<tr><th>備考</th><td><textarea name="remarks" rows="3">${staffRemarks}</textarea></td></tr>
				</table>
				<br>
				<input type="submit" value="${submit}">
			</form:form>
			<a href="/controller/haken/shainInfo" target="right">
				<input type="button" value="戻る">
			</a>
		</div>
	</body>
</html>