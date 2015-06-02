<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate;"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ログインユーザー情報</h1>
<form:form modelAttribute="formModel" action="Userkensaku">
<table>
	<tr>
		<td><form:label path="kensakuword">検索:</form:label></td>
		<td><form:input path="kensakuword" size="20" maxlength="10" /></td>
		<td><input type="submit" value="検索"></td>
	</tr>
</table>
</form:form>
<form:form modelAttribute="dataSet" action="Usertouroku">
	<input type="submit" value="新規登録">
</form:form>
<br />
	<table frame="border" border="1">
		<tr>
			<td>ログインユーザー名称</td>
			<td>ログインユーザー</td>
			<td>パスワード</td>
			<td>編集</td>
		</tr>
		<tr>
			<c:forEach var="obj" items="${list}">
				<tr height="50">
				<form:form action="Userhensyu" modelAttribute="formModel">
					<td><c:out value="${obj.loginUserName}" /></td>
					<td><c:out value="${obj.loginUser}" /></td>
					<td><c:out value="${obj.loginUserPass}" /></td>
					<td><input type="hidden" value="${obj.loginUserId}" name="updateloginUserId">
						<input type="submit" value="編集"></td>
				</form:form>
				<form:form action="Delete" modelAttribute="formModel">
					<td><input type="hidden" value="${obj.loginUserId}" name="deleteId">
						<input type="hidden" value="tblLoginUser" name="tablename">
						<input type="hidden" value="loginUserId" name="primary">
						<input type="hidden" value="redirect:UserItiran" name="Itiran">
					<input type="submit" value="削除"></td>
				</form:form>
				</tr>
			</c:forEach>
	</table>
</body>
</html>