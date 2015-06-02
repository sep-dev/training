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
			<c:if test="${flag !=1}">
				${message}
				<form:form modelAttribute="form">
					<form:errors path="*" element="div" />
					<table border="1">
						<caption>${title}</caption>
						<tr><th>出向先名称</th><td><form:input path="name" value="${clientName}"/></td></tr>
						<tr><th>電話番号</th><td><form:input path="tel" value="${clientTel}" /></td></tr>
						<tr><th>郵便番号</th><td><form:input path="postcode" value="${clientPostcode}" /></td></tr>
						<tr><th>住所</th><td><textarea name="add">${clientAdd}</textarea></td></tr>
						<tr><th>最寄駅</th><td><form:input path="neareststation" value="${clientNeareststation}" /></td></tr>
						<tr><th>備考</th><td><textarea name="remarks" rows="3">${clientRemarks}</textarea></td></tr>
					</table>
					<br>
					<input type="submit" value="${submit}">
				</form:form>
				<a href="/controller/haken/shukkosakiInfo" target="right">
					<input type="button" value="戻る">
				</a>
			</c:if>
			<c:if test="${flag ==1}">
				${message}
				<br><br>
				<a href="/controller/haken/shukkosakiInfo" target="right">
					<input type="button" value="戻る">
				</a>
			</c:if>
		</div>
	</body>
</html>