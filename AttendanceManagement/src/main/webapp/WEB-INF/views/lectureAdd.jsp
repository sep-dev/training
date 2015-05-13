<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>講義新規登録画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
</head>
<body>
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
    <form:form modelAttribute="lecture">
        <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
        <tr><td><form:label path="lectureId">講義ID：</form:label></td>
            <td><form:input path="lectureId" size="50"/></td></tr>
        <tr><td><form:label path="lesson.lessonId">科目ID：</form:label></td>
            <td><form:input path="lesson.lessonId" size="50"/></td></tr>
        <tr><td><form:label path="lectureDate">講義年月日：</form:label></td>
            <td><form:input type=""  path="lectureDate" /></td></tr>
        <tr><td><form:label path="lectureHour">講義時限：</form:label></td>
            <td><form:input path="lectureHour" size="50"/></td></tr>


        <tr><td><input type="submit" value="登録"  onclick="location.href='lectureAdd'"/></td>
            <td><input type="reset" value="リセット"/></td></tr>
    </form:form>
    </table>
    <input type="submit" value="戻る"  onclick="location.href='lectureList'"/>
</body>
</html>