<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>管理者メインページ</title>
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet" >
    <link href="<c:url value="/resources/css/managerTop.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>管理者メインページ</h1>
    <p>ユーザ名:${managerName}</p>
    <ul>
      <li><a href="">生徒管理</a></li>
      <li><a href="">講義管理</a></li>
      <li><a href="">講師管理</a></li>
      <li><a href="">クラス管理</a></li>
      <li><a href="">科目管理</a></li>
      <li><a href="">出席一覧</a></li>
    </ul>
  </body>
</html>