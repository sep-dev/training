<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>

	<div align = "center">
		<h2>${h2}</h2>
		<form:form modelAttribute="SqlModel2">
			<form:errors path="SqlModel2" element="div" />
			<!-- 更新 -->
			<c:if test="${list != null}">
				<input type="hidden" value="1" name="up">
					<table border="1" style="width:500px">
						<c:forEach var="obj" items="${list}" varStatus="status">
						<input type="hidden" value="${obj.loginUserID}" name="id">
							<tr><td><label>ユーザー名称</label></td>
								<td><input type="text" name="name" value="${obj.loginUserName}"  style="width : 100%"></td></tr>
							<tr><td><label>ユーザーID</label></td>
								<td><input type="text" name="loginUser"  value="${obj.loginUser}" style="width : 100%"></td></tr>
							<tr><td><label>パスワード</label></td>
								<td><input type="password" name="loginUserPass"  value="${obj.loginUserPass}"  style="width : 100%"></td></tr>
							<tr><td><label>パスワード確認</label></td>
								<td><input type="password" name="loginUserPass_second"style="width : 100%"></td></tr>
						</c:forEach>
					</table>
			</c:if>

				<form:errors path="SqlModel2" element="div" />
				<!-- 新規作成 -->
				<c:if test="${list == null}">
					<input type="hidden" value="2" name="up">
						<table border="1" style="width:500px">
							<tr><td><label>ユーザー名称</label></td>
								<td><input type="text" name="name" style="width : 100%"></td></tr>
							<tr><td><label>ユーザーID</label></td>
								<td><input type="text" name="loginUser" style="width : 100%"></td></tr>
							<tr><td><label>パスワード</label></td>
								<td><input type="password" name="loginUserPass" style="width : 100%"></td></tr>
							<tr><td><label>パスワード確認</label></td>
								<td><input type="password" name="loginUserPass_second" style="width : 100%"></td></tr>
						</table>
				</c:if>
			<input type="submit" value="${button}" name="change">
		</form:form>

		<a href="/spring/menu/User" target="info" >
			<input type="submit" value=戻る name="back">
		</a>
	</div>

</body>
</html>