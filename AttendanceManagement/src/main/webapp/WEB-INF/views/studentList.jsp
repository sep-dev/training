<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>生徒一覧画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
    <script type="text/javascript">
    <!--
    function goServletB(){
    	document.getElementById('form').action = 'studentUpdate';
    	}
    	function goServletC(){
    	document.getElementById('form').action = 'servletCのパス';
    	}
    // -->
    </script>
</head>
<body>
    <div class="managerDiv">
    <h1>生徒管理画面</h1>
    <p>生徒一覧を表示</p>

	<form action="${pageContext.request.contextPath}/studentList" method="post">
	<table>
		<tr><td>検索 :</td>
		<td><input type="text" name="fstr" value="${find1}"/></td></tr>
		<tr><td></td><td><input type="submit" value="検索"></td></tr>
	</table>
	</form>
	<form action="${pageContext.request.contextPath}/studentList" method="get">
        <input type="submit" value="一覧表示">
    </form>

    <input type="submit" value="新規作成"  onclick="location.href='studentAdd'"/>
    <hr>
    <form name='form'>
    <c:if test="${datalist !=null}">
	<table border="1" class="managerListTable">
	<thead>
		<tr><th class="col_s">ID</th><th class="col_m">名前</th><th class="col_l">住所</th>
		    <th class="col_m">電話番号</th><th class="col_bottun"></th><th class="col_ss"></th></tr>
	</thead>
	<tbody>
	<c:forEach var="obj" items="${datalist}" varStatus="status">
		<tr>
		<td class="col_s"><a href="<c:url value="studentUpdate?id=${obj.studentId}"/>" ><c:out value="${obj.studentId}" /></td>
		<td class="col_m"><a href="<c:url value="studentUpdate?id=${obj.studentId}"/>" ><c:out value="${obj.studentName}"/></td>
		<td class="col_l"><c:out value="${obj.studentAddress}" /></td>
		<td class="col_m"><c:out value="${obj.studentTel}" /></td>
		<td class="col_bottun"><button type="submit" name="id" value="${obj.studentId}" onClick="form.action='studentUpdate';return true">編集</button><br/>
		    <button type="submit" name="id" value="${obj.studentId}" onClick="form.action='studentDelete';return true">削除</button></td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	</c:if>
	</form>
	<input type="submit" value="戻る"  onClick="history.go(-1)"/>
	</div>
</body>
</html>