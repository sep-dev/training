<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
		<style>
		h1	{font-size:16pt; background-color:#CCCCFF; padding:3px;}
		</style>
	</head>
	<body>

		<h1>${midashi}</h1>
		<%--changeの値で表示する内容を決定 --%>
		<c:choose>

			<%--会員リスト表示ページ--%>
			<c:when test="${change ==1}">
				<form:form modelAttribute="formModelRadio" action="Addressupdate">
					<table>
						<c:forEach var="obj" items="${list1}" varStatus="status">
							<tr>
							<td><form:radiobutton path="radio" value="${obj.id}" checked="true"/></td>
							<td>名前:<c:out value="${obj.name}" />　</td>
							<td>住所:<c:out value="${obj.address}" />　</td>
							<td>番号:<c:out value="${obj.tel}" /></td>
							</tr>
						</c:forEach>
					</table>
					<input type="submit" value="更新or削除">
					<input type="button" value="会員登録" onClick="location.href='Addressbook'">
				</form:form>
			</c:when>

			<%--会員登録ページ--%>
			<c:when test="${change ==2}">
				<table>
					<form:form modelAttribute="formModel">
						<tr><td></td><td><form:errors path="*" element="div" /></td></tr>
						<tr><td><form:label path="name">名前:</form:label></td>
							<td><form:input path="name" size="20" maxlength="10" /></td></tr>
						<tr><td><form:label path="address">住所:</form:label></td>
							<td><form:input path="address" size="20" maxlength="20" /></td></tr>
						<tr><td><form:label path="tel">電話番号:</form:label></td>
							<td><form:input path="tel" size="20" maxlength="11"/></td></tr>
						<tr><td></td><td><input type="submit" value="登録"></td></tr>
						<tr><td></td><td><input type="reset"></td></tr>
						<tr><td></td><td><input type="button" value="会員一覧" onClick="location.href='Addresslist'">
					</form:form>
				</table>
			</c:when>

			<%--会員一覧への移動ページ--%>
			<c:when test="${change ==3}">
				<table>
					<form:form action="Addresslist" method="GET">
						<input type="submit" value="会員一覧ページへ">
					</form:form>
				</table>
			</c:when>

			<%--会員情報の更新or削除ページ--%>
			<c:when test="${change ==4}">
				名前:<c:out value="${name}" />
				住所:<c:out value="${address}" />
				番号:<c:out value="${tel}" />
				←選択したデータ<br /><br />
				<form:form modelAttribute="formModelUpdate" action="Addressupdated">
						<tr><td></td><td><form:errors path="*" element="div" /></td></tr>
						<tr><td><form:label path="name">名前:</form:label></td>
							<td><form:input path="name" size="20" maxlength="10" value="${name}" /></td></tr><br />
						<tr><td><form:label path="address">住所:</form:label></td>
							<td><form:input path="address" size="20" maxlength="20" value="${address}" /></td></tr><br />
						<tr><td><form:label path="tel">電話番号:</form:label></td>
							<td><form:input path="tel" size="20" maxlength="11" value="${tel}"/></td></tr><br />
						<form:hidden path="id" value="${id}"/>
						<tr><td></td><td><input type="submit" name="update" value="更新"></td></tr>
						<tr><td></td><td><input type="reset"></td></tr>
						<tr><td></td><td><input type="submit" name="delete" value="削除"></td></tr>
						<tr><td></td><td><input type="button" value="会員一覧" onClick="location.href='Addresslist'">
				</form:form>
			</c:when>

		</c:choose>

	</body>
</html>

