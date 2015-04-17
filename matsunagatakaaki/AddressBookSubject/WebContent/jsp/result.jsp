<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    登録、更新、削除処理後の結果画面
  <meta>タグ内の、<title>
  <body>タグ内の、<h1>,<form>,<input>
    に、動的に値を挿入する。
--%>


<%
   String TITLE = "title";
   String H1_TEXT = "h1";
   String URL = "url";
   String VALUE = "btnValue";
   if(request.getAttribute(TITLE) == null || request.getAttribute(H1_TEXT) == null ||
      request.getAttribute(URL) == null || request.getAttribute(VALUE) == null){
       response.sendRedirect("./../"); //必要な4つの値のうち、1つでも不足していればTopへ強制遷移
   }
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/common.css" />
    <link rel="stylesheet" type="text/css" href="./css/result.css" />
    <script type="text/javascript" src="./js/addressbook.js"></script>
    <title><% out.print((String)request.getAttribute(TITLE)); %></title>
  </head>
  <body>
    <section>
      <h1 class="title"><% out.print((String)request.getAttribute(H1_TEXT)); %></h1>
      <form action="<% out.print((String)request.getAttribute(URL)); %>" method="POST">
        <p><input type="submit" value="<%out.print((String)request.getAttribute(VALUE)); %>" /></p>
      </form>
    </section>
  </body>
</html>