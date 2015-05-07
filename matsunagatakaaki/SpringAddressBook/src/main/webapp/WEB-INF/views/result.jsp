<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>${title}</title>
    <link href="<c:url value="/resources/css/addressbook.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>${title}</h1>
    <p>${message}</p>
    <form action="${pageContext.request.contextPath}" class="linkForm">
      <input type="submit" value="TOPへ" />
    </form>
  </body>
</html>