<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page session = "false" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>派遣社員編集</title>
	</head>
	<body>
			<a href="./" target="_top" name="home">ログアウト</a>
		<div align="center">
			<p>${message}</p>
		<form:form modelAttribute="FormModel2" action="./syainhensyu" >
			<table border="1"  style="width:500px">
				<caption>派遣者社員編集</caption>
				<tr>
					<td colspan="2" align="center">社員名称</td>
					<td colspan="2"><form:input path="name" value="${staffName}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">メールアドレス</td>
					<td colspan="2"><form:input path="meru" value="${staffMail}"/></td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td align="center"><form:input type="number" path="dennwa" value="${staffTel}" /></td>
					<td>携帯電話</td>
					<td align="center"><form:input type="number" path="keitai" value="${staffMobileTel}"/></td>
				</tr>
				<tr>
					<td colspan="2">郵便番号</td>
					<th colspan="2"><form:input  type="number" path="yuubinn" value="${staffPostCode}"/></th>
				</tr>
				<tr>
					<td colspan="2">住所</td>
					<th colspan="2"><form:input path="zyusyo" value="${staffAdd}"/></th>
				</tr>
				<tr>
					<td colspan="2">最寄駅</td>
					<td><form:input path="eki" value="${staffNearestStation}"/></td>
				</tr>
				<tr>
					<td colspan="2">所属企業</td>
					<td colspan="2">
					<form:select path="kaisyaID" size="1">
					<c:forEach var="obj" items="${data}">
						<option value="${obj.affiliationId}" > ${obj.affiliationName}</option>
					</c:forEach>
					</form:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">備考</td>
					<td><form:input path="bikou" value="${staffRemarks}"/></td>
				</tr>
			</table>
		<form:hidden path="id" value="${id}"/>
				<input type="submit" value="確定"/>
		</form:form>
		<form action="./syain" method="GET">
			<input type="submit" value="戻る">
		</form>
		</div>
	</body>
</html>