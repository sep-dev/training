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
			<h3>所属元情報</h3>
		</div>
		<br>
		<form method="post" action="/controller/haken/shozokumotoSelect">
		検索：<input type="text" name="input"/> <input type="submit" value="検索">
		</form>

		<a href="/controller/haken/shozokumotoInsert" target="right">
			<input type="submit" value="新規登録">
		</a>

		<c:if test="${data !=null}">
			<div align="center">
				<table border="1" style="width:60%">
					<tr><th>企業名</th><th>郵便番号</th><th>住所</th><th>電話番号</th><th>最寄駅</th><th>備考</th><th>編集</th></tr>
					<c:forEach var="obj" items="${data}" varStatus="status">
						<tr><td rowspan="2"><c:out value="${obj.affiliationName}" /></td>
						<td rowspan="2"><c:out value="${obj.affiliationPostCode}" /></td>
						<td rowspan="2"><c:out value="${obj.affiliationAdd}" /></td>
						<td rowspan="2">${obj.affiliationTel}</td>
						<td rowspan="2"><c:out value="${obj.affiliationNearestStation}" /></td>
						<td rowspan="2"><c:out value="${obj.affiliationRemarks}" /></td>
						<td>
						<form name="form1" method="get" action="/controller/haken/shozokumotoUpdate" target="right">
							<input type="hidden" name="id" value="${obj.affiliationId}">
							<input type="submit" value="編集">
						</form>
						</td></tr>
						<tr><td>
							<form name="form2" method="post" action="/controller/haken/shozokumotoDelete" target="right">
								<input type="hidden" name="id" value="${obj.affiliationId}">
								<input type="submit" value="削除" onClick="return disp();">
							</form>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</body>
</html>