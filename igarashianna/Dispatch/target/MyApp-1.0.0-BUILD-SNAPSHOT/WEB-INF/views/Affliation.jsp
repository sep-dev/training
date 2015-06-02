<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所属元リスト</title>
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
	<p>
		<!-- ③所属元情報一覧表示画面 -->
	<div align="center">
		<h1>${title}</h1>
	</div>

	<form:form modelAttribute="affliation_Search_Model"
		action="${pageContext.request.contextPath}/Affliation" method="post">
		<form:label path="searchword">検索 ：</form:label>
		<form:input path="searchword" size="20" />
		<input type="submit" name="search" value="検索" />
	</form:form>
	<form action="${pageContext.request.contextPath}/Affliation Insert"
		method="get">
		<input type="submit" value="新規登録">
	</form>
	<table border="1">
		<tr>
			<th>企業名</th>
			<th>郵便番号</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>最寄駅</th>
			<th>備考</th>
			<th>編集</th>
		</tr>
		<c:forEach var="obj" items="${data}" varStatus="status">
			<form:form modelAttribute="AffliationModel"
				action="${pageContext.request.contextPath}/Affliation Update"
				method="post">
				<c:if test="${data != null}">


					<tr>
						<input type="hidden" name="affliationId"
							value="${obj.affliationId}" />
						<td><c:out value="${obj.affliationName}" /></td>
						<td><c:out value="${obj.affliationPostCode}" /></td>
						<td><c:out value="${obj.affliationAdd}" /></td>
						<td><c:out value="${obj.affliationTel}" /></td>
						<td><c:out value="${obj.affliationNearestStation}" /></td>
						<td><c:out value="${obj.affliationRemarks}" /></td>
						<td><input type="submit" value="編集" /><br />


				</c:if>
			</form:form>
			<form:form modelAttribute="AffliationModel"
				action="${pageContext.request.contextPath}/Affliation Update" onSubmit="return check()">
				<input type="hidden" name="affliationId"
							value="${obj.affliationId}" />
				<input type="submit" name="delete" value="削除" /></td></tr>
				</form:form>

		</c:forEach>
	</table>
</body>
</html>