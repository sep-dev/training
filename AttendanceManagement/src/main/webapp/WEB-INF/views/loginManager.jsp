<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>管理者認証画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>管理者認証画面</h1>
    <a href="./newTeacher">新規の方はこちら</a>
    <c:if test="${error != null}">
       <div class="error"><p>${error}</p></div>
    </c:if>
      <form:form modelAttribute="loginForm" action="./login" method="post">
        <form:hidden path="type"/>
        <table class="centerTable">
          <tr>
            <td>管理者ID</td>
            <td><form:input path="id"/></td>
          </tr>
          <tr>
            <td>パスワード</td>
            <td><form:password path="password"/></td>
          </tr>
        </table>
        <p><input type="submit" value="ログイン"/></p>
      </form:form>
      <a class="linkButton_r" href="./" class="link_button">戻る</a>
  </body>
</html>