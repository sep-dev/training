<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>初期画面</title>
		<style>
		h1	{font-size:16pt; background-color:#CCCCFF; padding:3px;}
		</style>
	</head>
	<body>
		<h1>住所録プログラム(SpringMVC)</h1>

		<c:url value="/Addressbook" var="messageUrl" />
		<a href="${messageUrl}">会員登録ページ</a>
		<br /><br />
		<c:url value="/Addresslist" var="messageUrl" />
		<a href="${messageUrl}">会員リスト表示</a>
	</body>
</html>
