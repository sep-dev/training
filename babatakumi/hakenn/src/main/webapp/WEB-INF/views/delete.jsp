<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
	<body>

		<div align="center">
			<h2>${h2}</h2>


			<form:form modelAttribute="SqlModel2">
				<c:forEach var="obj" items="${list}" varStatus="status">
					<h3>${list}</h3>
						<c:choose>
							<c:when test="${from == 1}">
								<input type="hidden" value="${obj.loginUserID}" name="id">
								<input type="submit" value="YES" name="delyes">
							</c:when>
							<c:when test="${from == 2}">
								<input type="hidden" value="${obj.clientId}" name="id">
								<input type="submit" value="YES" name="delyes">
							</c:when>
							<c:when test="${from == 3}">
								<input type="hidden" value="${obj.affiliationId}" name="id">
								<input type="submit" value="YES" name="delyes">
							</c:when>
							<c:when test="${from == 4}">
								<input type="hidden" value="${obj.staffId}" name="id">
								<input type="submit" value="YES" name="delyes">
							</c:when>
							<c:when test="${from == 5}">
								<input type="hidden" value="${obj.staffManId}" name="id">
								<input type="submit" value="YES" name="delyes">
							</c:when>
						</c:choose>
				</c:forEach>
			</form:form>

			<a href="${home}" target="info" >
				<input type="submit" value="NO">
			</a>
		</div>
	</body>
</html>