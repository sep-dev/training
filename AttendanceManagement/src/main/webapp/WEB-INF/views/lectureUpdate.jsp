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
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/i18n/jquery.ui.datepicker-ja.js"></script>
    <link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" rel="stylesheet" />

</head>
<body>

    <script type="text/javascript" >
    <!--
      $(function() {
    	  $("#datepicker").datepicker({
    		  showButtonPanel: true
    	  });
      });
    -->
    </script>

    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
    <form:form modelAttribute="lecture">
        <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
        <tr><td><form:label path="lectureId" >講義ID：</form:label></td>
            <td><form:input path="lectureId"  size="50" readonly="true"/></td></tr>
        <tr><td><form:label path="lesson">講義名：</form:label></td>
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
        <tr><td><form:label path="lesson.teacher">担当講師名：</form:label></td>
            <td><form:select path="lesson.teacher"  items="${selectTeacher}" itemLabel="teacherName" itemValue="teacherId"/></td></tr>
        <tr><td><form:label path="lectureDate">日付：</form:label></td>
            <td><form:input type="date" path="lectureDate"/></td></tr>
        <tr><td><form:label path="lectureHour">時限：</form:label></td>
            <td><form:input path="lectureHour"  /></td></tr>
        <tr><td><input type="submit" value="確定"  onclick="location.href='lectureUpdate'"/></td>
            <td><input type="reset" value="リセット"/></td></tr>
    </form:form>
    </table>
    <input type="submit" value="戻る"  onclick="location.href='lectureList'"/>
</body>
</html>