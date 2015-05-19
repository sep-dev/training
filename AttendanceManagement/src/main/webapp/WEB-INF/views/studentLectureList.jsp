<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>本日の授業一覧</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>本日の授業一覧</h1>
    <h3>${date}</h3>
    <table border="1" class="listTable">
      <thead>
        <tr>
          <th>科目</th><th>時限</th><th>担当講師</th><th>出席確認</th>
        </tr>
      </thead>
      <c:forEach items="${lectureList}" var="list">
        <tr>
          <td><c:out value="${list.lessonName}"/></td>
          <td><c:out value="${list.hour}"/></td>
          <td><c:out value="${list.teacherName}"/></td>
          <c:choose>
            <c:when test="${list.attendanceState == 0}"><td>出席済み</td></c:when>
            <c:when test="${list.attendanceState == 1}">
              <form:form modelAttribute="lecture_attendancePK" action="./attendanceAdd?confirm">
                <form:hidden path="lectureId" value="${list.lectureId}"/>
                <form:hidden path="studentId"/>
                <td><input type="submit" value="出席登録"/></td>
              </form:form>
            </c:when>
            <c:when test="${list.attendanceState == 2}"><td>未登録</td></c:when>
            <c:when test="${list.attendanceState == 3}"><td>未出席</td></c:when>
          </c:choose>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>