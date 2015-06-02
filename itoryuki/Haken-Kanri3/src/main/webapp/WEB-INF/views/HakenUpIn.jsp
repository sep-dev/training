<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 派遣先登録/編集のページ -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<!-- 派遣先情報登録 -->
	<c:if test="${List == 'null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>派遣先新規登録</title>
		</head>
		<body>
			<div align="center">
				<h2>派遣先新規登録</h2>
			</div>
			<br>
			<div align="center">
			<form:form modelAttribute="hakenModel">
				<table border=1>
					<tr><th colspan=2>派遣社員名称</th>
							<td colspan=2>
								<form:select path="staffid">
										<c:forEach var="obj" items="${Sdata}" >
											<form:option value="${obj.staffId}"><c:out value="${obj.staffName}"/></form:option>
										</c:forEach>
								</form:select></td></tr>
					<tr><th colspan=2>派遣先企業</th>
							<td colspan=2>
								<form:select  path="clientid">
										<c:forEach var="obj" items="${Cdata}">
											<form:option value="${obj.clientId}"><c:out value="${obj.clientName}"/></form:option>
										</c:forEach>
								</form:select></td></tr>
					<tr><th>単金</th><td><form:input path="amountmonth" pattern="^[0-9]+$"/>円</td>
							<th>条件</th><td><form:input path="conditions" /></td></tr>
					<tr><th>控除単価</th><td><form:input path="deductionunitprice" pattern="^[0-9]+$"/>円</td>
							<th>超過単価</th><td><form:input path="overtimerate" pattern="^[0-9]+$"/>円</td></tr>
					<tr><th colspan=2>サイト</th><td colspan=2><form:input path="site" /></td></tr>
					<tr><th>開始日</th><td><form:input path="startdate1" maxlength="4" size="4" pattern="^[0-9]+$"/>年
									<form:input path="startdate2" maxlength="2" size="2" pattern="^[0-9]+$"/>月
									<form:input path="startdate3" maxlength="2" size="2" pattern="^[0-9]+$"/>日</td>
							<th>終了日</th><td><form:input path="enddate1" maxlength="4" size="4" pattern="^[0-9]+$"/>年
									<form:input path="enddate2" maxlength="2" size="2" pattern="^[0-9]+$"/>月
									<form:input path="enddate3" maxlength="2" size="2" pattern="^[0-9]+$"/>日</td></tr>
					<tr><th colspan=2 rowspan=3>備考</th><td colspan=2 rowspan=3><form:input path="staffmanremarks" /></td></tr>
				</table>
			<br>
				<input type="submit" name="hakenInsert2" value="登録" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
			</div>
		</body>
	</html>
	</c:if>

	<!-- 派遣先情報編集 -->
	<c:if test="${List != 'null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="<c:url value="/resources/CSS/menuCSS.css" />" rel="stylesheet">
			<title>派遣先情報編集</title>
		</head>
		<body>
		<div align="center">
			<h2>派遣先情報編集</h2>
		</div>
		<br>
		<div align="center">
			<form:form modelAttribute="hakenModel">
			<table border=1>
				<tr><th colspan=2>派遣社員名称</th>
						<td colspan=2>
							<form:select path="staffid">
									<c:forEach var="obj" items="${Sdata}" >
										<form:option value="${obj.staffId}">
											<c:out value="${obj.staffName}"/></form:option>
									</c:forEach>
							</form:select></td></tr>
				<tr><th colspan=2>派遣先企業</th>
						<td colspan=2>
							<form:select  path="clientid">
									<c:forEach var="obj" items="${Cdata}">
										<form:option value="${obj.clientId}"><c:out value="${obj.clientName}"/></form:option>
									</c:forEach>
							</form:select></td></tr>
				<tr><th>単金</th><td><form:input path="amountmonth" value="${amountMonth}"/></td>
						<th>条件</th><td><form:input path="conditions" value="${conditions}"/></td></tr>
				<tr><th>控除単価</th><td><form:input path="deductionunitprice" value="${deductionUnitPrice}"/></td>
						<th>超過単価</th><td><form:input path="overtimerate" value="${overtimeRate}"/></td></tr>
				<tr><th colspan=2>サイト</th><td colspan=2><form:input path="site" value="${site}"/></td></tr>
				<tr><th>開始日</th><td><form:input path="startdate1" maxlength="4" size="4" value="${syear}"/>年
								<form:input path="startdate2"  maxlength="2" size="2" value="${smonth}"/>月
								<form:input path="startdate3"  maxlength="2" size="2" value="${sday}"/>日</td>
						<th>終了日</th><td><form:input path="enddate1" maxlength="4" size="4" value="${eyear}"/>年
								<form:input path="enddate2"  maxlength="2" size="2" value="${emonth}"/>月
								<form:input path="enddate3"  maxlength="2" size="2" value="${eday}"/>日</td></tr>
				<tr><th colspan=2>備考</th><td colspan=2><form:input path="staffmanremarks" /></td></tr>
			</table>
		<br>
				<input type="submit" name="hakenUpdate2" value="確定" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
		</div>
		</body>
	</html>
	</c:if>