<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>本日の授業一覧</title>
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>本日の授業一覧</h1>
    <p>${date}</p>
    <table border="1">
      <tr>
        <th>科目</th><th>時限</th><th>担当講師</th><th>出席確認</th>
      </tr>
      <c:forEach items="${lectureList}" var="list">
        <tr>
          <form:form modelAttribute="lecture_attendancePK">
            <form:hidden path="lectureId" value="${list.lectureId}"/>
            <form:hidden path="studentId"/>
            <td><c:out value="${list.lessonName}"/></td>
            <td><c:out value="${list.hour}"/></td>
            <td><c:out value="${list.teacherName}"/></td>
            <td>
              <c:choose>
                <c:when test="${list.attendanceState != 0}">登録済み</c:when>
                <c:when test="${list.attendanceState == 0}"><input type="submit" value="出席登録"/></c:when>
              </c:choose>
            </td>
          </form:form>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>