<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>派遣先登録</title>
	</head>

	<body>

		<h1>派遣先登録</h1>
		<form:form modelAttribute="formModel" action="Hakensakiinsert">
			<table frame="border" border="1" width="640">
				<tr>
					<th colspan="2" width="320">派遣社員名称</th>
					<th colspan="2" width="320">
						<form:select path="staffId">
							<c:forEach var="obj" items="${stafflist}">
								<form:option value="${obj.staffId}" name="staffId">${obj.staffName}</form:option>
							</c:forEach>
						</form:select>
					</th>
				</tr>
				<tr>
					<th colspan="2" width="320">派遣先企業</th>
					<th colspan="2" width="320">
						<form:select path="clientId">
							<c:forEach var="obj" items="${clientlist}">
								<form:option value="${obj.clientId}" name="clientId">${obj.clientName}</form:option>
							</c:forEach>
						</form:select>
					</th>
				</tr>
				<tr>
					<th width="80">単金</th>
					<th width="240">
						<form:input type="number" path="tankin" />
					</th>
					<th width="80">条件</th>
					<th width="240">
						<form:input path="jouken" />
					</th>
				</tr>
				<tr>
					<th width="80">控除単価</th>
					<th width="240">
						<form:input path="koujo" />
					</th>
					<th width="80">超過単価</th>
					<th width="240">
						<form:input path="tyouka" />
					</th>
				</tr>
				<tr>
					<th colspan="2" width="320">サイト</th>
					<th colspan="2" width="320">
						<form:input path="site" />
					</th>
				</tr>
				<tr>
					<th width="80">開始日 </th>
					<th width="240">
						<form:input path="startyear" size="3" />年
						<form:input path="startmonth" size="2" />月
						<form:input path="startday" size="2" />日
					</th>
					<th width="80">終了日</th>
					<th width="240">
						<form:input path="endyear" size="3" />年
						<form:input path="endmonth" size="2" />月
						<form:input path="endday" size="2" />日
					</th>
				</tr>
				<tr>
					<th colspan="2" rowspan="3" width="320">備考</th>
					<th colspan="2" rowspan="3" width="320">
						<form:input path="bikou" />
					</th>
				</tr>
			</table>
			<td>
			<input type="submit" value="確定">
			</td>
		</form:form>
		<form:form modelAttribute="dataset" action="HakensakiItiran" method="get">
			<INPUT type="submit" value="戻る">
		</form:form>
	</body>
</html>