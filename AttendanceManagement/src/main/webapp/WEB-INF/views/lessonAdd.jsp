<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>科目新規登録画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <div class="managerDiv">
      <h1>科目新規登録画面</h1>
      <p>${message}</p>
      <form:form modelAttribute="lesson" action="lessonAdd">
        <table>
          <tr><th>科目ID：</th>
            <td><form:input path="lessonId" required="true" placeholder="IDを入力してください" pattern="^[0-9]*$"/></td>
            <form:errors path="lessonId" cssClass="error" element="td"/>
          </tr>
          <tr><th>科目名：</th>
            <td><form:input path="lessonName" required="true" placeholder="名前を入力してください"/></td>
              <form:errors path="lessonName" cssClass="error" element="td"/>
          </tr>
          <tr><th>担当講師名：</th>
            <td><form:select path="teacher"  items="${selectTeacher}" itemLabel="teacherName" itemValue="teacherId"/></td></tr>
        </table>
        <p><input type="submit" value="登録"/>
        <input type="reset" value="リセット"/></p>
      </form:form>
      <p><input type="submit" value="戻る"  onClick="history.go(-1)"/></p>
    </div>
  </body>
</html>