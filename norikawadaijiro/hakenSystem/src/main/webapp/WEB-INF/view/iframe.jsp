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
   <iframe name="info" src="/hakenSystem/HakensakiItiran" width=85% height=100% style="float:right"></iframe>
  </div>

  <h2>メインメニュー</h2>


  <a href="/hakenSystem/HakensakiItiran" target="info" >派遣先情報</a><br>
  <a href="/hakenSystem/HakensyainItiran" target="info" >派遣社員情報</a><br>
  <a href="/hakenSystem/SyozokumotoItiran" target="info" >所属元情報</a><br>
  <a href="/hakenSystem/SyukkousakiItiran" target="info" >出向先情報</a><br>
  <a href="/hakenSystem/UserItiran" target="info" >ユーザー管理</a><br>

  <br>
  <a href="/hakenSystem/">ログアウト</a>

 </body>
</html>