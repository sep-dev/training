<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    削除確認画面
   hiddun属性のvalueに、削除対象のIDを付与
--%>

<%
  if(request.getMethod().equals("GET")  || request.getParameter("id") == null){
      response.sendRedirect("./.."); //methodがGET、またはIDが無ければTOPに強制遷移
  }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./../css/common.css" />
    <link rel="stylesheet" type="text/css" href="./../css/deleteConf.css" />
    <script type="text/javascript" src="./js/addressbook.js"></script>
    <title>削除確認</title>
  </head>
  <body>
    <section>
      <h1 class="title">本当に削除してもいいですか？</h1>
      <form action="./../Delete" method="POST">
          <input type="hidden" name="id" value="<% out.print(request.getParameter("id")); %>" />
          <p><input type="submit" value="削除" /></p>
      </form>
      <form action="./../AddressList" method="POST">
        <p><input type="submit" value="一覧表示へ" /></p>
      </form>
    </section>
  </body>
</html>