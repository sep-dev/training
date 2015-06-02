<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<title>Login</title>
</head>
<body>
    <div align="center">
    <h1>派遣人員管理システム</h1>
    <c:if test="${not empty param.error}">
        <font color="red">
            Error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </font>
    </c:if>
    <form class="well" method="POST" action="<c:url value="/j_spring_security_check"/>">
                ユーザー名　：　
                <input type="text" name="j_username"/><br/><br/>
                パスワード　：　
                <input type="password" name="j_password"/><br/><br/>
                <input type="submit" value="ログイン" class="btn btn-primary"/>
    </form>
    </div>
</body>
</html>