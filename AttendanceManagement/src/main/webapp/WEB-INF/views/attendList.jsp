<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>出席生徒一覧画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <div class="managerDiv">
      <h1>出席生徒一覧画面</h1>
      <form action="${pageContext.request.contextPath}/manager/attendList" method="post">
      <table>
        <tr><td>生徒名検索 :</td>
          <td><input type="text" name="student_name" value="${find1}" /></td></tr>
        <tr><td>講義名検索 :</td>
          <td><input type="text" name="lecture_name" value="${find2}"/></td></tr>
        <tr><td>日付検索 :</td>
          <td><input type="date" name="lecture_date" value="${find3}"/>～<input type="date" name="lecture_date2" value="${find4}"/></td></tr>
        <tr><td>時限検索 :</td>
          <td><input type="text" name="lecture_hour" value="${find5}"/></td></tr>
        <tr><td></td><td><input type="submit" value="検索"></td></tr>
      </table>
      </form>
      <form action="${pageContext.request.contextPath}/manager/attendList" method="get">
        <input type="submit" value="一覧表示">
      </form>
      <hr>
      <form>
        <c:if test="${datalist !=null}">
          <table border="1" class="managerListTable">
            <thead>
              <tr><th class="col_s">ID</th><th class="col_m">名前</th><th class="col_m">講義名</th><th class="col_m">日付</th>
                <th class="col_ss">時限</th><th class="col_bottun"></th><th class="col_ss"></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="obj" items="${datalist}" varStatus="status">
                <tr>
                  <td class="col_s"><a href="<c:url value="studentUpdate?id=${obj.studentId}"/>" ><c:out value="${obj.studentId}"  /></a></td>
                  <td class="col_m"><a href="<c:url value="studentUpdate?id=${obj.studentId}"/>" ><c:out value="${obj.studentName}"/></td>
                  <td class="col_m"><a href="<c:url value="lessonUpdate?id=${obj.lectureId}"/>" ><c:out value="${obj.lessonName}" /></td>
                  <td class="col_m"><c:out value="${obj.lectureDate}" /></td>
                  <td class="col_ss"><c:out value="${obj.lectureHour}" /></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </form>
      <table border="1" class="attendListTable"><tr><td>合計</td><td>${count}</td></tr></table>
      <input type="submit" value="戻る"  onClick="location.href='managerMain'"/>
    </div>
  </body>
</html>

