<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		<br>
		<div align="center">
			${message}
			<form:form modelAttribute="form">
				<form:errors path="*" element="div" />
				<table border="1" style="width:65%;">
					<caption>${title}</caption>
					<tr><th colspan="2">社員名称</th><td colspan="2">
						<select name="staffid">
							<option value="-1">選択</option>
							<c:forEach var="obj" items="${staffList}" varStatus="status">
								<c:if test="${staffid ==obj.staffid}">
									<option value="${obj.staffId}" selected><c:out value="${obj.staffName}"/></option>
								</c:if>
								<c:if test="${staffid !=obj.staffId}">
									<option value="${obj.staffId}"><c:out value="${obj.staffName}"/></option>
								</c:if>
						</c:forEach></select>
					</td></tr>
					<tr><th colspan="2">派遣先企業</th><td colspan="2">
						<select name="clientid">
							<option value="-1">選択</option>
							<c:forEach var="obj" items="${clientList}" varStatus="status">
								<c:if test="${clientid ==obj.clientid}">
									<option value="${obj.clientId}" selected><c:out value="${obj.clientName}"/></option>
								</c:if>
								<c:if test="${clientid !=obj.clientid}">
									<option value="${obj.clientId}"><c:out value="${obj.clientName}"/></option>
								</c:if>
						</c:forEach></select>
					</td></tr>
					<tr><th>単金</th><td><form:input path="amountmonth" value="${amountMonth}" style="width:100%;"/></td>
					<th>条件</th><td><form:input path="conditions" value="${conditions}" style="width:100%;" /></td></tr>
					<tr><th nowrap>控除単価</th><td><form:input path="deductionunitprice" value="${deductionUnitprice}" style="width:100%;" /></td>
					<th nowrap>超過単価</th><td><form:input path="overtimerate" value="${overtimeRate}" style="width:100%;" /></td>
					<tr><th colspan="2">サイト</th><td colspan="2"><form:input path="site" value="${site}" style="width:100%;" /></td></tr>
					<tr><th nowrap>開始日</th><td>
					<form:input path="startyear" value="${startYear}" style="width:25%;" />年
					<form:input path="startmonth" value="${startMonth}" style="width:25%;"/>月
					<form:input path="startday" value="${startDay}" style="width:25%;" />日</td>
					<th nowrap>終了日</th><td>
					<form:input path="endyear" value="${endYear}" style="width:25%;" />年
					<form:input path="endmonth" value="${endMonth}" style="width:25%;" />月
					<form:input path="endday" value="${endDay}" style="width:25%;" />日</td></tr>
					<tr><th colspan="2">備考</th><td colspan="2"><textarea name="remarks"  style="width:100%;" rows="3">${staffManRemarks}</textarea></td></tr>
				</table>
				<br>
				<input type="submit" value="${submit}">
			</form:form>
			<a href="/controller/haken/hakensakiInfo" target="right">
				<input type="button" value="戻る">
			</a>
		</div>
	</body>
</html>