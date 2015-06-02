<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 所属元登録/編集のページ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<!-- 所属元登録 -->
	<c:if test="${List == 'null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="<c:url value="/resources/CSS/menuCSS.css" />" rel="stylesheet">
			<title>所属元登録</title>
		</head>
		<body>
		<div id="header">
			<h2>所属元登録</h2>
		</div>
		<br>
		<div align="center">
			<form:form modelAttribute="syozokuModel">
			<table border=1>
				<tr><th colspan=2>所属元名称</th><td colspan=2><form:input path="affiliationname"/></td></tr>
				<tr><th colspan=2>電話番号</th><td colspan=2><form:input path="affiliationtel" maxlength='14' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>郵便番号</th><td colspan=2><form:input path="affiliationpostcode" maxlength='8' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>住所</th><td colspan=2><form:input path="affiliationadd"/></td></tr>
				<tr><th colspan=2>最寄駅</th><td colspan=2><form:input path="affiliationneareststation"/></td></tr>
				<tr><th colspan=2>備考</th><td colspan=2><form:input path="affiliationremarks"/></td></tr>
			</table>
		<br>
			<input type="submit" name="syozokuInsert2" value="登録" />　
			<input type="button" onclick="javascript: history.back();" value="戻る">　
			<input type="reset" value="リセット" />
			</form:form>
		</div>
		</body>
	</html>
	</c:if>

	<!-- 所属元編集 -->
	<c:if test="${List == 'edit'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="<c:url value="/resources/CSS/menuCSS.css" />" rel="stylesheet">
			<title>所属元編集</title>
		</head>
		<body>
		<div id="header">
			<h2>所属元編集</h2>
		</div>
		<br>
		<div align="center">
			<form:form modelAttribute="syozokuModel">
			<table border=1>
				<tr><th colspan=2>所属元名称</th><td colspan=2><form:input path="affiliationname" value="${affiliationName}"/></td></tr>
				<tr><th colspan=2>電話番号</th><td colspan=2><form:input path="affiliationtel" value="${affiliationTel}" maxlength='14' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>郵便番号</th><td colspan=2><form:input path="affiliationpostcode" value="${affiliationPostCode}" maxlength='8' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>住所</th><td colspan=2><form:input path="affiliationadd" value="${affiliationAdd}"/></td></tr>
				<tr><th colspan=2>最寄駅</th><td colspan=2><form:input path="affiliationneareststation" value="${affiliationNearestStation}"/></td></tr>
				<tr><th colspan=2>備考</th><td colspan=2><form:input path="affiliationremarks" value="${affiliationRemarks}"/></td></tr>
			</table>
		<br>
				<input type="submit" name="syozokuUpdate2" value="確定" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
		</div>
		</body>
	</html>
	</c:if>