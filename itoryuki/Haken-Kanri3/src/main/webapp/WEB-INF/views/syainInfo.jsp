<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 派遣社員情報のページ -->
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
		<title>派遣社員情報</title>
	</head>
	<body>
	<div align="center">
		<h2>派遣社員情報一覧</h2>
		<font size="5" color="red">${message}</font>
	</div>
	<br>
	<!-- 検索-->
		<form action="Syain" method="post">
			検索：<input type="text" maxlength="20" name="ssearch">　<input type="submit" value="検索" name="syainSearch"/>
		</form>
	<!-- 新規登録 -->
		<form action="Syain">
			<p><input type="submit" name="syainInsert1" value="新規登録" /></p>
		</form>
		<table border=1 width="100%">
			<tr><th>社員名</th>
			<th>メールアドレス</th>
			<th>郵便番号</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>携帯番号</th>
			<th>最寄駅</th>
			<th>所属企業名</th>
			<th>備考</th>
			<th>編集</th></tr>
		<c:forEach var="obj" items="${data}" >
			<tr><td rowspan=2>
						<!-- ※下記の編集ボタンと同じ機能 -->
						<a href="syainUpdate3 ?value='${obj.staffId}'" name="staffid" target="contents" ><c:out value="${obj.staffName}"/></a></td>
			<td rowspan=2><c:out value="${obj.staffEMail}"/></td>
			<td rowspan=2><c:out value="${obj.staffPostCode}"/></td>
			<td rowspan=2><c:out value="${obj.staffAdd}" /></td>
			<td rowspan=2><c:out value="${obj.staffTel}"/></td>
			<td rowspan=2><c:out value="${obj.staffMobileTel}"/></td>
			<td rowspan=2><c:out value="${obj.staffNearestStation}"/></td>
			<td rowspan=2><c:out value="${obj.affiliationName}"/></td>
			<td rowspan=2><c:out value="${obj.staffRemarks}"/></td>
			<td width="40px">
				<form action="Syain"><!-- 編集画面へ -->
					<input type="hidden" value="${obj.staffId}" name="staffid">
					<input type="submit" name="syainUpdate1" value="編集" />
				</form></td></tr>
			<tr><td width="40px"><!-- 削除確認 -->
				<form action="Syain" method="post">
					<input type="hidden" value="${obj.staffId}" name="staffid">
					<input type="submit" value="削除" name="syainDelete"  onClick="return kakunin();">
				</form></td></tr>
		</c:forEach>
		</table>
	</body>
</html>