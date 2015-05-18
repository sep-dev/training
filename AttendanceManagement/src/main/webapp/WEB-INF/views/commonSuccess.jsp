<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>${titile}</title>
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>${message}</h1>
    <a href="./lectureList" class="link_button">本日の授業一覧へ</a>
  </body>
</html>