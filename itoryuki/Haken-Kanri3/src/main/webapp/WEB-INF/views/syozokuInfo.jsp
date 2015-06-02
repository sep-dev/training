<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 所属元情報のページ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8)">
		<meta http-equiv="Content-Script-Type" content="text/javascript">
			<script type="text/javascript"><!--
				function kakunin(){
					if(window.confirm( 'この派遣先情報を削除しますか？')){
						return true;
					}else{
						return false;
					}
				}
			// --></script>
		<title>所属元情報</title>
	</head>
	<body>
	<div align="center">
		<h2>所属元情報一覧</h2>
		<font size="5" color="red">${message}</font>
	</div>
	<br>
	<!-- 検索-->
		<form action="Syozoku" method="post">
			検索：<input type="text" maxlength="20" name="asearch">　<input type="submit" value="検索" name="syozokuSearch"/>
		</form>
	<!-- 新規登録 -->
		<form action="Syozoku">
			<p><input type="submit" name="syozokuInsert1" value="新規登録" /></P>
		</form>
		<table border=1 width="100%">
			<tr><th>企業名</th>
			<th>郵便番号</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>最寄駅</th>
			<th>備考</th>
			<th>編集</th></tr>
		<c:forEach var="obj" items="${data}" >
			<tr><td rowspan=2>
						<a href="syozokuUpdate3 ?value='${obj.affiliationId}'" name="affiliationid" target="contents"><c:out value="${obj.affiliationName}"/></a>
			<td rowspan=2><c:out value="${obj.affiliationPostCode}"/></td>
			<td rowspan=2><c:out value="${obj.affiliationAdd}" /></td>
			<td rowspan=2><c:out value="${obj.affiliationTel}"/></td>
			<td rowspan=2><c:out value="${obj.affiliationNearestStation}"/></td>
			<td rowspan=2><c:out value="${obj.affiliationRemarks}"/></td>
			<td width="40px">
				<form action="Syozoku">
					<input type="hidden" value="${obj.affiliationId}" name="affiliationid">
					<input type="submit" name="syozokuUpdate1" value="編集" />
				</form></td></tr>
			<tr><td width="40px">
				<form action="Syozoku" method="post">
					<input type="hidden" value="${obj.affiliationId}" name="affiliationid">
					<input type="submit" name="syozokuDelete" value="削除" onClick="return kakunin();">
				</form></td></tr>
		</c:forEach>
		</table>
	</body>
</html>