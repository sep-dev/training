<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>
	<h1>管理者登録</h1>
	<c:if test="${loginfailureflag == true}">
		<p>登録失敗！違うパスワードが入力されました！！</p>
	</c:if>
	<form:form modelAttribute="formModel" action="UserInsert">
		<table frame="border" border="1" width="640">
			<tr>
				<td width="240">ユーザー名称</td>
				<td width="400">
					<form:input path="username"/>
				</td>
			</tr>
			<tr>
				<td width="240">ユーザーID</td>
				<td width="400">
					<form:input path="user"/>
				</td>
			</tr>
			<tr>
				<td width="240">パスワード</td>
				<td width="400">
					<form:input path="password"/>※半角英数字記号を使ってください
				</td>
			</tr>
			<tr>
				<td width="240">パスワード確認</td>
				<td width="400">
					<form:input path="passwordcheck"/>
				</td>

		</table>
		<td>
			<input type="submit" value="確定">
			</td>
		</form:form>
		<form:form modelAttribute="dataset" action="UserItiran" method="get">
			<INPUT type="submit" value="戻る">
		</form:form>
</body>
</html>