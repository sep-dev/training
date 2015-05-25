<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>講義削除画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <div class="managerDiv">
      <h1>講義削除画面</h1>
      <p>本当に削除してよろしいですか？</p>
      <table>
        <form:form modelAttribute="lecture" action="lectureDelete">
          <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
          <tr><td><form:label path="lectureId">講義ID：</form:label></td>
            <td><form:input path="lectureId"  size="50" disabled="true"/></td></tr>
          <tr><td><form:label path="lesson.lessonId">講義名：</form:label></td>
            <td><form:input path="lesson.lessonName" size="50" disabled="true"/></td></tr>
          <tr><td><form:label path="lesson.teacher.teacherName">担当講師氏名：</form:label></td>
            <td><form:input path="lesson.teacher.teacherName" size="50" disabled="true"/></td></tr>
          <tr><td><form:label path="lectureDate">講義日付：</form:label></td>
            <td><form:input path="lectureDate" size="50" disabled="true"/></td></tr>
          <tr><td><form:label path="lectureHour">講義時間：</form:label></td>
            <td><form:input path="lectureHour" size="50" disabled="true"/></td></tr>
          <tr><td><input type="submit" value="削除" /></td></tr>
          <input type="hidden" name="id" value="${id}">
        </form:form>
      </table>
      <input type="submit" value="戻る"  onClick="history.go(-1)"/>
    </div>
  </body>
</html>