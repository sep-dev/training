<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ログインに成功した場合はこのページへ -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>成功！</title>
	</head>
	<body>
		<div align="center">
			<h1>ログイン成功！！！</h1>
		<!-- 派遣先情報一覧ページへ -->
			<form action="Menu">
				<p><input type="submit" name="menu" value="メニューへ" /></p>
			</form>
			<form action="logout">
				<p><input type="submit" value="ログアウト" /></p>
			</form>
		</div>
	</body>
</html>