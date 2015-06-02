<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>ログイン</title>
	</head>
	<body>

	<div align ="center">
		<h1>派遣人員管理システム</h1>

		<h2>${h2}</h2>

			<form:form modelAttribute="loginModel" >

				<table class="log_form" >
						<tr><td><label for="id">ユーザーID:</label></td>
							<td><input type="text" name="id" style="width : 300px"></td></tr>
						<tr><td><label for="pass">パスワード:</label></td>
							<td><input type="password" name="pass" style="width : 300px"></td></tr>
				</table>

					<!-- ボタン -->
					<p>
						<input type="submit" name="in" value="ログイン" >
					</p>
			</form:form>
	</div>


	</body>
</html>