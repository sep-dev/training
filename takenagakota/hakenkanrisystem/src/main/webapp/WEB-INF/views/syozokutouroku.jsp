<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page session = "false" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所属元登録</title>
</head>
<body>
<a href="./" target="_top" name="home">ログアウト</a>
	<div align="center">
<p>${message}</p>
<form:form modelAttribute="FormModel3" action="./syozokutouroku" >
		<table border=1 style="width:500px">
		<caption>所属元登録</caption>
<tr>
	<td colspan="2">所属元名称</td>
				<td colspan="2"><form:input type="text" path="name" maxlength="255" size="30"/></td>
</tr>

<tr>
	<td colspan="2" width="30">電話番号</td>
		<th  colspan="2"><form:input  type="number" path="dennwa" maxlength="20" size="30"/></th>

</tr>
<tr>
	<td colspan="2">郵便番号</td>
		<th colspan="2"><form:input type="number" path="yuubinn" maxlength="8" size="30"/></th>
</tr>
<tr>
	<td colspan="2">住所</td>
		<th colspan="2"><form:input type="text" path="zyusyo" maxlength="255" size="30"/></th>
</tr>
<tr>
	<td colspan="2">最寄駅</td>
		<th colspan="2"><form:input type="text" path="eki"  maxlength="255" size="30"/></th>
</tr>
<tr>
	<td colspan="2">備考</td>
		<th colspan="2"><form:input type="text" path="bikou" maxlength="1000" size="30"/></th>
</tr>

	</table>

		<input type="submit" value="登録"/>
			</form:form>
			<form action="./syozoku" method="GET">
			<input type="submit" value="戻る">
			</form>
</div>
</body>
</html>