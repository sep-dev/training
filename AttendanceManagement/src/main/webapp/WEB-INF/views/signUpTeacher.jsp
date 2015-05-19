<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>新規登録</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>新規登録</h1>
    <div class="singUpDiv">
      <ul>
        <li>講師IDには責任者より配布されたIDを入力してください。</li>
        <li>パスワードは8文字以上で入力してください</li>
      </ul>
      <form:form modelAttribute="teacherAddForm" action="./teacherAdd">
        <table>
          <tr>
            <th>管理者ID</th>
            <td><form:input path="teacherId" required="true" placeholder="管理者IDを入力してください" pattern="^[0-9]*$"/></td>
            <form:errors path="teacherId" cssClass="error" element="td"/>
          </tr>
          <tr>
            <th>氏名</th>
            <td><form:input path="teacherName" required="true" placeholder="氏名を入力してください"/></td>
            <form:errors path="teacherName" cssClass="error" element="td"/>
          </tr>
          <tr>
            <th>住所</th>
            <td><form:input path="teacherAddress" required="true" placeholder="住所を入力してください"/></td>
            <form:errors path="teacherAddress" cssClass="error" element="td"/>
          </tr>
          <tr>
            <th>電話番号</th>
            <td><form:input path="teacherTel" required="true" placeholder="電話番号を入力してください" pattern="^[0-9]{10,11}$"/></td>
            <form:errors path="teacherTel" cssClass="error" element="td"/>
          </tr>
          <tr>
            <th>パスワード</th>
            <td><form:password path="password" required="true" placeholder="パスワードを入力してください" pattern="^*{8,}$"/></td>
            <form:errors path="password" cssClass="error" element="td"/>
          </tr>
          <tr>
            <th>確認用パスワード</th>
            <td><form:password path="passwordConfirm" required="true" placeholder="パスワードを再入力してください"/></td>
            <form:errors path="passwordConfirm" cssClass="error" element="td"/>
          </tr>
        </table>
        <p>
          <input type="submit" value="登録" class="submitButton"/>
          <a href="./loginManager" class="linkButton_r">戻る</a>
        </p>
      </form:form>
      <c:if test="${error != null}">
        <div class="error">${error}</div>
      </c:if>
    </div>
  </body>
</html>