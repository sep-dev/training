<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>クラス新規登録画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
</head>
<body>
    <div class="managerDiv">
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
    <form:form modelAttribute="clas">
        <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
        <tr><td><form:label path="classId">クラスID：</form:label></td>
            <td><form:input path="classId" size="50"/></td></tr>
        <tr><td><form:label path="className">クラス名：</form:label></td>
            <td><form:input path="className" size="50"/></td></tr>
        <tr><td><input type="submit" value="登録"  onclick="location.href='classAdd'"/></td>
            <td><input type="reset" value="リセット"/></td></tr>
    </form:form>
    </table>
    <input type="submit" value="戻る"  onClick="history.go(-1)"/>
    </div>
</body>
</html>