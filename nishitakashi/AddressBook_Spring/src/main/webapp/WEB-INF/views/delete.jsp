<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>削除画面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
  </head>
  <body>
    <div id="wrap">
      <h1 id="title">会員情報削除画面</h1>
      <p>${selectData}</p>
      <div id="edittext">
        <form:form modelAttribute="form" action="${pageContext.request.contextPath}/delete">
          <p>削除してよろしいですか？</p>
          <input type="submit" value="削除"/>
        </form:form>
      </div>
      <div id="button">
      <input type="submit" value="一覧表示" onClick="location.href='showList'"/>
      </div>
    </div>
  </body>
</html>
