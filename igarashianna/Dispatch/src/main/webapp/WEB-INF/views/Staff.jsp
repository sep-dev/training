<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派遣社員リスト</title>
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
		<!-- ②派遣社員情報一覧表示画面 -->
	<div align="center">
		<h1>${title}</h1>
		<p>${message}</p>
	</div>
	<form:form modelAttribute="staffMan_Search_Model"
		action="${pageContext.request.contextPath}/Staff" method="post">
		<form:label path="searchword">検索 ：</form:label>
		<form:input path="searchword" size="20" />
		<input type="submit" name="search" value="検索" />
	</form:form>
	<form action="${pageContext.request.contextPath}/Staff Insert"
		method="get">
		<input type="submit" value="新規登録">
	</form>

	<table border="1">
		<tr>
			<th>社員名</th>
			<th>メールアドレス</th>
			<th>郵便番号</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>携帯電話番号</th>
			<th>最寄駅</th>
			<th>所属企業名</th>
			<th>備考</th>
			<th>編集</th>
		</tr>
		<c:forEach var="obj" items="${data}" varStatus="status">
			<form:form modelAttribute="staffManModel"
				action="${pageContext.request.contextPath}/Staff Update"
				method="post">
				<c:if test="${data != null}">

					<tr>
						<input type="hidden" name="staffId" value="${obj.staffId}" />
						<input type="hidden" name="affliationId"
							value="${obj.affliationId}" />
						<td><c:out value="${obj.staffName}" /></td>
						<td><c:out value="${obj.staffMail}" /></td>
						<td><c:out value="${obj.staffPostCode}" /></td>
						<td><c:out value="${obj.staffAdd}" /></td>
						<td><c:out value="${obj.staffTel}" /></td>
						<td><c:out value="${obj.staffMobileTel}" /></td>
						<td><c:out value="${obj.staffNearestStation}" /></td>
						<td><c:out value="${obj.affliationName}" /></td>
						<td><c:out value="${obj.staffRemarks}" /></td>
						<td><input type="submit" value="編集" /><br />


				</c:if>
			</form:form>
			<form:form modelAttribute="staffManModel"
				action="${pageContext.request.contextPath}/Staff Update" onSubmit="return check()">
				<input type="hidden" name="staffId" value="${obj.staffId}" />
						<input type="hidden" name="affliationId"
							value="${obj.affliationId}" />
				<input type="submit" name="delete" value="削除" /></td></tr>
				</form:form>
		</c:forEach>
	</table>
</body>
</html>