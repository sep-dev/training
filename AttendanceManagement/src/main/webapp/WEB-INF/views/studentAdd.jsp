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
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
</head>
<body>
    <div class="managerDiv">
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
    <form:form modelAttribute="student" action="studentAdd">
        <tr><th>生徒ID：</th>
            <td><form:input path="studentId"  required="true" placeholder="生徒IDを入力してください" pattern="^[0-9]*$"/></td>
            <form:errors path="studentId" cssClass="error" element="td"/>
        </tr>
        <tr><th>生徒パスワード：</th>
            <td><form:password path="studentPassword" required="true" placeholder="パスワードを入力してください" /></td>
            <form:errors path="studentPassword" cssClass="error" element="td"/>
        </tr>
        <tr><th>生徒パスワード(確認用)：</th>
            <td><input type="text" name="passwordConfirm"  required placeholder="確認のためパスワードを入力してください"/>
        </td>
        </tr>
        <tr><th>名前：</th>
            <td><form:input path="studentName" required="true" placeholder="名前を入力してください" /></td>
            <form:errors path="studentName" cssClass="error" element="td"/>
        </tr>
        <tr><th>所属クラス：</th>
            <td><form:select path="clas">
            <c:forEach items="${selectClass}" var="opt">
            <c:choose>
            <c:when test="${opt.classId eq id}">
            <option value="${opt.classId}" selected="selected"><c:out value="${opt.className}"/></option>
            </c:when>
            <c:otherwise>
            <option value="${opt.classId}"><c:out value="${opt.className}"/></option>
            </c:otherwise>
            </c:choose>
            </c:forEach>
            </form:select></td></tr>
        <tr><th>住所：</th>
            <td><form:input path="studentAddress" required="true" placeholder="住所を入力してください" /></td>
            <form:errors path="studentAddress" cssClass="error" element="td"/>
        </tr>
        <tr><th>電話番号</th>
            <td><form:input path="studentTel" required="true" placeholder="電話番号を入力してください" pattern="^[0-9]{10,11}$"/></td>
            <form:errors path="studentTel" cssClass="error" element="td"/>
        </tr>
        <tr><td><input type="submit" value="登録"/></td>
            <td><input type="reset" value="リセット"/></td>
        </tr>
    </form:form>
    </table>
    <input type="submit" value="戻る"  onClick="history.go(-1)"/>
    </div>
</body>
</html>