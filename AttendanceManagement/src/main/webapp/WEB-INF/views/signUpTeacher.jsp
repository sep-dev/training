<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>新規登録</title>
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>新規登録</h1>
    <ul>
      <li>講師IDには責任者より配布されたIDを入力してください。</li>
      <li>パスワードは8文字以上で入力してください</li>
    </ul>
    <form:form modelAttribute="teacher" action="./teacherAdd">
      <table>
        <tr>
          <th>管理者ID</th>
          <td><form:input path="teacherId"/>
        </tr>
        <tr>
          <th>氏名</th>
          <td><form:input path="teacherName"/></td>
        </tr>
        <tr>
          <th>住所</th>
          <td><form:input path="teacherAddress"/></td>
        </tr>
        <tr>
          <th>電話番号</th>
          <td><form:input path="teacherTel"/></td>
        </tr>
        <tr>
          <th>パスワード</th>
          <td><form:password path="teacherPassword"/></td>
        </tr>
        <tr>
          <th>確認用パスワード</th>
          <td><input type="password" name="kakuninPassword"/></td>
        </tr>
      </table>
      <p>
        <input type="submit" value="登録"/>
        <a href="./manager" class="link_button">戻る</a>
      </p>
    </form:form>
    <c:if test="${error != null}">
      <div class="error">${error}</div>
    </c:if>
  </body>
</html>