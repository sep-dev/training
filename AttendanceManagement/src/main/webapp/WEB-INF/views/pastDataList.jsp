<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>過去の出席情報</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <h1>過去の出席情報検索ページ</h1>
    <form:form modelAttribute="searchAttendancePastDataForm" class="searchForm">
      <table>
        <tr>
          <th>日付：</th>
          <td><form:input path="startDate" type="date"/></td>
          <td>~</td>
          <td><form:input path="endDate" type="date"/></td>
        </tr>
        <tr>
          <th>科目名：</th>
          <td>
            <form:select path="lesson_id" items="${lessonList}"/>
          </td>
        </tr>
        <tr>
          <th>時限：</th>
          <td>
            <form:select path="hour" items="${hourList}" /> 時限目
          </td>
          <td>   </td>
          <td><input type="submit" value="検索"/></td>
        </tr>
      </table>
    </form:form>

    <table border="1">
      <tr>
        <th>日付</th><th>時限</th><th>科目</th>
      </tr>
      <c:forEach items="${pastDataList}" var="pastData">
        <tr>
          <td><c:out value="${pastData.date}"/></td>
          <td><c:out value="${pastData.hour}"/></td>
          <td><c:out value="${pastData.lessonName}"/></td>
        </tr>
      </c:forEach>
    </table>

    <p>
      <form:form modelAttribute="searchAttendancePastDataForm" action="./search?download">
        <form:hidden path="startDate"/>
        <form:hidden path="endDate"/>
        <form:hidden path="lesson_id"/>
        <form:hidden path="hour"/>
        <input type="submit" value="CSV出力" />
      </form:form>
    </p>
  </body>
</html>
