<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
		<script type="text/javascript">
		<!--
				function disp(){
					if(window.confirm('削除しますか？')){
						return true;
					}else{
						return false;
					}
				}
		//-->
		</script>
	</head>
	<body>
		<a href="/controller/logout" target="_top">ログアウト</a>
		<br>
		<div align="center">
			<h3>派遣先情報一覧</h3>
		</div>
		<br>
		<form method="post" action="/controller/haken/hakensakiSelect">
			表示期間：
			<select name="year">
				<c:forEach var="list" items="${year}" varStatus="status">
					<c:if test="${list.year !=null}">
						<option label="${list.year}"><c:out value="${list.year}"/></option>
					</c:if>
				</c:forEach>
			</select>年
			<select name="month">
				<option label="01">01</option>
				<option label="02">02</option>
				<option label="03">03</option>
				<option label="04">04</option>
				<option label="05">05</option>
				<option label="06">06</option>
				<option label="07">07</option>
				<option label="08">08</option>
				<option label="09">09</option>
				<option label="10">10</option>
				<option label="11">11</option>
				<option label="12">12</option>
			</select>月
			<br>
			検索：<input type="text" name="input"/> <input type="submit" value="検索">
		</form>

		<a href="/controller/haken/hakensakiInsert" target="right">
			<input type="submit" value="新規登録">
		</a>

		<c:if test="${data !=null}">
			<div align="center">
				<table border="1" style="width:80%">
					<tr><th>派遣社員名</th><th>派遣先企業名</th><th>単金</th><th>条件</th><th>控除単金</th><th>超過単金</th><th>サイト</th>
					<th>開始日</th><th>終了日</th><th>備考</th><th>編集</th></tr>
					<c:forEach var="obj" items="${data}" varStatus="status">
						<c:if test="${obj.staffName !=null}">
							<tr><td rowspan="2"><a href="/controller/haken/shainUpdate ?id=${obj.staffid}" target="right"><c:out value="${obj.staffName}" /></a></td>
							<td rowspan="2"><a href="/controller/haken/shukkosakiUpdate ?id=${obj.clientid}" target="right"><c:out value="${obj.clientName}" /></a></td>
							<td rowspan="2"><c:out value="${obj.amountMonth}" /></td>
							<td rowspan="2"><c:out value="${obj.conditions}" /></td>
							<td rowspan="2"><c:out value="${obj.deductionUnitPrice}" /></td>
							<td rowspan="2"><c:out value="${obj.overtimeRate}" /></td>
							<td rowspan="2"><c:out value="${obj.site}"></c:out></td>
							<td rowspan="2"><c:out value="${obj.startDate}" /></td>
							<td rowspan="2"><c:out value="${obj.endDate}" /></td>
							<td rowspan="2"><c:out value="${obj.staffManRemarks}" /></td>
							<td>
							<form name="form1" method="get" action="/controller/haken/hakensakiUpdate" target="right">
								<input type="hidden" name="id" value="${obj.StaffManId}">
								<input type="submit" value="編集">
							</form>
							</td></tr>
							<tr><td>
								<form name="form2" method="post" action="/controller/haken/hakensakiDelete" target="right">
									<input type="hidden" name="id" value="${obj.StaffManId}">
									<input type="submit" value="削除" onClick="return disp();">
								</form>
							</td></tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</body>
</html>
