<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8">
<title>ログイン画面</title>
</head>
<body>
	<!-- ⓪ログイン画面 -->
	<div align="center">
		<br />
		<h1>${title}</h1>
		<form:form modelAttribute="loginUserModel"
			action="<c:url value="/j_spring_security_check"/>" method="POST">
 	 ユーザーID　：　<input type="text" name="j_loginUser" />
			<br />
			<br />
 	 パスワード　：　<input type="password" name="j_loginUserPass" />
			<br />
			<br />
			<input type="submit" value="ログイン" />
		</form:form>
	</div>
	<!-- メインメニューリンク 最終段階で外す -->
	メインメニュー
	<br />
	<a href="${pageContext.request.contextPath}/LoginList">派遣先情報</a>
	<br />
	<a href="${pageContext.request.contextPath}/Staff">派遣社員情報</a>
	<br />
	<a href="${pageContext.request.contextPath}/Affliation">所属元情報</a>
	<br />
	<a href="${pageContext.request.contextPath}/Client">出向先情報</a>
	<br />
	<a href="${pageContext.request.contextPath}/LoginUserList">ユーザ管理</a>
	<br />
	<br />
	<br />
	<br />
	<a href="${pageContext.request.contextPath}/">ログアウト</a>
</body>
</html>
