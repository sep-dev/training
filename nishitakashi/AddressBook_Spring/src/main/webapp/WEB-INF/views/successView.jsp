<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登録成功</title>
<link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
</head>
<body>
    <div id="wrap">
    <h1 id="title">${title}</h1>
    <p>${message}</p>
    <div id="edittext">
    <form:form modelAttribute="insertForm" action="${pageContext.request.contextPath}/successView">
    <input type="submit" value="一覧表示"/>
    </form:form>
    </div>
    </div>
</body>
</html>