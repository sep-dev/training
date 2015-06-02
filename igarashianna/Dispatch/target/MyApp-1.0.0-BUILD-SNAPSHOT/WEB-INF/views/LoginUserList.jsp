<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログインユーザーリスト</title>
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
		<!-- ⑤ログインユーザー情報一覧表示画面 -->
	<div align="center">
		<h1>${title}</h1>
	</div>
	<form:form modelAttribute="loginUser_Search_Model"
		action="${pageContext.request.contextPath}/LoginUserList"
		method="post">
		<form:label path="searchword">検索 ：</form:label>
		<form:input path="searchword" size="20" />
		<input type="submit" name="search" value="検索" />
	</form:form>

	<form action="${pageContext.request.contextPath}/LoginUserList Insert"
		method="get">
		<input type="submit" value="新規登録">
	</form>

	<table border="1">
		<tr>
			<th>ログインユーザー名称</th>
			<th>ログインユーザー</th>
			<th>パスワード</th>
			<th>編集</th>
		</tr>

		<c:forEach var="obj" items="${data}" varStatus="status">

			<form:form modelAttribute="loginUserModel"
				action="${pageContext.request.contextPath}/LoginUserList Update"
				method="post">
				<c:if test="${data != null}">



					<tr>
						<input type="hidden" name="loginUserID" value="${obj.loginUserID}" />

						<td><c:out value="${obj.loginUserName}" /></td>
						<td><c:out value="${obj.loginUser}" /></td>
						<td><c:out value="${obj.loginUserPass}" /></td>
						<td><input type="submit" value="編集" /><br />
				</c:if>
			</form:form>
			<form:form modelAttribute="loginUserModel"
				action="${pageContext.request.contextPath}/LoginUserList Update" onSubmit="return check()">
				<input type="hidden" name="loginUserID" value="${obj.loginUserID}" />
				<input type="submit" name="delete" value="削除" /></td>
				</form:form>
		</c:forEach>
	</table>
</body>
</html>