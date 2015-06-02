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

	<h2>派遣社員情報</h2>
	<h3>${h3}</h3>

	<table>
		<form:form modelAttribute ="SqlModel2">
			<tr><td><label>検索：</label></td>
				<td><input type="text" name="name" ></td>
				<td><input type="submit" name ="kensaku" value="検索" ></td></tr>
		</form:form>
	</table>
	<form:form>
			<input type="submit" name ="new" value="新規登録" >
		</form:form>


<div align=center>
		<table border=1>
		<tr>
			<td>社員名</td><td>メールアドレス</td><td>郵便番号</td><td>住所</td><td>電話番号</td>
			<td>携帯電話番号</td><td>最寄駅</td><td>所属企業名</td><td>備考</td><td>編集</td>
		</tr>
		<c:if test="${list != null}">
			<c:forEach var="obj" items="${list}" varStatus="status">
				<tr>
				<td rowspan="2"><c:out value="${obj.staffName}" /></td>
				<td rowspan="2"><c:out value="${obj.staffEMail}" /></td>
				<td rowspan="2"><c:out value="${obj.staffPostCode}" /></td>
				<td rowspan="2"><c:out value="${obj.staffAdd}" /></td>
				<td rowspan="2"><c:out value="${obj.staffTel}" /></td>
				<td rowspan="2"><c:out value="${obj.staffMobileTel}" /></td>
				<td rowspan="2"><c:out value="${obj.staffNearestStation}" /></td>
				<td rowspan="2"><c:out value="${obj.affiliationName}" /></td>
				<td rowspan="2"><c:out value="${obj.staffRemarks}" /></td>

				<form:form modelAttribute="pageModel">
								<input type="hidden" value="${obj.staffId}" name="userid">
								<td><input type="submit"  name ="update" value="編集" ></td>
							</form:form></tr>

							<tr><form:form modelAttribute="pageModel">
								<input type="hidden" value="${obj.staffId}" name="userid">
								<td><input type="submit"  name ="delete" value="削除" ></td>
								</form:form>
							</tr>
			</c:forEach>
		</c:if>
		</table>
	</div>
</body>
</html>