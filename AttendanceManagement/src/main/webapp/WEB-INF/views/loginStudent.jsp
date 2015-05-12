<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>生徒ログイン画面</title>
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>生徒ログイン画面</h1>
    <form:form modelAttribute="loginForm" action="./login">
      <form:hidden path="type"/>
      <table>
        <tr>
          <td>生徒ID</td>
          <td><form:input path="id" placeholder="生徒IDを入力してください"/></td>
        </tr>
        <tr>
          <td>パスワード</td>
          <td><form:password path="password" placeholder="パスワードを入力してください"/></td>
        </tr>
      </table>
      <p><input type="submit" value="ログイン" /></p>
    </form:form>
    <c:if test="${error != null}">
      <div class="error">
        <c:out value="${error}" />
      </div>
    </c:if>
  </body>
</html>