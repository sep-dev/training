<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>会員情報の登録</title>
    <link href="<c:url value="/resources/css/addressbook.css" />" rel="stylesheet" >
  </head>
    <body>
      <h1>会員情報の登録</h1>
      <form:form modelAttribute="user" action="./insert" method="POST" class="inputForm">
        <form:hidden path="id"/>
        <table>
          <tr>
            <th>氏名</th>
            <td><form:input path="name"/></td>
          </tr>
          <tr>
            <th>住所</th>
            <td><form:input path="address" /></td>
          </tr>
           <tr>
            <th>電話番号</th>
            <td><form:input path="tel" /></td>
          </tr>
      </table>
      <p><input type="submit" value="登録"/></p>
    </form:form>
    <form:form action="./addressList" method="GET" class="linkForm">
      <input type="submit" value="会員一覧へ"/>
    </form:form>
  </body>
</html>
