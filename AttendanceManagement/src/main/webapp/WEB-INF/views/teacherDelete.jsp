<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>講師削除画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
</head>
<body>
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
    <form:form modelAttribute="teacher" action="teacherDelete">
        <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
        <tr><td><form:label path="teacherId">講師ID：</form:label></td>
            <td><form:input path="teacherId" size="50" disabled="true"/></td></tr>
        <tr><td><form:label path="teacherPassword">講師パスワード：</form:label></td>
            <td><form:input path="teacherPassword" size="50" value="********" disabled="true"/></td></tr>
        <tr><td><form:label path="teacherName">名前：</form:label></td>
            <td><form:input path="teacherName"  size="50" disabled="true"/></td></tr>
        <tr><td><form:label path="class.className">所属クラス：</form:label></td>
            <td><form:input path="class.className"  size="50" disabled="true"/></td></tr>
        <tr><td><form:label path="teacherAddress">住所：</form:label></td>
            <td><form:input path="teacherAddress"  size="50" disabled="true"/></td></tr>
        <tr><td><form:label path="teacherTel">電話番号</form:label></td>
            <td><form:input path="teacherTel"   size="20" maxsize="11" disabled="true"/></td></tr>
        <tr><td><input type="submit" value="削除" /></td></tr>
        <input type="hidden" name="id" value="${id}">
    </form:form>
    </table>
    <input type="submit" value="戻る"  onclick="location.href='teacherList'"/>
</body>
</html>