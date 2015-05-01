<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>削除確認</title>
  </head>
  <body>
    <h1>削除しますか？</h1>
    <form:form modelAttribute="deleteUser" method="POST" action="./excute">
      <form:hidden path="id" value="${deleteUser.id}"/>
      <form:hidden path="name" value="${deleteUser.name}}"/>
      <form:hidden path="address" value="${deleteUser.address}}"/>
      <form:hidden path="tel" value="${deleteUser.tel}}"/>
      <table>
        <tr>
          <td>${deleteUser.name}</td><td>${deleteUser.address}</td><td>${deleteUser.tel}</td>
        </tr>
      </table>
      <input type="submit" value="削除" />
      </form:form>
      <form:form action="${pageContext.request.contextPath}/addressList">
        <input type="submit" value="住所一覧へ" />
      </form:form>
  </body>
</html>