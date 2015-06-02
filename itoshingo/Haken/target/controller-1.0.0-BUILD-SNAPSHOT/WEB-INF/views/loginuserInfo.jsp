<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
		<script type="text/javascript">
		<!--
				function disp(){
					if(window.confirm('削除しますか？')){
						return true;
					}else{
						return false;
					}
				}
		//-->
		</script>
	</head>
	<body>
		<a href="/controller/logout" target="_top">ログアウト</a>
		<br>
		<div align="center">
			<h3>ログインユーザー情報</h3>
		</div>
		<br>
		<form method="post" action="/controller/haken/loginuserSelect">
		検索：<input type="text" name="input"/> <input type="submit" value="検索">
		</form>

		<a href="/controller/haken/loginuserInsert" target="right">
			<input type="submit" value="新規登録">
		</a>


			<c:if test="${data !=null}">
				<div align="center">
					<table border="1" style="width:50%">
						<tr><th>ログインユーザー名称</th><th>ログインユーザー</th><th>パスワード</th><th>編集</th></tr>
						<c:forEach var="obj" items="${data}" varStatus="status">
							<tr><td rowspan="2"><c:out value="${obj.loginusername}"/></td>
							<td rowspan="2"><c:out value="${obj.loginuser}"/></td>
							<td rowspan="2"><c:out value="${obj.loginUserPass}"/></td>
							<td>
								<form name="form1" method="get" action="/controller/haken/loginuserUpdate" target="right">
									<input type="hidden" name="id" value="${obj.loginuserId}">
									<input type="submit" value="編集">
								</form>
							</td></tr>
							<tr><td>
								<form name="form2" method="post" action="/controller/haken/loginuserDelete" target="right">
									<input type="hidden" name="id" value="${obj.loginuserid}">
									<input type="submit" value="削除" onClick="return disp();">
								</form>
							</td></tr>
						</c:forEach>
					</table>
				</div>
			</c:if>

	</body>
</html>