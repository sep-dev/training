<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>派遣社員登録</h1>
	<form:form modelAttribute="formModel" action="Hakensyainupdate">
		<table frame="border" border="1" width="640">
			<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th colspan="2" width="320">社員名称</th>
					<th colspan="2" width="320"><form:input path="staffname"
							value="${obj.staffName}" /></th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th colspan="2" width="320">メールアドレス</th>
					<th colspan="2" width="320"><form:input path="staffemail"
							value="${obj.staffEMail}" /></th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th width="80">電話番号</th>
					<th width="240"><form:input path="stafftel"
							value="${obj.staffTel}" /></th>
					<th width="80">携帯電話</th>
					<th width="240"><form:input path="staffmobiletel"
							value="${obj.staffMobiletel}" /></th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th colspan="2" width="320">郵便番号</th>
					<th colspan="2" width="320"><form:input path="staffpostcode"
							value="${obj.staffPostCode}" /></th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th colspan="2" width="320">住所</th>
					<th colspan="2" width="320"><form:input path="staffadd"
							value="${obj.staffAdd}" /></th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th width="80">最寄駅</th>
					<th width="240"><form:input path="staffneareststation"
							value="${obj.staffNearestStation }" /></th>
					<th width="80">所属企業</th>
					<th width="240"><form:select path="affiliationId">
							<c:forEach var="obj" items="${list}">
								<form:option value="${obj.affiliationId}" name="affiliationId">${obj.affiliationName}</form:option>
							</c:forEach>
						</form:select></th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="obj" items="${updatelist}">
					<th colspan="2" rowspan="3" width="320">備考</th>
					<th colspan="2" rowspan="3" width="320"><form:input
							path="staffremarks" value="${obj.staffRemarks}" /></th>
				</c:forEach>
			</tr>
		</table>
		<td><input type="hidden" value="${updatestaffId}"
			name="updatestaffId"> <input type="submit" value="確定">
		</td>
	</form:form>
	<form:form modelAttribute="dataset" action="HakensyainItiran"
		method="get">
		<INPUT type="submit" value="戻る">
	</form:form>
</body>
</html>