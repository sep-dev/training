<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>講義一覧画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
     <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/i18n/jquery.ui.datepicker-ja.js"></script>
    <link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" rel="stylesheet" />
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
    <script type="text/javascript" >
    <!--
      $(function() {
    	  $("#datepicker").datepicker({
    	  });
    	  $("#datepicker2").datepicker({
    	  });
      });
    -->
    </script>
    <h1>${title}</h1>
    <p>${message}</p>
    <table width="800">
	<form action="${pageContext.request.contextPath}/lectureList" method="post">
		<tr><td>講義名検索 :</td>
		<td><input type="text" name="lessonName" size="20"/></td></tr>
		<tr><td>講師名検索 :</td>
		<td><input type="text" name="teacherName" size="20"/></td></tr>
		<tr><td>日付検索 :</td>
		<td><input type="text" name="date" id="datepicker"/>～<input type="text" name="date2" id="datepicker2"/></td></tr>
		<tr><td>時限検索 :</td>
		<td><input type="text" name="lectureHour" size="20"/></td></tr>
		<tr><td></td><td><input type="submit" value="検索"></td></tr>
	</form>
    </table>
    <input type="submit" value="新規作成"  onclick="location.href='lectureAdd'"/>
    <hr>
    <form name='form'>
    <c:if test="${datalist !=null}">
	<table border="1" width="800" >
	<tr><th>講義ID</th><th>講義名</th><th>担当講師名</th><th>日付</th><th>時限</th></tr>

	<c:forEach var="obj" items="${datalist}" varStatus="status">
		<tr align="center">
		<td ><a href="<c:url value="lectureUpdate?id=${obj.lectureId}"/>" ><c:out value="${obj.lectureId}" /></td>
		<td><a href="<c:url value="lessonUpdate?id=${obj.lessonId}"/>" ><c:out value="${obj.lessonName}"/></td>
		<td><c:out value="${obj.teacherName}"/></td>
		<td><c:out value="${obj.lectureDate}"/></td>
		<td><c:out value="${obj.lectureHour}"/></td>
		<td><button type="submit" name="id" value="${obj.lectureId}" onClick="form.action='lectureUpdate';return true">編集</button><br/>
		    <button type="submit" name="id" value="${obj.lectureId}" onClick="form.action='lectureDelete';return true">削除</button></td>
		</tr>
	</c:forEach>

	</table>
	</c:if>
	</form>
	<input type="submit" value="戻る"  onclick="location.href='managerMain'"/>
</body>
</html>