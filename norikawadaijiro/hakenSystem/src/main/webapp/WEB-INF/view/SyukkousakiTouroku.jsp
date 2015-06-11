<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出向先登録</title>
</head>
<body>
	<h1>出向先登録</h1>
	<form:form modelAttribute="formModel" action="SyukkousakiInsert">
		<table frame="border" border="1" width="640">
			<tr>
				<td width="240">出向先名称</td>
				<td width="400">
					<form:input path="clientname"/>
				</td>
			</tr>
			<tr>
				<td width="240">電話番号</td>
				<td width="400">
					<form:input path="clienttel"/>
				</td>
			</tr>
			<tr>
				<td width="240">郵便番号</td>
				<td width="400">
					<form:input path="clientpostcode"/>
				</td>
			</tr>
			<tr>
				<td width="240">住所</td>
				<td width="400">
					<form:input path="clientadd"/>
				</td>
			</tr>
						<tr>
				<td width="240">最寄駅</td>
				<td width="400">
					<form:input path="clientneareststation"/>
				</td>
			</tr>
						<tr>
				<td width="240">備考</td>
				<td width="400">
					<form:input path="clientremarks"/>
				</td>
			</tr>
		</table>
		<td>
			<input type="submit" value="確定">
			</td>
		</form:form>
		<form:form modelAttribute="dataset" action="SyukkousakiItiran" method="get">
			<INPUT type="submit" value="戻る">
		</form:form>
</body>
</html>