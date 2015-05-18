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
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/i18n/jquery.ui.datepicker-ja.js"></script>
    <link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript">
    
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
    <table>
	<form action="${pageContext.request.contextPath}/attendList" method="post">
		<tr><td>生徒名検索 :</td>
		<td><input type="text" name="student_name" size="20" /></td></tr>
		<tr><td>講義名検索 :</td>
		<td><input type="text" name="lecture_name" size="20"/></td></tr>
		<tr><td>日付検索 :</td>
		<td><input type="text" name="lecture_date" id="datepicker"/>～<input type="text" name="lecture_date2" id="datepicker2"/></td></tr>
		<tr><td>時限検索 :</td>
		<td><input type="text" name="lecture_hour" size="20"/></td></tr>
		<tr><td></td><td><input type="submit" value="検索"></td></tr>
	</form>
	<form action="${pageContext.request.contextPath}/attendList" method="get">
		<tr><td></td><td><input type="submit" value="一覧表示"></td></tr>
	</form>
    </table>

    <hr>
    <form>
    <c:if test="${datalist !=null}">
	<table border="1" width="800">
	<tr><th>ID</th><th>名前</th><th>講義名</th><th>日付</th><th>時限</th></tr>

	<c:forEach var="obj" items="${datalist}" varStatus="status">
		<tr align="center">
		<td><a href="<c:url value="studentUpdate?id=${obj.studentId}"/>" ><c:out value="${obj.studentId}"  /></a></td>
		<td><a href="<c:url value="studentUpdate?id=${obj.studentId}"/>" ><c:out value="${obj.studentName}"/></td>
		<td><a href="<c:url value="lessonUpdate?id=${obj.lectureId}"/>" ><c:out value="${obj.lessonName}" /></td>
		<td><c:out value="${obj.lectureDate}" /></td>
		<td><c:out value="${obj.lectureHour}" /></td>

		</tr>
	</c:forEach>

	</table>
	</c:if>
	</form>
	<input type="submit" value="戻る"  onclick="location.href='managerMain'"/>
</body>
</html>

