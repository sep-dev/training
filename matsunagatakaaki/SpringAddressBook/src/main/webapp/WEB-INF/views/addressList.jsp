<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>会員一覧</title>
    <link href="<c:url value="/resources/css/addressbook.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>会員情報一覧画面</h1>
    <p><strong>必ずひとつだけ選択してください</strong></p>
    <form:form modelAttribute="listForm" action="./update/input" method="POST" class="inputForm">
      <table>
        <tr>
          <th></th><th >氏名</th><th >住所</th><th >電話番号</th>
        </tr>
        <c:forEach var="user" items="${userList}">
        <tr>
          <td><form:radiobutton path="id" value="${user.id}"/></td>
          <td class="name_td"><c:out value="${user.name}" /></td>
          <td class="address_td"><c:out value="${user.address}" /></td>
          <td class="tel_td"><c:out value="${user.tel}" /></td>
        </tr>
        </c:forEach>
      </table>
      <p><input type="submit" value="更新 or 削除" /></p>
      <p><input type="submit" value="新規登録" formaction="./"/></p>
    </form:form>
  </body>
</html>