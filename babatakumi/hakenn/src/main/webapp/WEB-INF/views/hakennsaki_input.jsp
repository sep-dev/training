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
		<form:form modelAttribute="ManagementSql">
			<!-- 更新 -->
			<c:if test="${list != null}">
				<input type="hidden" value="1" name="up">
					<table border="1" style="width: 700px">
						<c:forEach var="obj" items="${list}" varStatus="status">
						<input type="hidden" value="${obj.staffManId}" name="id">

							<tr><td colspan="2"><label>派遣社員名</label></td>
								<td colspan="2"><select name="sub" style="width:100%">
										<c:forEach var="obj2" items="${list2}" varStatus="status">
											<option value ="${obj2.staffId}">${obj2.staffName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>

							<tr><td colspan="2"><label>派遣先企業</label></td>
								<td colspan="2"><select name="kigyou" style="width:100%">
										<c:forEach var="obj3" items="${list3}" varStatus="status">
												<option value ="${obj3.clientId}">${obj3.clientName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr><td>単金</td>
								<td><input type="text" style="width:100%" name="amo" value="${obj.amountMonth}"></td>
								<td>条件</td>
								<td><input type="text" style="width:100%" name="con" value="${obj.conditions}"></td>
							</tr>
							<tr><td>控除単価</td>
								<td><input type="text" style="width:100%" name="ded" value="${obj.deductionUnitPrice}"></td>
								<td>超過単価</td>
								<td><input type="text" style="width:100%" name="over" value="${obj.overtimeRate}"></td>
							</tr>
							<tr><td colspan="2">サイト</td>
								<td colspan="2"><input type="text" style="width:100%" name="site" value="${obj.site}" ></td>
							</tr>
							<tr><td><label>開始日</label></td>
								<td><input type="text" name="yearS" value="${obj.yearS}"  style="width: 50px">
								<label>年</label>
								<input type="text" name="monthS" value="${obj.monthS}" style="width: 50px">
								<label>月</label>
								<input type="text" name="dayS" value="${obj.dayS}" style="width: 50px">
								<label>日</label></td>

								<td><label>終了日</label></td>
								<td><input type="text" name="yearE" value="${obj.yearE}" style="width: 50px">
								<label>年</label>
								<input type="text" name="monthE" value="${obj.monthE}" style="width: 50px">
								<label>月</label>
								<input type="text" name="dayE" value="${obj.dayE}" style="width: 50px">
								<label>日</label></td>
							</tr>
							<tr><td colspan="2"><label>備考</label></td>
								<td colspan="2"><textarea style="width:100%" name="remarks">${obj.staffManRemarks}</textarea></td>
							</tr>
						</c:forEach>
					</table>
			</c:if>

			<!-- 新規作成 -->
			<c:if test="${list == null}">
				<input type="hidden" value="2" name="up">
					<table border="1" style="width:700px">
							<tr><td colspan="2"><label>派遣社員名</label></td>
								<td colspan="2"><select name="sub" style="width:100%">
									<option value = 0>選択してください</option>
										<c:forEach var="obj2" items="${list2}" varStatus="status">
											<option value ="${obj2.staffId}">${obj2.staffName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>

							<tr><td colspan="2"><label>派遣先企業</label></td>
								<td colspan="2"><select name="kigyou" style="width:100%">
									<option value = 0>選択してください</option>
										<c:forEach var="obj3" items="${list3}" varStatus="status">
												<option value ="${obj3.clientId}">${obj3.clientName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr><td>単金</td>
								<td><input type="text" style="width:100%" name="amo"></td>
								<td>条件</td>
								<td><input type="text" style="width:100%" name="con" ></td>
							</tr>
							<tr><td>控除単価</td>
								<td><input type="text" style="width:100%" name="ded" ></td>
								<td>超過単価</td>
								<td><input type="text" style="width:100%" name="over"></td>
							</tr>
							<tr><td colspan="2">サイト</td>
								<td colspan="2"><input type="text" style="width:100%" name="site" ></td>
							</tr>
							<tr><td><label>開始日</label></td>
								<td><input type="text" name="yearS" style="width: 50px">
								<label>年</label>
								<input type="text" name="monthS" style="width: 50px">
								<label>月</label>
								<input type="text" name="dayS" style="width: 50px">
								<label>日</label></td>

								<td><label>終了日</label></td>
								<td><input type="text" name="yearE" style="width: 50px">
								<label>年</label>
								<input type="text" name="monthE" style="width: 50px">
								<label>月</label>
								<input type="text" name="dayE" style="width: 50px">
								<label>日</label></td>
							</tr>
							<tr><td colspan="2"><label>備考</label></td>
								<td colspan="2"><textarea style="width:100%" name="remarks"></textarea></td>
							</tr>
					</table>
			</c:if>
			<input type="submit" value="${button}" name="change">
		</form:form>

		<a href="/spring/menu">
			<input type="submit" value=戻る name="back">
		</a>
	</div>
	</body>
</html>