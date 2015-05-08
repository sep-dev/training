<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head>
	<body>

		<c:url value="/Addressup" var="messageUrl" />
		<a href="${messageUrl}">会員登録ページ</a>
		<br /><br />
		<c:url value="/Addresslist" var="messageUrl" />
		<a href="${messageUrl}">会員リスト表示</a>
	</body>
</html>
