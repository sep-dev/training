<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>生徒新規登録画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
</head>
<body>
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
    <form:form modelAttribute="student">
        <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
        <tr><td><form:label path="studentId">生徒ID：</form:label></td>
            <td><form:input path="studentId" size="50"/></td></tr>
        <tr><td><form:label path="studentPassword">生徒パスワード：</form:label></td>
            <td><form:input path="studentPassword" size="50"/></td></tr>
        <tr><td><form:label path="studentName">名前：</form:label></td>
            <td><form:input path="studentName" size="50"/></td></tr>
        <tr><td><form:label path="classId">所属クラス：</form:label></td>
            <td><form:input path="classId" size="50"/></td></tr>
        <tr><td><form:label path="studentAddress">住所：</form:label></td>
            <td><form:input path="studentAddress" size="50"/></td></tr>
        <tr><td><form:label path="studentTel">電話番号</form:label></td>
            <td><form:input path="studentTel" size="20" maxsize="11"/></td></tr>
        <tr><td><input type="submit" value="登録"  onclick="location.href='studentAdd'"/></td>
            <td><input type="reset" value="リセット"/></td></tr>
    </form:form>
    </table>
    <input type="submit" value="戻る"  onclick="location.href='studentList'"/>
</body>
</html>