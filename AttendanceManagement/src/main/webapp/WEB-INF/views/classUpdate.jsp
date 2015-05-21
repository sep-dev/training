<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>クラス編集画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <div class="managerDiv">
      <h1>クラス編集画面</h1>
      <p>${message}</p>
      <form:form modelAttribute="clas" action="classUpdate">
        <table>
          <tr><th>クラスID：</th>
            <td><form:input path="classId" required="true" placeholder="IDを入力してください" pattern="^[0-9]*$"/></td>
            <form:errors path="classId" cssClass="error" element="td"/>
          </tr>
          <tr><th>クラス名：</th>
            <td><form:input path="className" required="true" placeholder="名前を入力してください"/></td>
            <form:errors path="className" cssClass="error" element="td"/>
          </tr>
        </table>
        <p><input type="submit" value="確定"/>
        <input type="reset" value="リセット"/></p>
      </form:form>
      <p><input type="submit" value="戻る"  onClick="history.go(-1)"/><p>
    </div>
  </body>
</html>