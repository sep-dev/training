<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>出席登録確認</title>
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>出席確認</h1>
    <table>
      <tr>
        <th>日付：</th>
        <td>${today}</td>
      </tr>
      <tr>
        <th>科目名：</th>
        <td>${lecture.lesson.lessonName}</td>
      </tr>
       <tr>
        <th>時限：</th>
        <td>${lecture.lectureHour}時限目</td>
      </tr>
    </table>
    <p>間違いありませんか</p>
    <form:form modelAttribute="id" action="./attendanceAdd?add">
      <form:hidden path="studentId"/>
      <form:hidden path="lectureId"/>
      <input type="submit" value="はい"/>
      <input type="submit" value="いいえ" formaction="./lectureList" />
    </form:form>
  </body>
</html>