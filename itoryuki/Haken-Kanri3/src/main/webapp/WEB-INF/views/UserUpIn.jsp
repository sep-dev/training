<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ユーザー情報の登録/編集 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<!-- ユーザー登録 -->
	<c:if test="${List == 'null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8)">
			<title>ユーザー登録</title>
		</head>
		<body>
		<div align="center">
			<h2>ユーザー登録</h2>
		</div>
		<div align="center">
		<p><font size="5" color="red">${message}</font></p>
			<form:form modelAttribute="userModel"  action="userInSyori">
			<table border=1>
				<tr><th colspan=2>ユーザー名称</th>
						<td><form:input path="loginusername" />(例：あああ太郎)</td></tr>
				<tr><th colspan=2>ユーザーID</th>
						<td><form:input path="loginuser" />(例：aaataro)</td><tr>
				<tr><th colspan=2>パスワード</th>
						<td><form:input type="password" path="loginuserpass1" pattern="^[0-9A-Za-z]+$"/><font color="red">※半角英数字</font></td><tr>
				<tr><th colspan=2>パスワード確認</th>
						<td><form:input type="password" path="loginuserpass2" pattern="^[0-9A-Za-z]+$"/>(もう1度入力してください)</td></tr>
			</table>
		<br>
				<input type="submit" value="登録" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
		</div>
		</body>
	</html>
	</c:if>

	<!-- ユーザー情報編集 -->
	<c:if test="${List != 'null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8)">
			<title>ユーザー情報編集</title>
		</head>
		<body>
		<div align="center">
			<h2>ユーザー情報編集</h2>
		</div>
		<br>
		<div align="center">
		<p><font size="5" color="red">${message}</font></p>
			<form:form modelAttribute="userModel" action="userUpSyori" >
			<input type="hidden" value="${loginUserId}" name="loginuserid">
			<table border=1>
				<tr><th colspan=2>ユーザー名称</th>
					<td><form:input path="loginusername" value="${loginUserName}"/>(例：あああ太郎)</td></tr>
				<tr><th colspan=2>ユーザーID</th>
					<td><form:input path="loginuser" value="${loginUser}" pattern="^[0-9A-Za-z]+$"/>(例：aaataro)</td><tr>
				<tr><th colspan=2>パスワード</th>
					<td><form:input type="password" path="loginuserpass1" value="${loginUserPass}" pattern="^[0-9A-Za-z]+$"/><font color="red">※半角英数字</font></td><tr>
				<tr><th colspan=2>パスワード確認</th>
					<td><form:input type="password" path="loginuserpass2"/>(もう1度入力してください)</td></tr>
			</table>
		<br>
				<input type="submit" value="確定" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
		</div>
		</body>
	</html>
	</c:if>
