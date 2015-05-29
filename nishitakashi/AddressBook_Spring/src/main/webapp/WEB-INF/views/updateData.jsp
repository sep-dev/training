<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>更新画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
  </head>
  <body>
    <div id="wrap">
      <h1 id="title">会員情報更新画面</h1>
      <p>${selectData}</p>
      <div id="edittext">
        <table>
          <form:form modelAttribute="form" action="${pageContext.request.contextPath}/updateData">
            <tr><td><form:label path="name">名前：</form:label></td>
              <td><form:input path="name" size="50"/></td></tr>
            <tr><td><form:label path="address">住所：</form:label></td>
              <td><form:input path="address" size="50"/></td></tr>
            <tr><td><form:label path="tel">電話番号</form:label></td>
              <td><form:input path="tel" size="20" maxsize="11"/></td></tr>
            <tr><td><input type="submit" value="更新" /></td><td><input type="reset" value="リセット"/></td></tr>
          </form:form>
        </table>
        <form:form modelAttribute="form"  action="${pageContext.request.contextPath}/delete">
          <input type="submit" value="削除"/>
        </form:form>
        <input type="submit" value="一覧表示" onClick="location.href='showList'"/>
      </div>
    </div>
  </body>
</html>