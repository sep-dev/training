<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view</title>
</head>
<body>

	<p>派遣人員管理システム</p>
	<c:if test="${loginfailureflag == true}">
		<p>ログイン失敗！もう一度入力しなおしてください！！</p>
	</c:if>
	<form:form modelAttribute="formModel" action="login">
		<form:label path="userId">ユーザーID:</form:label>
			<form:input path="userId" size="20" maxlength="10" />
			<br />
		<form:label path="pass">パスワード:</form:label>
			<form:input path="pass" size="20" maxlength="20" />
			<br />
		<input type="submit" value="ログイン">
	</form:form>

</body>
</html>