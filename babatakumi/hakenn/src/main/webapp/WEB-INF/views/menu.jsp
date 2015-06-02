<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>メニュー</title>
	</head>
	<body>

		<div align=right>
			<iframe name="info" src="/spring/menu" width=80% height=100% style="float:right"></iframe>
		</div>

		<div align=center>

			<h2>メインメニュー</h2>

			<a href="/spring/menu" target="info" >派遣先情報</a><br>
			<a href="/spring/menu/hakennsyain_info" target="info" >派遣社員情報</a><br>
			<a href="/spring/menu/syozokumoto_info" target="info" >所属元情報</a><br>
			<a href="/spring/menu/syukkousaki_info" target="info" >出向先情報</a><br>
			<a href="/spring/menu/User" target="info" >ユーザー管理</a><br>

			<br>
			<a href="/spring">ログアウト</a>

		</div>

	</body>
</html>