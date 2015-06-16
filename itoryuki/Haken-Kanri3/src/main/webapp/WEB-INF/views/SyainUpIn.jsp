<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 派遣社員情報登録/編集 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<!-- 派遣社員登録 -->
	<c:if test="${List == 'null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8)">
			<title>派遣社員登録</title>
		</head>
		<body>
		<div align="center">
			<h2>派遣社員登録</h2>
		</div>
		<br>
		<div align="center">
			<form:form modelAttribute="syainModel"  action="syainInsert2">
			<table border=1>
				<tr><th colspan=2>社員名称</th><td colspan=2><form:input path="staffname" /></td></tr>
				<tr><th colspan=2>メールアドレス</th><td colspan=2><form:input path="staffemail" /></td></tr>
				<tr><th>電話番号</th><td><form:input path="stafftel" maxlength='14' pattern="^[0-9]+$"/></td>
						<th>携帯電話</th><td><form:input path="staffmobiletel" maxlength='14' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>郵便番号</th><td colspan=2><form:input path="staffpostcode" maxlength='8' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>住所</th><td colspan=2><form:input path="staffadd" /></td></tr>
				<tr><th>最寄駅</th><td><form:input path="staffneareststation" /></td>
						<th>所属企業</th><td>
						<form:select path="affiliationid">
								<c:forEach var="obj" items="${Adata}" >
									<form:option value="${obj.affiliationId}"><c:out value="${obj.affiliationName}"/></form:option>
								</c:forEach>
							</form:select></td></tr>
				<tr><th colspan=2>備考</th><td colspan=2><form:input path="staffremarks" /></td></tr>
			</table>
		<br>
				<input type="submit" value="登録" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
			<p><font color="red">※備考は空欄可。</font></p>
			</div>
		</body>
	</html>
	</c:if>

	<!-- 派遣社員情報編集 -->
	<c:if test="${List != 'null'}">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8)">
			<title>派遣社員情報編集</title>
		</head>
		<body>
		<div align="center">
			<h2>派遣社員情報編集</h2>
		</div>
		<br>
		<div align="center">
			<form:form modelAttribute="syainModel"  action="syainUpdate3">
			<input type="hidden" value="${staffId}" name="staffid">
			<table border=1>
				<tr><th colspan=2>社員名称</th><td colspan=2><form:input path="staffname" value="${staffName}"/></td></tr>
				<tr><th colspan=2>メールアドレス</th><td colspan=2><form:input path="staffemail" value="${staffEMail}"/></td></tr>
				<tr><th>電話番号</th><td><form:input path="stafftel" value="${staffTel}" maxlength='14' pattern="^[0-9]+$"/></td>
						<th>携帯電話</th><td><form:input path="staffmobiletel" value="${staffMobileTel}" maxlength='14' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>郵便番号</th><td colspan=2><form:input path="staffpostcode" value="${staffPostCode}" maxlength='8' pattern="^[0-9]+$"/></td></tr>
				<tr><th colspan=2>住所</th><td colspan=2><form:input path="staffadd" value="${staffAdd}"/></td></tr>
				<tr><th>最寄駅</th><td><form:input path="staffneareststation" value="${staffNearestStation}"/></td>
						<th>所属企業</th><td>
							<form:select path="affiliationid">
									<c:forEach var="obj" items="${Adata}" >
										<form:option value="${obj.affiliationId}"><c:out value="${obj.affiliationName}"/></form:option>
									</c:forEach>
							</form:select></td></tr>
				<tr><th colspan=2>備考</th><td colspan=2><form:input path="staffremarks" value="${staffRemarks}"/></td></tr>
				</table>
		<br>
				<input type="submit" value="確定" />　
				<input type="button" onclick="javascript: history.back();" value="戻る">　
				<input type="reset" value="リセット" />
			</form:form>
			<p><font color="red">※備考は空欄可。</font></p>
		</div>
		</body>
	</html>
	</c:if>