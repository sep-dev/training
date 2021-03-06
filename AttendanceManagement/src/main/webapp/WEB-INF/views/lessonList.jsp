<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>科目一覧画面</title>
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
      <h1>科目管理画面</h1>
      <form action="${pageContext.request.contextPath}/manager/lessonList" method="post">
        <table>
          <tr><td>科目名検索 :</td>
            <td><input type="text" name="fstr" value="${find1}"/></td></tr>
          <tr><td></td><td><input type="submit" value="検索"></td></tr>
        </table>
      </form>
      <form action="${pageContext.request.contextPath}/manager/lessonList" method="get">
        <input type="submit" value="一覧表示">
      </form>
      <input type="submit" value="新規作成"  onclick="location.href='lessonAdd'"/>
      <hr>
      <form name='form'>
        <c:if test="${datalist !=null}">
          <table border="1" class="managerListTable">
            <thead>
              <tr>
                <th class="col_lessonId">科目ID</th>
                <th class="col_lesson">科目名</th>
                <th class="col_teacher">担当講師名</th>
                <th class="col_bottun"></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="obj" items="${datalist}" varStatus="status">
                <tr align="center">
                  <td class="col_lessonId"><a href="<c:url value="lessonUpdate?id=${obj.lessonId}"/>" ><c:out value="${obj.lessonId}" /></td>
                  <td class="col_lesson"><a href="<c:url value="lessonUpdate?id=${obj.lessonId}"/>" ><c:out value="${obj.lessonName}"/></td>
                  <td class="col_teacher"><a href="<c:url value="teacherUpdate?id=${obj.teacher.teacherId}"/>" ><c:out value="${obj.teacher.teacherName}"/></td>
                  <td class="col_bottun"><button type="submit" name="id" value="${obj.lessonId}" onClick="form.action='lessonUpdate';return true">編集</button><br/>
                  <button type="submit" name="id" value="${obj.lessonId}" onClick="form.action='lessonDelete';return true">削除</button></td>
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
