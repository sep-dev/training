<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 最初に表示されるページ -->
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>派遣人員管理システム</title>
	</head>
	<body>
		<div align="center">
			<h1>${message1}</h1>
			${message2}
			<form action="login">
				<p><input type="submit" value="ログイン" /></p>
			</form>
		</div>
	</body>