<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
   更新する値を入力するページ
--%>

<%
  String ID = "id";
  String ADDRESS_DATA = "addressData";
  if(request.getMethod().equals("GET") ||
          request.getAttribute(ID) == null || request.getAttribute(ADDRESS_DATA) == null){
      response.sendRedirect("./.."); //methodがGET、または必要な値が不足している場合、Topページに強制遷移
  }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/common.css" />
    <link rel="stylesheet" type="text/css" href="./css/update.css" />
    <script type="text/javascript" src="./js/addressbook.js"></script>
    <title>会員情報の更新</title>
  </head>
  <body>
    <section>
      <h1 class="title">会員情報の更新</h1>
      <p><% out.print((String)request.getAttribute(ADDRESS_DATA)); %></p>
      <form action="./Update" method="POST" id="input">
        <input type="hidden" name="id" value="<% out.print((String)request.getAttribute(ID)); %>" />
        <input type="hidden" name="update" />
        <table>
          <tr>
            <td>氏名</td>
            <td><input type="text" name="name" id="name" size="20" pattern="^[\D]*$"/></td>
          </tr>
          <tr>
            <td>住所</td>
            <td><input type="text" name="address" id="address" size="25"/></td>
          </tr>
          <tr>
            <td>電話番号</td>
            <td><input type="tel" name="tel" id="tel" size="20" maxlength="11" pattern="^[0-9]*$" /></td>
          </tr>
        </table>
        <p>
          <input type="submit" value="更新" />
          <input type="reset" value="リセット" />
          <input type="submit" value="一覧表示" onclick="itemsReset();" formaction="./AddressList" />
        </p>
      </form>
      <form action="./jsp/deleteConfirmation.jsp" method="POST" >
        <input type="hidden" name="id" value="<%out.print((String)request.getAttribute(ID)); %>" />
        <p><input type="submit" value="削除" /></p>
      </form>
    </section>
  </body>
</html>