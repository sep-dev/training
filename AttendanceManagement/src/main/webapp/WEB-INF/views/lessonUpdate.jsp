<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>科目編集画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
</head>
<body>
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
    <form:form modelAttribute="lesson">
        <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
        <tr><td><form:label path="lessonId">科目ID：</form:label></td>
            <td><form:input path="lessonId"  size="50"/></td></tr>
        <tr><td><form:label path="lessonName">科目名：</form:label></td>
            <td><form:input path="lessonName" size="50"/></td></tr>
        <tr><td><form:label path="teacher">担当講師名：</form:label></td>
             <td><form:select path="teacher"  items="${selectTeacher}" itemLabel="teacherName" itemValue="teacherId"/></td></tr>
        <tr><td><input type="submit" value="確定"  onclick="location.href='lessonUpdate'"/></td>
            <td><input type="reset" value="リセット"/></td></tr>
    </form:form>
    </table>
    <input type="submit" value="戻る"  onclick="location.href='lessonList'"/>
</body>
</html>