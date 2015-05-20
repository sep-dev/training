<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>生徒削除画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
</head>
<body>
    <div class="managerDiv">
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
    <form:form modelAttribute="student" action="studentDelete">
        <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
        <tr><td><form:label path="studentId">生徒ID：</form:label></td>
            <td><form:input path="studentId"  size="50" disabled="true"/></td></tr>
        <tr><td><form:label path="studentPassword">生徒パスワード：</form:label></td>
            <td><form:input path="studentPassword" size="50" value="********" disabled="true"/></td></tr>
        <tr><td><form:label path="studentName">名前：</form:label></td>
            <td><form:input path="studentName" size="50" disabled="true"/></td></tr>
        <tr><td><form:label path="clas">所属クラス：</form:label></td>
            <td><form:input path="clas.className"  size="50" disabled="true"/></td></tr>
        <tr><td><form:label path="studentAddress">住所：</form:label></td>
            <td><form:input path="studentAddress"  size="50" disabled="true"/></td></tr>
        <tr><td><form:label path="studentTel">電話番号</form:label></td>
            <td><form:input path="studentTel"  size="20" maxsize="11" disabled="true"/></td></tr>
        <tr><td><input type="submit" value="削除" /></td></tr>
        <input type="hidden" name="id" value="${id}">
    </form:form>
    </table>
    <input type="submit" value="戻る"  onClick="history.go(-1)"/>
    </div>
</body>
</html>