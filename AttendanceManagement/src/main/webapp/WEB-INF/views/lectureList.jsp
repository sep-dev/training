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
      <h1>講義管理画面</h1>
      <form action="${pageContext.request.contextPath}/manager/lectureList" method="post">
        <table>
          <tr><td>講義名検索 :</td>
            <td><input type="text" name="lessonName" value="${find1}"/></td></tr>
          <tr><td>講師名検索 :</td>
            <td><input type="text" name="teacherName" value="${find2}"/></td></tr>
          <tr><td>日付検索 :</td>
            <td><input type="date" name="date" value="${find3}"/>～<input type="date" name="date2" value="${find4}"/></td></tr>
          <tr><td>時限検索 :</td>
            <td><input type="text" name="lectureHour" value="${find5}"/></td></tr>
          <tr><td></td><td><input type="submit" value="検索"></td></tr>
        </table>
      </form>
      <form action="${pageContext.request.contextPath}/manager/lectureList" method="get">
        <input type="submit" value="一覧表示">
      </form>
      <input type="submit" value="新規作成"  onclick="location.href='lectureAdd'"/>
      <hr>
      <form name='form'>
        <c:if test="${datalist !=null}">
          <table border="1" class="managerListTable">
            <thead>
              <tr>
                <th class="col_id">講義ID</th>
                <th class="col_lecture">講義名</th>
                <th class="col_lecTeacher">担当講師名</th>
                <th class="col_date">日付</th>
                <th class="col_lecHour">時限</th>
                <th class="col_bottun"></th>
              </tr>
          </thead>
          <tbody>
              <c:forEach var="obj" items="${datalist}" varStatus="status">
                <tr align="center">
                  <td class="col_id"><a href="<c:url value="lectureUpdate?id=${obj.lectureId}"/>" ><c:out value="${obj.lectureId}" /></td>
                  <td class="col_lecture"><a href="<c:url value="lessonUpdate?id=${obj.lessonId}"/>" ><c:out value="${obj.lessonName}"/></td>
                  <td class="col_lecTeacher"><a href="<c:url value="teacherUpdate?id=${obj.teacherId}"/>" ><c:out value="${obj.teacherName}"/></td>
                  <td class="col_date"><c:out value="${obj.lectureDate}"/></td>
                  <td class="col_lecHour"><c:out value="${obj.lectureHour}"/></td>
                  <td class="col_bottun"><button type="submit" name="id" value="${obj.lectureId}" onClick="form.action='lectureUpdate';return true">編集</button><br/>
                    <button type="submit" name="id" value="${obj.lectureId}" onClick="form.action='lectureDelete';return true">削除</button></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </form>
      <input type="submit" value="戻る" onClick="location.href='managerMain'"/>
    </div>
  </body>
</html>
