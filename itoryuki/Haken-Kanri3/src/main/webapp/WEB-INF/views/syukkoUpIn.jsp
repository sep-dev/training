<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 出向先登録/編集のページ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<!-- 出向先登録 -->
	<c:if test="${List == 'null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>出向先登録</title>
		</head>
		<body>
		<div align="center">
			<h2>出向先登録</h2>
		</div>
		<br>
		<div align="center">
			<form:form modelAttribute="syukkoModel" action="syukkoInsert2" >
			<input type="hidden" value="${clientId}" name="clientid">
			<table border=1>
				<tr><th colspan=2>出向先名称</th><td colspan=2><form:input path="clientname"/></td></tr>
				<tr><th colspan=2>電話番号</th><td colspan=2><form:input path="clienttel" maxlength='14' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>郵便番号</th><td colspan=2><form:input path="clientpostcode" maxlength='8' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>住所</th><td colspan=2><form:input path="clientadd"/></td></tr>
				<tr><th colspan=2>最寄駅</th><td colspan=2><form:input path="clientneareststation"/></td></tr>
				<tr><th colspan=2>備考</th><td colspan=2><form:input path="clientremarks"/></td></tr>
			</table>
		<br>
				<input type="submit" value="登録" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
			<p><font color="red">※備考は空欄可。</font></p>
		</div>
		</body>
	</html>
	</c:if>

	<!-- 出向先情報編集 -->
	<c:if test="${List !='null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>出向先情報編集</title>
		</head>
		<body>
		<div align="center">
			<h2>出向先情報編集</h2>
		</div>
		<br>
		<div align="center">
			<form:form modelAttribute="syukkoModel"  action="syukkoUpdate3">
			<input type="hidden" value="${clientId}" name="clientid">
			<table border=1>
				<tr><th colspan=2>出向先名称</th><td colspan=2><form:input path="clientname" value="${clientName}"/></td></tr>
				<tr><th colspan=2>電話番号</th><td colspan=2><form:input path="clienttel" value="${clientTel}" maxlength='14' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>郵便番号</th><td colspan=2><form:input path="clientpostcode" value="${clientPostCode}" maxlength='8' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>住所</th><td colspan=2><form:input path="clientadd" value="${clientAdd}"/></td></tr>
				<tr><th colspan=2>最寄駅</th><td colspan=2><form:input path="clientneareststation" value="${clientNearestStation}"/></td></tr>
				<tr><th colspan=2>備考</th><td colspan=2><form:input path="clientremarks" value="${clientRemarks}"/></td></tr>
			</table>
		<br>
				<input type="submit" value="確定" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
			<p><font color="red">※備考は空欄可。</font></p>
		</div>
		</body>
	</html>
	</c:if>