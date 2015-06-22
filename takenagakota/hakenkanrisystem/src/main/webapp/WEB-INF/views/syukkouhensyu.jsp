<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page session = "false" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出向元編集</title>
</head>
<body>
<h1>出向先編集</h1>
</body>
	<title>所属元登録</title>
</head>
<body>
<a href="./" target="_top" name="home">ログアウト</a>
	<div align="center">
<p>${message}</p>
<form:form modelAttribute="FormModel3" action="./syukkouhensyu" >
		<table border=1 style="width:500px">
		<caption>出向元編集</caption>
<tr>
	<td colspan="2">出向元名称</td>
				<td colspan="2"><form:input type="text" path="name" value="${clientName}" maxlength="255" size="30"/></td>
</tr>

<tr>
	<td colspan="2" width="30">電話番号</td>
		<th  colspan="2"><form:input type="number" path="dennwa" value="${clientTel}" maxlength="20" size="30" /></th>

</tr>
<tr>
	<td colspan="2">郵便番号</td>
		<th colspan="2"><form:input type="number" path="yuubinn"  value="${clientPostCode}" maxlength="8" size="30"/></th>
</tr>
<tr>
	<td colspan="2">住所</td>
		<th colspan="2"><form:input type="text" path="zyusyo"  value="${clientAdd}" maxlength="255" size="30"/></th>
</tr>
<tr>
	<td colspan="2">最寄駅</td>
		<th colspan="2"><form:input type="text" path="eki" value="${clientNearestStation}" maxlength="255" size="30"/></th>
</tr>
<tr>
	<td colspan="2">備考</td>
		<th colspan="2"><form:input type="text" path="bikou" value="${clientRemarks}" maxlength="1000" size="30"/></th>
</tr>

	</table>
	<input type="hidden" value="${id}" name="id">
		<input type="submit" value="登録"/>
			</form:form>
			<form action="./syukkou" method="GET">
			<input type="submit" value="戻る">
			</form>
</div>

</html>