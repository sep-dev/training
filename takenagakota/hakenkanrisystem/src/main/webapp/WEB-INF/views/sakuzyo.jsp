<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@page import="java.util.*"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>削除画面</title>
	</head>
	<body>
		削除しますか？
		<form action="./sakuzyo" method="POST">
			<input type="submit" value="はい">
			<input type="hidden" value="${kesu} " name="kesu">
		</form>
		<form action="./zenn" method="GET">
			<input type="submit" value="いいえ">
		</form>
	</body>
</html>