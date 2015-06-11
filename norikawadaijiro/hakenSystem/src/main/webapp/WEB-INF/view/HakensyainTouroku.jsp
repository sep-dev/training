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
	<form:form modelAttribute="formModel" action="HakensyainInsert">
		<table frame="border" border="1" width="640">
			<tr>
				<th colspan="2" width="320">社員名称</th>
				<th colspan="2" width="320"><form:input path="staffname" /></th>
			</tr>
			<tr>
				<th colspan="2" width="320">メールアドレス</th>
				<th colspan="2" width="320"><form:input path="staffemail" /></th>
			</tr>
			<tr>
				<th width="80">電話番号</th>
				<th width="240"><form:input path="stafftel" /></th>
				<th width="80">携帯電話</th>
				<th width="240"><form:input path="staffmobiletel" /></th>
			</tr>
			<tr>
				<th colspan="2" width="320">郵便番号</th>
				<th colspan="2" width="320"><form:input path="staffpostcode" />
				</th>
			</tr>
			<tr>
				<th colspan="2" width="320">住所</th>
				<th colspan="2" width="320"><form:input path="staffadd" /></th>
			</tr>
			<tr>
				<th width="80">最寄駅</th>
				<th width="240"><form:input path="staffneareststation" /></th>
				<th width="80">所属企業</th>
				<th width="240"><form:select path="affiliationId">
						<c:forEach var="obj" items="${list}">
							<form:option value="${obj.affiliationId}" name="affiliationId">${obj.affiliationName}</form:option>
						</c:forEach>
					</form:select></th>
			</tr>
			<tr>
				<th colspan="2" rowspan="3" width="320">備考</th>
				<th colspan="2" rowspan="3" width="320"><form:input
						path="staffremarks" /></th>
			</tr>
		</table>
		<td><input type="submit" value="確定"></td>
	</form:form>
	<form:form modelAttribute="dataset" action="HakensyainItiran"
		method="get">
		<INPUT type="submit" value="戻る">
	</form:form>
</body>
</html>