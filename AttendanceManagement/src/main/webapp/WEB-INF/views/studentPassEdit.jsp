<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>パスワード変更</title>
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>パスワード変更</h1>
    <p>すべて8文字以上の半角英数で入力してください</p>
    <form:form modelAttribute="passwordEditForm" action="./studentPasswordEdit?excute">
      <form:hidden path="studentId"/>
        <table>
          <tr>
            <td><form:label path="password">現在のパスワード:</form:label></td>
            <td>
              <form:password path="password" size="35" placeholder="現在のパスワードを入力してください" required="true"/>
            </td>
            <td><form:errors path="password" cssStyle="color:red"/></td>
          </tr>
          <tr>
            <td><form:label path="newPassword">新しいパスワード：</form:label></td>
            <td>
              <form:password path="newPassword" size="35" placeholder="新しいパスワードを入力してください" required="ture"/>
            </td>
            <td><form:errors path="newPassword" cssStyle="color:red"/></td>
          </tr>
          <tr>
            <td><form:label path="newPasswordConfirm">新しいパスワード（確認用）：</form:label></td>
            <td>
              <form:password path="newPasswordConfirm" size="35" placeholder="もう一度新しいパスワードを入力してください" required="ture"/>
            </td>
            <td><form:errors path="newPasswordConfirm" cssStyle="color:red"/></td>
          </tr>
        </table>
      <p><input type="submit" value="変更"/></p>
      <c:if test="${errorMsg!=null}"><div class="error">${errorMsg}</div></c:if>
    </form:form>
  </body>
</html>