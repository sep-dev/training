<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>講義編集画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >

  </head>
  <body>
    <div class="managerDiv">
      <h1>講義編集画面</h1>
      <p>${message}</p>
      <form:form modelAttribute="lecture" action="lectureUpdate">
        <table>
          <tr><th>講義ID：</th>
            <td><form:input path="lectureId"  readonly="true"/></td>
            <form:errors path="lectureId" cssClass="error" element="td"/>
          </tr>
          <tr><th>講義名：</th>
            <td><form:select path="lesson">
            <c:forEach items="${selectLesson}" var="opt">
              <c:choose>
                <c:when test="${opt.lessonId eq id}">
                  <option value="${opt.lessonId}" selected="selected"><c:out value="${opt.lessonName}"/></option>
                </c:when>
                <c:otherwise>
                  <option value="${opt.lessonId}"><c:out value="${opt.lessonName}"/></option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </form:select></td></tr>
          
          <tr><th>日付：</th>
            <td><form:input type="date" path="lectureDate" /></td>
          </tr>
          <tr><th>時限：</th>
            <td><form:select path="lectureHour"  items="${selectHour}" itemLabel="hourId" itemValue="hourId"/></td></tr>
          </tr>
        </table>
        <p><input type="submit" value="確定"/>
        <input type="reset" value="リセット"/></p>
      </form:form>
      <p><input type="submit" value="戻る"  onClick="history.go(-1)"/></p>
    </div>
  </body>
</html>