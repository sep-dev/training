<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>講師新規登録画面</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" >
  </head>
  <body>
    <div class="managerDiv">
      <h1>講師新規登録画面</h1>
      <ul>
        <li>空欄が無いように入力してください</li>
        <li>パスワードは8文字以上で入力してください</li>
      </ul>
      <p>${message}</p>
      <form:form modelAttribute="teacher" action="teachersAdd">
        <table>
          <tr><td></td><td><form:errors path="*" element="div" /></td></tr>
          <tr><th>講師ID：</th>
            <td><form:input path="teacherId"  required="true" placeholder="講師IDを入力してください" pattern="^[0-9]*$"/></td>
            <form:errors path="teacherId" cssClass="error" element="td"/>
          </tr>
          <tr><th>講師パスワード：</th>
            <td><form:password path="teacherPassword"  required="true" placeholder="パスワードを入力してください" minlength='8'/></td>
            <form:errors path="teacherPassword" cssClass="error" element="td"/>
          </tr>
          <tr><th>講師パスワード(確認用)：</th>
            <td><input type="text" name="passwordConfirm"  required placeholder="パスワードを入力してください"/></td>
          </tr>
          <tr><th>名前：</th>
            <td><form:input path="teacherName" required="true" placeholder="氏名を入力してください"/></td>
            <form:errors path="teacherName" cssClass="error" element="td"/>
          </tr>
          <tr><th>所属クラス：</th>
            <td><form:select path="clas">
              <c:forEach items="${selectClass}" var="opt">
                <c:choose>
                  <c:when test="${opt.classId eq id}">
                    <option value="${opt.classId}" selected="selected"><c:out value="${opt.className}"/></option>
                  </c:when>
                  <c:otherwise>
                    <option value="${opt.classId}"><c:out value="${opt.className}"/></option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </form:select></td></tr>
          <tr><th>住所：</th>
            <td><form:input path="teacherAddress" required="true" placeholder="住所を入力してください"/></td>
            <form:errors path="teacherAddress" cssClass="error" element="td"/>
          </tr>
          <tr><th>電話番号</th>
            <td><form:input path="teacherTel" required="true" placeholder="電話番号を入力してください" pattern="^[0-9]{10,11}$"/></td>
            <form:errors path="teacherTel" cssClass="error" element="td"/>
          </tr>
        </table>
        <p><input type="submit" value="登録"/>
        <input type="reset" value="リセット"/></p>
      </form:form>
      <p><input type="submit" value="戻る"  onClick="history.go(-1)"/></p>
    </div>
  </body>
</html>
