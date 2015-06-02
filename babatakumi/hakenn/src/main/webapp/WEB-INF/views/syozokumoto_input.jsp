<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<title></title>
</head>
<body>

	<div align = "center">

		<h2>${h2}</h2>

		<form:form modelAttribute="SqlModel2">
			<!-- 更新 -->
			<c:if test="${list != null}">
				<input type="hidden" value="1" name="up">
					<table border="1" style="width:700px">
						<c:forEach var="obj" items="${list}" varStatus="status">
						<input type="hidden" value="${obj.affiliationId}" name="id">
							<tr><td><label>所属元名称</label></td>
								<td><input type="text" name="name" value="${obj.affiliationName}"  style="width : 100%"></td></tr>
							<tr><td><label>電話番号</label></td>
								<td><input type="text" name="tel"  value="${obj.affiliationTel}" style="width : 100%"></td></tr>
							<tr><td><label>郵便番号</label></td>
								<td><input type="text" name="post" value="${obj.affiliationPostCode}" style="width : 100%"></td></tr>
							<tr><td><label>住所</label></td>
								<td><textarea name="add" style="width : 100%">${obj.affiliationAdd}</textarea></td></tr>
							<tr><td><label>最寄駅</label></td>
								<td><input type="text" name="station" value="${obj.affiliationNearestStation}" style="width : 100%"></td></tr>
							<tr><td><label>備考</label></td>
								<td><textarea name="remarks" style="width : 100%">${obj.affiliationRemarks}</textarea></td></tr>
						</c:forEach>
					</table>
			</c:if>

				<!-- 新規作成 -->
				<c:if test="${list == null}">
					<input type="hidden" value="2" name="up">
						<table border="1" style="width:700px">
							<tr><td><label>所属元名称</label></td>
								<td><input type="text" name="name"  style="width : 100%"></td></tr>
							<tr><td><label>電話番号</label></td>
								<td><input type="text" name="tel" style="width : 100%"></td></tr>
							<tr><td><label>郵便番号</label></td>
								<td><input type="text" name="post" style="width : 100%"></td></tr>
							<tr><td><label>住所</label></td>
								<td><textarea name="add" style="width : 100%"></textarea></td></tr>
							<tr><td><label>最寄駅</label></td>
								<td><input type="text" name="station"style="width : 100%"></td></tr>
							<tr><td><label>備考</label></td>
								<td><textarea name="remarks" style="width : 100%"></textarea></td></tr>
						</table>
				</c:if>
			<input type="submit" value="${button}" name="change">
		</form:form>

		<a href="/spring/menu/syozokumoto_info" target="info" >
			<input type="submit" value=戻る name="back">
		</a>
	</div>
</body>
</html>