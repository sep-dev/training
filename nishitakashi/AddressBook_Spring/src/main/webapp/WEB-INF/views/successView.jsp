<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登録成功</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/c_list.css" />" type="text/css" />
  </head>
  <body>
    <div id="wrap">
      <h1 id="title">登録成功画面</h1>
      <p>登録成功！</p>
      <div id="edittext">
        <input type="submit" value="一覧表示" onClick="location.href='showList'"/>
      </div>
    </div>
  </body>
</html>