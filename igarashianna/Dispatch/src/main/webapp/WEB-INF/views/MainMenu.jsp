<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>メインメニュー</title>
</head>
<body>

	<!-- メインメニューリンク -->
	<center>
		<br />
		<br />
		<p>メインメニュー</p>
		<br /> <a href="${pageContext.request.contextPath}/LoginList"
			target="_top">派遣先情報</a> <br /> <a
			href="${pageContext.request.contextPath}/Staff" target="_top">派遣社員情報</a>
		<br /> <a href="${pageContext.request.contextPath}/Affliation"
			target="_top">所属元情報</a> <br /> <a
			href="${pageContext.request.contextPath}/Client" target="_top">出向先情報</a>
		<br /> <a href="${pageContext.request.contextPath}/LoginUserList"
			target="_top">ユーザ管理</a> <br /> <br /> <br /> <br /> <a
			href="${pageContext.request.contextPath}/" target="_top">ログアウト</a>
	</center>
</body>
</html>