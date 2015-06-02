<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログインユーザー編集</title>
</head>
<body>
	<p>
		<iframe src="${pageContext.request.contextPath}/MainMenu" width="200"
			height="550" align="left" frameBorder="0"></iframe>
	</p>
	<!-- ⑸ログインユーザー情報編集画面 -->
<div align="center">		<h1>${title}</h1>
		<p>${message}</p>

			<form:form modelAttribute="loginUserUpdateModel">
			<table border="1">
				<form:hidden name="loginUserID" path="loginUserID" value="${Id}" />
				<tr>
					<td><form:label path="loginUserName">
							<center>ユーザー名称</center>
						</form:label></td>
					<td><form:input path="loginUserName" size="20" value="${Name}" /></td>
				<tr>
					<td><form:label path="loginUser">
							<center>ユーザーID</center>
						</form:label></td>
					<td><form:input path="loginUser" size="20" value="${User}" /></td>
				</tr>
				<tr>
					<td><form:label path="loginUserPass">
							<center>パスワード</center>
						</form:label></td>
					<td><form:password path="loginUserPass" size="20"
							manlength="10" />※半角英数字記号を使ってください</td>
				</tr>
				<tr>
					<td><form:label path="loginUserPassCheck">
							<center>パスワード確認</center>
						</form:label></td>
					<td><form:password path="loginUserPassCheck" size="20"
							manlength="10" /></td>
				</tr></table>
				<tr>
					<input type="submit" name="update" value="確定" />
				</tr>


			</form:form>

		<form action="${pageContext.request.contextPath}/LoginUserList"
			method="get">
			<input type="submit" value="戻る">
		</form>
</div>
</body>
</html>