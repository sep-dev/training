<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 派遣先情報一覧のページ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8)">
		<meta http-equiv="Content-Script-Type" content="text/javascript">
			<!-- 削除確認 -->
			<script type="text/javascript"><!--
				function kakunin(){
					if(window.confirm( 'この派遣先情報を削除しますか？')){
						return true;
					}else{
						return false;
					}
				}
			// --></script>
		<title>派遣先一覧</title>
	</head>
	<body>
	<div align="center">
		<h2>派遣先情報一覧</h2>
		<font size="5" color="red">${message}</font>
	</div>
	<br>
	<!-- 検索-->
		<form action="Haken" method="post">
			<p>表示期間：<select name="startdate1">
									<c:forEach var="obj" items="${ydata}">
										<option><c:out value="${obj.syear}"/></option>
									</c:forEach>
								</select>年
								<select name="startdate2">
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select>月</p>
			検索：<input type="text" maxlength="20" name="hsearch">　<input type="submit" value="検索" name="hakenSearch"/>
		</form>
	<br>
	<!-- 新規登録 -->
		<form action="Haken">
			<input type="submit" name="hakenInsert1" value="新規登録" />
		</form>
	<br>
	<!-- 取得した派遣先情報を表示 -->
		<table border=1 width="100%" >
			<tr><th>派遣社員名</th>
			<th>派遣先企業名</th>
			<th>単金</th>
			<th>条件</th>
			<th>控除単金</th>
			<th>超過単金</th>
			<th>サイト</th>
			<th>開始日</th>
			<th>終了日</th>
			<th>備考</th>
			<th>編集</th></tr>
		<c:forEach var="obj" items="${data}">
			<tr><td rowspan=2>
					<!-- 社員情報編集へ -->
						<a href="syainUpdate3 ?value='${obj.staffId}'" name="staffid" target="contents" ><c:out value="${obj.staffName}"/></a></td>
			<td rowspan=2>
					<!-- 出向先情報編集へ -->
						<a href="syukkoUpdate3 ?value='${obj.clientId}'" name="clientid" target="contents"><c:out value="${obj.clientName}"/></a></td>
			<td rowspan=2><c:out value="${obj.amountMonth}"/>円</td>
			<td rowspan=2><c:out value="${obj.conditions}" /></td>
			<td rowspan=2><c:out value="${obj.deductionUnitPrice}"/>円</td>
			<td rowspan=2><c:out value="${obj.overtimeRate}"/>円</td>
			<td rowspan=2><c:out value="${obj.site}"/></td>
			<td rowspan=2><c:out value="${obj.startDate}"/></td>
			<td rowspan=2><c:out value="${obj.endDate}"/></td>
			<td rowspan=2><c:out value="${obj.staffManRemarks}"/></td>
			<td width="40px">
				<form action="Haken"><!-- 編集画面へ -->
					<input type="hidden" value="${obj.staffManId}" name="staffmanid">
					<input type="submit" name="hakenUpdate1" value="編集" />
				</form></td></tr>
			<tr><td width="40px"><!-- 削除確認へ -->
				<form action="Haken" method="post">
					<input type="hidden" value="${obj.staffManId}" name="staffmanid">
					<input type="submit" value="削除" name="hakenDelete"  onClick="return kakunin();">
				</form></td></tr>
			</c:forEach>
		</table>
	</body>
</html>