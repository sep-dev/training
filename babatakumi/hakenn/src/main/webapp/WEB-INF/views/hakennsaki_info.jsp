<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>
</head>
<body>

	<h2>派遣先情報一覧</h2>
	<table>
		<form:form modelAttribute="ManagementSql">
			<tr><td><label>表示期間：</label></td>
				<td><select name="year">
					<c:forEach var="obj2" items="${list2}" varStatus="status">
						<option value="${obj2.year}">${obj2.year}</option>
					</c:forEach>
				</select>
				<label>年</label>
				<select name="month">
					<option value="01">1</option>
					<option value="02">2</option>
					<option value="03">3</option>
					<option value="04">4</option>
					<option value="05">5</option>
					<option value="06">6</option>
					<option value="07">7</option>
					<option value="08">8</option>
					<option value="09">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
				<label>月</label></td>
				</tr>

			<tr><td><label>検索：</label></td>
				<td><input type="text" name="input" ></td>
				<td><input type="submit" name ="kensaku" value="検索" ></td></tr>
		</form:form>
	</table>

		<form:form>
			<input type="submit" name ="new" value="新規登録" >
		</form:form>


	<div align=center>
		<table border=1>
		<tr>
			<td>派遣社員名</td><td>派遣先企業名</td><td>単金</td><td>条件</td><td>控除単価</td>
			<td>超過単価</td><td>サイト</td><td>開始日</td><td>終了日</td><td>備考</td><td>編集</td>
		</tr>
		<c:if test="${list!= null}">
			<c:forEach var="obj" items="${list}" varStatus="status">
				<tr>
				<td rowspan="2"><a href="/spring/menu/hakennsyain_info">
								<c:out value="${obj.staffName}" /></a></td>
				<td rowspan="2"><a href="/spring/menu/syukkousaki_info">
								<c:out value="${obj.clientName}" /></a></td>
				<td rowspan="2"><c:out value="${obj.amountMonth}" /></td>
				<td rowspan="2"><c:out value="${obj.conditions}" /></td>
				<td rowspan="2"><c:out value="${obj.deductionUnitPrice}" /></td>
				<td rowspan="2"><c:out value="${obj.overtimeRate}" /></td>
				<td rowspan="2"><c:out value="${obj.site}" /></td>
				<td rowspan="2"><c:out value="${obj.startDate}" /></td>
				<td rowspan="2"><c:out value="${obj.endDate}" /></td>
				<td rowspan="2"><c:out value="${obj.staffManRemarks}" /></td>

					<form:form modelAttribute="pageModel">
						<input type="hidden" value="${obj.staffManId}" name = "userid">
						<td><input type="submit"  name ="update" value="編集" ></td>
					</form:form></tr>

					<tr><form:form modelAttribute="pageModel">
						<input type="hidden" value="${obj.staffManId}" name = "userid">
						<td><input type="submit"  name ="delete" value="削除" ></td>
					</form:form></tr>
			</c:forEach>
		</c:if>
		</table>
	</div>
</body>
</html>