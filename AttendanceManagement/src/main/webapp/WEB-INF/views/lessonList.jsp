<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>教科一覧画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
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
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
	<form action="${pageContext.request.contextPath}/lessonList" method="post">
		<tr><td>検索 :</td>
		<td><input type="text" name="fstr" size="20"/></td></tr>
		<tr><td></td><td><input type="submit" value="検索"></td></tr>
	</form>
    </table>
    <input type="submit" value="新規作成"  onclick="location.href='lessonAdd'"/>
    <hr>
    <form name='form'>
    <c:if test="${datalist !=null}">
	<table border="1" width="800">
	<tr><th>科目ID</th><th>科目名</th><th>担当講師名</th></tr>

	<c:forEach var="obj" items="${datalist}" varStatus="status">
		<tr align="center">
		<td ><c:out value="${obj.lessonId}" /></td>
		<td><c:out value="${obj.lessonName}"/></td>
		<td><c:out value="${obj.teacher.teacherName}"/></td>

		<td width="50"><button type="submit" name="id" value="${obj.lessonId}" onClick="form.action='lessonUpdate';return true">編集</button><br/>
		    <button type="submit" name="id" value="${obj.lessonId}" onClick="form.action='lessonDelete';return true">削除</button></td>
		</tr>
	</c:forEach>

	</table>
	</c:if>
	</form>
	<input type="submit" value="戻る"  onclick="location.href='managerMain'"/>
</body>
</html>