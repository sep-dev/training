<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>生徒画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <nav class="menu_nav">
      <p><a href="${pageContext.request.contextPath}/logout" class="linkButton_l">ログアウト</a></p>
      <ul>
        <li><a href="./studentPasswordEdit?input" class="linkButton_r" target="section">パスワード変更</a></li>
        <li><a href="./search" class="linkButton_r" target="section">過去の出席情報へ</a></li>
        <li><a href="./lectureList" class="linkButton_r" target="section">本日の授業一覧へ</a></li>
      </ul>
    </nav>
    <section>
      <iframe src="./lectureList" name="section">iframeを使用</iframe>
    </section>
  </body>
</html>