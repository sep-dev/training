<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>

		<h2>ログインユーザー情報</h2>

		<h3>${h3}</h3>

		<table>
			<form:form modelAttribute ="SqlModel2">
				<tr><td><label>検索：</label></td>
					<td><input type="text" name="name" ></td>
					<td><input type="submit" name ="kensaku" value="検索" ></td></tr>
			</form:form>
		</table>


		<form:form modelAttribute="pageModel">
			<input type="submit" name ="new" value="新規登録" >
		</form:form>

		<div align=center>
			<table border=1>
			<tr>
				<td>ログインユーザー名</td><td>ログインユーザー</td><td>パスワード</td><td>編集</td>
			</tr>
			<c:if test="${list != null}">
				<c:forEach var="obj" items="${list}" varStatus="status">
					<tr>
						<td rowspan="2"><c:out value="${obj.loginUserName}" /></td>
						<td rowspan="2"><c:out value="${obj.loginUser}" /></td>
						<td rowspan="2"><c:out value="${obj.loginUserPass}" /></td>
							<form:form modelAttribute="pageModel">
								<input type="hidden" value="${obj.loginUserID}" name="userid">
								<td><input type="submit"  name ="update" value="編集" ></td>
							</form:form></tr>

							<tr><form:form modelAttribute="pageModel" >
								<input type="hidden" value="${obj.loginUserID}" name="userid">
								<td><input type="submit"  name ="delete" value="削除" ></td>
							</form:form></tr>
				</c:forEach>
				</c:if>
			</table>
		</div>

	</body>
</html>