<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- メインメニューのページ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>メインメニュー</title>
	</head>
	<body>
		<!--各jspを画面右に配置-->
		<iframe src="hakenInfo" name="contents"  height=800px width=85%  style="float:right" frameborder=0></iframe>
		<!-- 各ページへのリンク -->
	<div id="menu" align="center">
		<h3>メインメニュー</h3>
			<a href="hakenInfo" target="contents">派遣先情報</a><br>
			<a href="syainInfo" target="contents">派遣社員情報</a><br>
			<a href="syozokuInfo" target="contents">所属元情報</a><br>
			<a href="syukkoInfo" target="contents">出向先情報</a><br>
			<a href="userInfo" target="contents">ユーザ管理</a><br>
	<br>
			<a href="logout">ログアウト</a><br>
	</div>
	</body>
</html>