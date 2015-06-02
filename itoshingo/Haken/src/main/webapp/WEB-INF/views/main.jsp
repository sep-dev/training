<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>派遣人員管理システム</title>
	</head>
	<body>
		<div align="right">
		<iframe name="right" src="/controller/haken/hakensakiInfo" width="85%" height="100%" style="float:right"></iframe>
		</div>

		<br><br><br>
		<h3>メインメニュー</h3>
		<br>
		<a href="/controller/haken/hakensakiInfo" target="right">派遣先情報</a>
		<br>
		<a href="/controller/haken/shainInfo" target="right">派遣社員情報</a>
		<br>
		<a href="/controller/haken/shozokumotoInfo" target="right">所属元情報</a>
		<br>
		<a href="/controller/haken/shukkosakiInfo" target="right">出向先情報</a>
		<br>
		<a href="/controller/haken/loginuserInfo" target="right">ユーザー管理</a>

		<br><br><br>
		<a href="/controller/logout">ログアウト</a>



	</body>
</html>
