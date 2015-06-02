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
		<div align = "center">

		<h2>${h2}</h2>

		<h3>${h3}</h3>

		<form:form modelAttribute="SqlModel2">
			<!-- 更新 -->
			<c:if test="${list != null}">
				<input type="hidden" value="1" name="up">
					<table border="1" style="width:700px">
						<c:forEach var="obj" items="${list}" varStatus="status">
						<input type="hidden" value="${obj.staffId}" name="id">
							<tr><td colspan="2"><label>社員名称</label></td>
								<td colspan="2"><input type="text" name="name" value="${obj.staffName}"  style="width : 100%"></td></tr>
							<tr><td colspan="2"><label>メールアドレス</label></td>
								<td colspan="2"><input type="text" name="email"  value="${obj.staffEMail}" style="width : 100%"></td></tr>
							<tr><td><label>電話番号</label></td>
								<td><input type="text" name="tel"  value="${obj.staffTel}" style="width : 100%"></td>
								<td><label>携帯電話</label></td>
								<td><input type="text" name="mobtel"  value="${obj.staffMobileTel}" style="width : 100%"></td>
							</tr>
							<tr><td colspan="2"><label>郵便番号</label></td>
								<td colspan="2"><input type="text" name="post" value="${obj.staffPostCode}" style="width : 100%"></td></tr>
							<tr><td colspan="2"><label>住所</label></td>
								<td colspan="2"><textarea name="add" style="width : 100%">${obj.staffAdd}</textarea></td></tr>
							<tr><td><label>最寄駅</label></td>
								<td><input type="text" name="station" value="${obj.staffNearestStation}" style="width : 100%"></td>
								<td><label>所属企業</label></td>
								<td><select name="sub" style="width:100%" >
										<c:forEach var="obj2" items="${list2}" varStatus="status">
											<option value="${obj2.affiliationId}">${obj2.affiliationName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr><td colspan="2"><label>備考</label></td>
								<td colspan="2"><textarea name="remarks" style="width : 100%">${obj.staffRemarks}</textarea></td></tr>
						</c:forEach>
					</table>
			</c:if>

			<!-- 新規作成 -->
			<c:if test="${list == null}">
				<input type="hidden" value="2" name="up">
					<table border="1" style="width:700px">
							<tr><td colspan="2"><label>社員名称</label></td>
								<td colspan="2"><input type="text" name="name" style="width : 100%"></td></tr>
							<tr><td colspan="2"><label>メールアドレス</label></td>
								<td colspan="2"><input type="text" name="email" style="width : 100%"></td></tr>
							<tr><td><label>電話番号</label></td>
								<td><input type="text" name="tel" style="width : 100%"></td>
								<td><label>携帯電話</label></td>
								<td><input type="text" name="mobtel" style="width : 100%"></td>
							</tr>
							<tr><td colspan="2"><label>郵便番号</label></td>
								<td colspan="2"><input type="text" name="post" style="width : 100%"></td></tr>
							<tr><td colspan="2"><label>住所</label></td>
								<td colspan="2"><textarea name="add" style="width : 100%"></textarea></td></tr>
							<tr><td><label>最寄駅</label></td>
								<td><input type="text" name="station" style="width : 100%"></td>
								<td><label>所属企業</label></td>
								<td><select name="sub" style="width:100%" >
										<option value = 0>選択してください</option>
										<c:forEach var="obj2" items="${list2}" varStatus="status">
											<option value="${obj2.affiliationId}">${obj2.affiliationName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr><td colspan="2"><label>備考</label></td>
								<td colspan="2"><textarea name="remarks" style="width : 100%"></textarea></td></tr>
					</table>
			</c:if>
			<input type="submit" value="${button}" name="change">
		</form:form>

		<a href="/spring/menu/hakennsyain_info" target="info" >
			<input type="submit" value=戻る name="back">
		</a>
	</div>

	</body>
</html>