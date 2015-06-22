<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@page import="java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派遣先登録</title>
</head>
<body>
<a href="./" target="_top" name="home">ログイン</a>
	<div align="center">
<p>${message}</p>
<form:form modelAttribute="FormModel" action="./touroku" >
		<table border=1>
		<caption>派遣先登録</caption>
			<form:errors path="*" element="div"/>
			<tr>
				<td colspan="2" align="center">派遣社員名称 </td>
				<td colspan="2">
				<select name="syainID">
					<option value="">選択</option>
					<c:forEach var="obj" items="${syamei}">
						<option value="${obj.staffId }"> ${obj.staffName}</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">派遣先企業</td>
				<td colspan="2">
				<form:select path="kaisya" size="1">
				<option value="">選択</option>
				<c:forEach var="obj" items="${kaisya}">
					<option value="${obj.clientID}" > ${obj.clientName}</option>
				</c:forEach>
				</form:select>
				</td>
			</tr>
			<tr>
				<td>単金</td>
				<td align="center"><form:input type="number" path="tankin" size="25" /></td>
				<td>条件</td>
				<td align="center"><form:input type="text" path="zyouken" maxlength="255" size="25"/></td>
			</tr>
			<tr>
				<td>控除単価</td>
				<th ><form:input type="number" path="kouzyo" size="25"/></th>
				<td>超過単価</td>
				<th><form:input type="number" path="tyouka" size="25"/></th>
			</tr>
			<tr>
				<td colspan="1">サイト</td>
				<th colspan="3"><form:input type="text" path="saito" maxlength="255" size="70" /></th>
			</tr>
			<tr>
				<td>開始日</td>
				<th><form:input type="number" path="nen" size="3"/>年
				<form:input type="number" path="gatu" size="1"/>月
				<form:input type="number" path="hi" size="1"/>日</th>
				<td>終了</td>
				<th><form:input type="number" path="onen" size="3"/>年
				<form:input type="number" path="ogatu" size="1"/>月
				<form:input type="number" path="ohi" size="1"/>日</th>
			</tr>
			<tr>
				<td colspan="2" height="60px" align="center">備考</td>
				<th colspan="2"><form:input type="text" path="bikou" size="40"/></th>
			</tr>
		</table>
		<form:hidden path="id" />
		<input type="submit" value="登録"/>
			</form:form>
			<form action="./zenn" method="GET">
			<input type="submit" value="戻る">
			</form>

	</div>
</body>
</html>