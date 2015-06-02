<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ログイン後初期画面</title>

<script>
function check(){
    myRet = confirm("削除してもよろしいですか？");
    if ( myRet == true ){
    	return true;
    }else{
    	return false;
    }
}
</script>



</head>
<body>
	<p>
		<iframe src="${pageContext.request.contextPath}/MainMenu" width="200"
			height="550" align="left" frameBorder="0"></iframe>
	</p>
	<!-- ログイン成功時画面・①派遣先情報一覧画面 -->
	<div align="center">
		<h1>${title}</h1>
		<p>${message}</p>
	</div>
	<form:form modelAttribute="staff_Search_Model"
		action="${pageContext.request.contextPath}/LoginList" method="post">
		<form:label path="date">表示期限：</form:label>
		<form:select path="dear">
			<option value="2000-" label="2000年"/>
			<option value="2010-" label="2010年"/>
			<option value="2011-" label="2011年"/>
			<option value="2012-" label="2012年"/>
			<option value="2013-" label="2013年"/>
			<option value="2014-" label="2014年"/>
			<option value="2015-" label="2015年"selected />
		</form:select>年
		<form:select path="day">
			<option value="01-01" label="1"/>
			<option value="02-01" label="2"/>
			<option value="03-01" label="3"/>
			<option value="04-01" label="4"/>
			<option value="05-01" label="5"/>
			<option value="06-01" label="6"/>
			<option value="07-01" label="7"/>
			<option value="08-01" label="8"/>
			<option value="09-01" label="9"/>
			<option value="10-01" label="10"/>
			<option value="11-01" label="11"/>
			<option value="12-01" label="12"/>
		</form:select>月
		<p>
			<form:label path="searchword">検索 ：</form:label>
			<form:input path="searchword" size="20" />
			<input type="submit" name="search" value="検索" />
		</p>
	</form:form>


	<form action="${pageContext.request.contextPath}/LoginList Insert"
		method="get">
		<input type="submit" value="新規登録">
	</form>

	<table border="1">

		<tr>
			<th>派遣社員名</th>
			<th>派遣先企業名</th>
			<th>単金</th>
			<th>条件</th>
			<th>控除単価</th>
			<th>超過単価</th>
			<th>サイト</th>
			<th>開始日</th>
			<th>終了日</th>
			<th>備考</th>
			<th>編集</th>
		</tr>

		<c:forEach var="obj" items="${data}" varStatus="status">

			<form:form modelAttribute="staffModel"
				action="${pageContext.request.contextPath}/LoginList Update"
				method="post">

				<c:if test="${data != null}">

					<tr>
						<input type="hidden" name="staffManId" value="${obj.staffManId}" />
						<input type="hidden" name="staffId" value="${obj.staffId}" />
						<input type="hidden" name="clientId" value="${obj.clientId}" />
						<td><c:out value="${obj.staffName}" /></td>
						<td><c:out value="${obj.clientName}" /></td>
						<td><c:out value="${obj.amountMonth}" /></td>
						<td><c:out value="${obj.conditions}" /></td>
						<td><c:out value="${obj.deductionUnitPrice}" /></td>
						<td><c:out value="${obj.overtimeRate}" /></td>
						<td><c:out value="${obj.site}" /></td>
						<td><c:out value="${obj.startDate}" /></td>
						<td><c:out value="${obj.endDate}" /></td>
						<td><c:out value="${obj.staffManRemarks}" default="値が存在しません。" /></td>
						<td><input type="submit" value="編集" /><br />
				</c:if>
			</form:form>
			<form:form modelAttribute="staffModel"
				action="${pageContext.request.contextPath}/LoginList Update"
				onSubmit="return check()">
				<input type="hidden" name="staffManId" value="${obj.staffManId}" />
						<input type="hidden" name="staffId" value="${obj.staffId}" />
						<input type="hidden" name="clientId" value="${obj.clientId}" />
				<input type="submit" name="delete" value="削除" />
			</form:form>
			</tr>
		</c:forEach>

	</table>
</body>
</html>