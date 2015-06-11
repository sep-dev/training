<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>派遣先各種</title>
	</head>

	<body>
		<h1>派遣先情報一覧</h1>
		<form:form modelAttribute="formModel" action="HakensakiUpdate">
			<table frame="border" border="1" width="640">
				<tr>
					<th colspan="2" width="320">派遣社員名称</th>
					<th colspan="2" width="320">
						<form:select path="syainId">
						<c:forEach var="obj" items="${stafflist}">
							<form:option value="${obj.staffId}" name="syainId">${obj.staffName }</form:option>
						</c:forEach>
						</form:select>
					</th>


				</tr>
				<tr>
					<th colspan="2" width="320">派遣先企業</th>
					<th colspan="2" width="320">
						<form:select path="hakensakiId">
							<c:forEach var="obj" items="${clientlist}">
								<form:option value="${obj.clientId}" name="hakensakiId">${obj.clientName}</form:option>
							</c:forEach>
						</form:select>
					</th>
				</tr>
				<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th width="80">単金</th>
					<th width="240">
						<form:input path="tankin" value="${obj.amountMonth}" />
					</th>
					<th width="80">条件</th>
					<th width="240">
						<form:input path="jouken" value="${obj.conditions}" />
					</th>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th width="80">控除単価</th>
					<th width="240">
						<form:input path="koujo" value="${obj.deductionUnitPrice}" />
					</th>
					<th width="80">超過単価</th>
					<th width="240">
						<form:input path="tyouka" value="${obj.overtimeRate}" />
					</th>
				</c:forEach>
				</tr>

				<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th colspan="2" width="320">サイト</th>
					<th colspan="2" width="320">
						<form:input path="site" value="${obj.site}" />
					</th>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th width="80">開始日 </th>
					<th width="240">
						<form:input path="startyear" size="3" value="${obj.startyear}" />年
						<form:input path="startmonth" size="2" value="${obj.startmonth}" />月
						<form:input path="startday" size="2" value="${obj.startday}" />日
					</th>
					<th width="80">終了日</th>
					<th width="240">
						<form:input path="endyear" size="3" value="${obj.endyear}" />年
						<form:input path="endmonth" size="2" value="${obj.endmonth}" />月
						<form:input path="endday" size="2" value="${obj.endday}" />日
					</th>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th colspan="2" rowspan="3" width="320">備考</th>
					<th colspan="2" rowspan="3" width="320">
						<form:input path="bikou" value="${obj.staffManRemarks}" />
					</th>
				</c:forEach>
				</tr>
			</table>
			<td>
			<c:forEach var="obj" items="${updatelist}">
			<input type="hidden" value="${obj.clientId}" name="clientId">
			<input type="hidden" value="${obj.staffId}" name="staffId">
			</c:forEach>
			<input type="hidden" value="${staffManId}" name="updatestaffManId">
			<input type="submit" value="確定">
			</td>
		</form:form>
		<form:form modelAttribute="dataset" action="HakensakiItiran" method="get">
			<INPUT type="submit" value="戻る">
		</form:form>
	</body>
</html>