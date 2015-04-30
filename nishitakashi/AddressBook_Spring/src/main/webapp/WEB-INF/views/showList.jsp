<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>会員一覧</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
</head>
<body>
    <div id="wrap">
    <h1 id="title">${title}</h1>
    <p>${message}</p>
    <div id="table">
    <form:form modelAttribute="selectForm" action="${pageContext.request.contextPath}/showList">

    <c:if test="${data !=null }">
    <table border="1">
    <tr><th></th><th>名前</th><th>住所</th><th>電話番号</th></tr>
    <c:forEach var="obj" items="${data}" varStatus="status">
        <tr>
        <td><form:radiobutton path="radio1" name="radio1" value="${obj.id}" /></td>
        <td><c:out value="${obj.name}"/></td>
        <td><c:out value="${obj.address}"/></td>
        <td><c:out value="${obj.tel}"/></td>
        </tr>
    </c:forEach>
    </table>
    </c:if>
    <div id="button">
    <input type="submit" value="更新・削除"/>
    </div>
    </form:form>

    <form action="${pageContext.request.contextPath}/">
    <input type="submit" value="新規作成"/>
    </form>
    </div>
    </div>
</body>
</html>