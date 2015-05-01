<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
  <head>
    <title>会員一覧</title>
  </head>
  <body>
    <h1>会員情報一覧画面</h1>
    <strong>必ずひとつだけ選択してください</strong>
    <form:form modelAttribute="listForm" action="./update/input" method="POST">
      <table>
        <tr>
          <th></th><th>氏名</th><th>住所</th><th>電話番号</th>
        </tr>
        <c:forEach var="user" items="${userList}">
        <tr>
          <td><form:radiobutton path="id" value="${user.id}"/></td>
          <td><c:out value="${user.name}" /></td>
          <td><c:out value="${user.address}" /></td>
          <td><c:out value="${user.tel}" /></td>
        </tr>
        </c:forEach>
      </table>
      <p><input type="submit" value="更新 or 削除" /></p>
      <p><input type="submit" value="新規登録" formaction="./"/></p>
    </form:form>
  </body>
</html>