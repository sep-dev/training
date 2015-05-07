<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>更新情報入力</title>
    <link href="<c:url value="/resources/css/addressbook.css" />" rel="stylesheet" >
  </head>
  <body>
    <form:form modelAttribute="updateUser" method="POST" action="./result" class="inputForm">
      <form:hidden path="id"/>
      <table>
        <tr>
          <th>氏名：</th>
          <td><form:input path="name"/></td>
        </tr>
        <tr>
          <th>住所：</th>
          <td><form:input path="address"/></td>
        </tr>
        <tr>
          <th>電話番号：</th>
          <td><form:input path="tel"/></td>
        </tr>
      </table>
      <p><input type="submit" value="更新" /></p>
      <p><input type="submit" value="削除" formaction="./../delete/kakunin" /><p>
    </form:form>
  </body>
</html>