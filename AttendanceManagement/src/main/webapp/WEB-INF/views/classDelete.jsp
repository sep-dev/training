<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>クラス削除画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <div class="managerDiv">
      <h1>クラス削除画面</h1>
      <p>本当に削除してよろしいですか？</p>
      <table>
        <form:form modelAttribute="class" action="classDelete">
          <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
          <tr><td><form:label path="classId">科目ID：</form:label></td>
            <td><form:input path="classId"  size="50" disabled="true"/></td></tr>
          <tr><td><form:label path="className">科目名：</form:label></td>
            <td><form:input path="className" size="50" disabled="true"/></td></tr>
          <tr><td><input type="submit" value="削除" /></td></tr>
        <input type="hidden" name="id" value="${id}">
      </form:form>
    </table>
   <input type="submit" value="戻る"  onClick="history.go(-1)"/>
    </div>
  </body>
</html>