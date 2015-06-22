<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<title>メニュー</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<style type="text/css">
			body{
			margin : 5px;
			padding : 0px
			}
			.back{
				background-color : yellow;
			}
		</style>
		<script >
		$(function(){
			$("a").hover(
					  function () {
						  $(this).addClass("back");
					  },
					  function () {
					    $(this).removeClass("back");
					  }
					);
		});
		</script>

	</head>

	<body>

	<iframe name="info" src="/spring/menu" style="width:80%; height:700px; float:right"></iframe>

	<div align="center">
		<h2>メインメニュー</h2>


			<p><a href="/spring/menu" 					target="info" >派遣先情報</a></p>
			<p><a href="/spring/menu/hakennsyain_info"  target="info" >派遣社員情報</a></p>
			<p><a href="/spring/menu/syozokumoto_info"  target="info" >所属元情報</a></p>
			<p><a href="/spring/menu/syukkousaki_info"  target="info" >出向先情報</a></p>
			<p><a href="/spring/menu/User"				target="info" >ユーザー管理</a></p>
			<br>
			<p><a href="/spring">ログアウト</a></p>
	</div>

	</body>
</html>